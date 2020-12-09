package francisco.calado.emojiindex.users.view

import francisco.calado.emojiindex.users.model.User
import io.reactivex.Observable

interface UsersView {
    fun showUsers(users: List<User>)
    fun addUser(user: User)
    fun searchClicked(): Observable<String>
    fun userClicked(): Observable<Int>
    fun removeUser(postion: Int)
}