package edu.ucne.textinputinjetpackcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "Mensajes",)
data class MensajeEntity(
    @PrimaryKey
    val mensajeId: Int? = null,
    val fecha: Date = Date(),
    val contenido: String = "",
    val remitente: String = "",
    val tipoRemitente: String = "",
)