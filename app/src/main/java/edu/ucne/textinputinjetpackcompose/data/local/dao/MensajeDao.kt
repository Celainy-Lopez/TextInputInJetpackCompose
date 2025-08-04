package edu.ucne.textinputinjetpackcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.textinputinjetpackcompose.data.local.entities.MensajeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MensajeDao{
    @Upsert()
    suspend fun save(Mensaje: MensajeEntity)
    @Query("""
            SELECT *
                FROM Mensajes
                WHERE mensajeId =:id
                limit 1
    """)
    suspend fun find(id: Int): MensajeEntity?
    @Delete
    suspend fun delete(Mensaje: MensajeEntity)
    @Query("SELECT * FROM Mensajes")
    fun getAll(): Flow<List<MensajeEntity>>
}