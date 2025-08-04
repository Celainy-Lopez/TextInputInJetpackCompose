package edu.ucne.textinputinjetpackcompose.data.repository

import edu.ucne.textinputinjetpackcompose.data.local.dao.MensajeDao
import edu.ucne.textinputinjetpackcompose.data.local.entities.MensajeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MensajesRepository @Inject constructor(
    private val dao: MensajeDao
) {
    suspend fun save(mensaje : MensajeEntity) = dao.save(mensaje)

    suspend fun find(id: Int): MensajeEntity? = dao.find(id)

    suspend fun delete(mensaje: MensajeEntity) = dao.delete(mensaje)

    fun getAll(): Flow<List<MensajeEntity>> = dao.getAll()
}