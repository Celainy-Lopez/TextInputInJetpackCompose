package edu.ucne.textinputinjetpackcompose.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import edu.ucne.textinputinjetpackcompose.data.local.dao.MensajeDao
import edu.ucne.textinputinjetpackcompose.data.local.entities.MensajeEntity
import java.util.Date


class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

@Database(
    entities = [
        MensajeEntity:: class,
    ],
    version = 2,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class TextInputInJetpackComposeDb : RoomDatabase() {
    abstract fun MensajeDao() : MensajeDao
}