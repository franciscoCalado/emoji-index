package francisco.calado.emojiindex.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe

@Dao
interface EmojiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEmoji(emoji: RoomEmoji)

    @Query("SELECT count(name) FROM roomemoji limit 1")
    fun getCount(): Maybe<Int>

    @Query("SELECT * FROM roomemoji")
    fun getEmojis(): Maybe<List<RoomEmoji>>
}