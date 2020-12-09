package francisco.calado.emojiindex.users

import francisco.calado.emojiindex.users.model.User
import io.reactivex.Completable
import io.reactivex.Maybe

class UsersManager(private val usersRepository: UsersRepository) {

    fun getUsers(): Maybe<List<User>> {
        return usersRepository.getUsers()
    }

    fun getUser(name: String): Maybe<User> {
        return usersRepository.getUser(name)
    }

    fun saveUsers(users: List<User>): Completable {
        return usersRepository.saveUsers(users)
    }
}