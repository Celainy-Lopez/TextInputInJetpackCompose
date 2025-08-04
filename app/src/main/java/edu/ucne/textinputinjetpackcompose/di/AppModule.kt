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
    fun provideTextInputInJetpackComposeDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            TextInputInJetpackComposeDb::class.java,
            "TextInputInJetpackCompose.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMensajeDao(textInputInJetpackComposeDb: TextInputInJetpackComposeDb) = textInputInJetpackComposeDb.MensajeDao()
}