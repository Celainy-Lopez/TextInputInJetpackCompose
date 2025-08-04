package edu.ucne.textinputinjetpackcompose.presentation.Mensajes

import edu.ucne.textinputinjetpackcompose.data.local.entities.MensajeEntity
import java.util.Date

data class MensajeUiState (
    val mensajeId: Int? = null,
    val fecha: Date = Date(),
    val contenido: String = "",
    val remitente : String =  "",
    var tipoRemitente: String? = null,
    val errorMessage: String? = null,
    val mensajes: List<MensajeEntity> = emptyList()
)