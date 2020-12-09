package francisco.calado.emojiindex.database

import androidx.room.*
import io.reactivex.Maybe


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: RoomUser): Maybe<Long>

    @Query("DELETE FROM roomuser WHERE name = :name")
    fun deleteUser(name: String): Maybe<Int>

    @Query("SELECT EXISTS (SELECT 1 FROM roomuser WHERE name = :name)")
    fun hasUser(name: String): Maybe<Boolean>

    @Query("SELECT * FROM roomuser WHERE name == :name")
    fun getUser(name: String): Maybe<RoomUser>

    @Query("SELECT * FROM roomuser")
    fun getUsers(): Maybe<List<RoomUser>>

    @Query("DELETE FROM roomuser")
    fun clearUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(users: List<RoomUser>)

    @Transaction
    fun clearAndAddUsers(users: List<RoomUser>) {
        // Anything inside this method runs in a single transaction.
        clearUsers()
        addUsers(users)

    }
}