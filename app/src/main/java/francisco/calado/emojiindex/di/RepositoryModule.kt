package francisco.calado.emojiindex.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import francisco.calado.emojiindex.EmojiIndexApplication
import francisco.calado.emojiindex.database.AppDatabase
import francisco.calado.emojiindex.home.HomeRepository
import francisco.calado.emojiindex.home.service.EmojiService
import javax.inject.Singleton

@Module
class RepositoryModule(private val app: EmojiIndexApplication) {

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java, "app_database"
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeRepository(database: AppDatabase, service: EmojiService): HomeRepository {
        return HomeRepository(database, service)
    }
}