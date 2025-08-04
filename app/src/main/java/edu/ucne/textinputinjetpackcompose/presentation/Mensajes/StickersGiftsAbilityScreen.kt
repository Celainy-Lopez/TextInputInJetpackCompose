package edu.ucne.textinputinjetpackcompose.presentation.Mensajes


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.delete
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun StickersGiftsAbility(
    viewModel: MensajesViewModel = hiltViewModel(),
    goBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedImages by remember { derivedStateOf { viewModel.selectedImages } }




    LaunchedEffect(Unit) {
        viewModel.getMensajes()
    }

    MensajeBodyScreenAbility(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        goBack = goBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MensajeBodyScreenAbility(
    uiState: MensajeUiState,
    onEvent: (MensajeEvent) -> Unit,
    goBack: () -> Unit,
    viewModel: MensajesViewModel = hiltViewModel(),

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
                            .contentReceiver { tranferableContent->
                                viewModel.handleContent(tranferableContent)
                            }
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
