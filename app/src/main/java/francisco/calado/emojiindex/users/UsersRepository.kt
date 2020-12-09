package francisco.calado.emojiindex.users

import francisco.calado.emojiindex.database.AppDatabase
import francisco.calado.emojiindex.database.RoomUser
import francisco.calado.emojiindex.users.model.User
import francisco.calado.emojiindex.users.service.UserResponse
import francisco.calado.emojiindex.users.service.UserService
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

class UsersRepository(private val usersService: UserService, private val database: AppDatabase) {

    fun getUsers(): Maybe<List<User>> {
        return database.userDao().getUsers().subscribeOn(Schedulers.io()).map { roomUsers ->
            mapToUsers(roomUsers)
        }
    }

    fun saveUsers(users: List<User>): Completable {
        return Completable.fromCallable {
            database.userDao().clearAndAddUsers(mapToRoomUsers(users))
        }.subscribeOn(Schedulers.io())
    }

    private fun mapToRoomUsers(users: List<User>): List<RoomUser> {
        val result = ArrayList<RoomUser>()
        for (user in users) {
            result.add(RoomUser(user.name, user.avatar))
        }
        return result
    }

    private fun mapToUsers(roomUsers: List<RoomUser>): List<User> {
        val result = ArrayList<User>()
        for (user in roomUsers) {
            result.add(User(user.name, user.avatar, false))
        }
        return result
    }

    fun getUser(name: String): Maybe<User> {
        return usersService.getUser(name)
            .map { user -> mapServerResponseToUser(user) }

    }

    private fun mapServerResponseToUser(user: UserResponse): User {
        return User(user.login, user.avatar, user.id.isEmpty())
    }
}