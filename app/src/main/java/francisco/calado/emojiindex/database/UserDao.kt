package francisco.calado.emojiindex.database

import androidx.room.*
import io.reactivex.Maybe

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: RoomUser)

    @Delete
    fun deleteUser(user: RoomUser)

    @Query("SELECT avatar FROM roomuser")
    fun getUsers(): Maybe<RoomUser>
}