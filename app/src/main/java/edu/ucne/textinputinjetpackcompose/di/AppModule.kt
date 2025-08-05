package edu.ucne.textinputinjetpackcompose.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.textinputinjetpackcompose.data.local.database.TextInputInJetpackComposeDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideTextInputInJetpackComposeDb(@ApplicationContext contexto: Context) =
        Room.databaseBuilder(
            context = contexto,
            klass = TextInputInJetpackComposeDb::class.java,
            name = "TextInputInJetpackComposeDb.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMensajeDao(ccdb: TextInputInJetpackComposeDb) = ccdb.MensajeDao()
}