package edu.ucne.textinputinjetpackcompose.presentation.Mensajes

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.content.TransferableContent
import androidx.compose.foundation.content.consume
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.textinputinjetpackcompose.data.local.entities.MensajeEntity
import edu.ucne.textinputinjetpackcompose.data.repository.MensajesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class MensajesViewModel @Inject constructor(
    private val mensajesRepository: MensajesRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MensajeUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: MensajeEvent){
        when(event) {
            is MensajeEvent.MensajeChange -> onMensajeIdChange(event.mensajeId)
            is MensajeEvent.FechaChange -> onFechaChange(event.fecha)
            is MensajeEvent.ContenidoChange -> onContenidoChange(event.contenido)
            is MensajeEvent.RemitenteChange -> onRemitenteChange(event.remitente)
            is MensajeEvent.TipoRemitenteChange -> onTipoRemitenteChange(event.tipoRemitente)

            MensajeEvent.Save -> saveMensaje()
            MensajeEvent.New -> nuevo()
            MensajeEvent.Delete -> deleteMensaje()
        }
    }

    private fun saveMensaje() {
        viewModelScope.launch {
            if (_uiState.value.contenido.isNullOrBlank()){
                _uiState.update {
                    it.copy(errorMessage = "Campo vacio!!!")
                }
            }
            else{
                mensajesRepository.save(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(contenido = "")
                }
            }
        }
    }


    private fun nuevo(){
        _uiState.update {
            it.copy(
                mensajeId = null,
                fecha = Date(),
                contenido = "",
                remitente = "",
            )
        }
    }

    fun getMensajes() {
        viewModelScope.launch {
            mensajesRepository.getAll().collect { mensajes ->
                _uiState.update {
                    it.copy(mensajes = mensajes)
                }
            }
        }
    }


    private fun deleteMensaje() {
        viewModelScope.launch {
            mensajesRepository.delete(_uiState.value.toEntity())
        }
    }


    private fun onFechaChange(fecha: Date) {
        _uiState.update {
            it.copy(fecha = fecha)
        }
    }

    private fun onMensajeIdChange(id: Int) {
        _uiState.update {
            it.copy(mensajeId = id)
        }
    }

    private fun onContenidoChange(contenido: String){
        _uiState.update {
            it.copy(contenido = contenido)
        }
    }

    private fun onRemitenteChange(remitente: String){
        _uiState.update {
            it.copy(remitente = remitente)
        }
    }

    private fun onTipoRemitenteChange(tipoRemitente: String){
        _uiState.update {
            it.copy(tipoRemitente = tipoRemitente)
        }
    }

    //Nuevo Mensaje
    val messageState = TextFieldState()

    //Imagenes para enviar con el siguiente mensaje
    var selectedImages by mutableStateOf<List<Uri>>(emptyList())
    @OptIn(ExperimentalFoundationApi::class)
    fun handleContent(
        transferableContent: TransferableContent
    ): TransferableContent? {
        val newUris = mutableListOf<Uri>()
        val remaining = transferableContent.consume {
            newUris += it.uri ?: return@consume false
            true
        }
        selectedImages = selectedImages + newUris
        return remaining
    }

    fun removeImage(uri: Uri) {
        selectedImages = selectedImages.filterNot { it == uri }
    }
}


fun MensajeUiState.toEntity() = MensajeEntity(
    mensajeId = mensajeId,
    fecha = fecha ?: Date(),
    contenido = contenido ?: "",
    remitente = remitente ?: "",
    tipoRemitente = tipoRemitente ?: "",
)

