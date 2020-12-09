package francisco.calado.emojiindex.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUser(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "avatar") val avatar: String
)