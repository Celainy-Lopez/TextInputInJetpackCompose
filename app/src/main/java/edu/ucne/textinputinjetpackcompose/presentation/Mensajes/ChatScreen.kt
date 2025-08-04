package edu.ucne.textinputinjetpackcompose.presentation.Mensajes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.delete
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.textinputinjetpackcompose.data.local.entities.MensajeEntity
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChatScreen(
    viewModel: MensajesViewModel = hiltViewModel(),
    goBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getMensajes()
    }

    MensajeBodyScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        goBack = goBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MensajeBodyScreen(
    uiState: MensajeUiState,
    onEvent: (MensajeEvent) -> Unit,
    goBack: () -> Unit,
    ) {
    val inputState = rememberTextFieldState()
    val mensajes = uiState.mensajes.sortedBy { it.fecha }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chats", color = MaterialTheme.colorScheme.onSecondary) },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            )
        },
        bottomBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    listOf("User", "Operator").forEach { tipo ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            RadioButton(
                                selected = uiState.tipoRemitente == tipo,
                                onClick = { onEvent(MensajeEvent.TipoRemitenteChange(tipo)) }
                            )
                            Text(text = tipo)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        state = inputState,
                        lineLimits = TextFieldLineLimits.SingleLine,
                        modifier = Modifier
                            .fillMaxWidth(),
                        placeholder = { Text("Escribe un mensaje...",
                                color = MaterialTheme.colorScheme.surface
                            )
                        },
                        shape = RoundedCornerShape(24.dp),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    onEvent(MensajeEvent.ContenidoChange(inputState.text.toString()))
                                    onEvent(MensajeEvent.Save)
                                    inputState.edit {
                                        delete(0, inputState.text.length)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Send,
                                    contentDescription = "Enviar",
                                    tint = MaterialTheme.colorScheme.surface
                                )
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                            focusedContainerColor = MaterialTheme.colorScheme.secondary,
                        )
                    )

                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(mensajes) { mensaje ->
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + expandVertically()
                    ) {
                        MensajeRow(mensaje)
                    }
                }
            }
        }
    }
}

@Composable
fun MensajeRow(mensaje: MensajeEntity) {
    val isOperator = mensaje.tipoRemitente == "Operator"
    val bubbleColor =
        if (isOperator) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
    val alignment = if (isOperator) Alignment.Start else Alignment.End
    val arrangement = if (isOperator) Arrangement.Start else Arrangement.End
    val inicial = mensaje.tipoRemitente.firstOrNull()?.uppercaseChar()?.toString() ?: "?"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isOperator) {
            AvatarSimulado(nombre = inicial)
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(horizontalAlignment = alignment) {
            Text(
                text = mensaje.tipoRemitente,
                style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.outline),
                modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
            )

            Box(
                modifier = Modifier
                    .background(color = bubbleColor, shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .widthIn(max = 280.dp)
            ) {
                Text(text = mensaje.contenido, color = MaterialTheme.colorScheme.surface)
            }

            Text(
                text = SimpleDateFormat(
                    "MMM dd, yyyy - HH:mm",
                    Locale.getDefault()
                ).format(mensaje.fecha),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        if (!isOperator) {
            Spacer(modifier = Modifier.width(8.dp))
            AvatarSimulado(nombre = inicial)
        }
    }
}

@Composable
fun AvatarSimulado(nombre: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color = MaterialTheme.colorScheme.outline, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = nombre,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.surface,
                fontWeight = FontWeight.Bold
            )
        )
    }
}