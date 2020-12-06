package francisco.calado.emojiindex.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomEmoji::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun emojiDao(): EmojiDao
}