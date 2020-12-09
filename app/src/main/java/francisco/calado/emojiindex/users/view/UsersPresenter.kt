package francisco.calado.emojiindex.users.view

import francisco.calado.emojiindex.users.UsersManager
import francisco.calado.emojiindex.users.model.User
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class UsersPresenter(
    private val view: UsersView,
    private val manager: UsersManager,
    private val subscriptions: CompositeDisposable,
    private val viewScheduler: Scheduler
) {

    fun populateView() {
        subscriptions.add(manager.getUsers()
            .observeOn(viewScheduler)
            .filter { it.isNotEmpty() }
            .doOnSuccess { users -> view.showUsers(users) }
            .subscribe({}, { it.printStackTrace() })
        )
    }

    fun addUser() {
        subscriptions.add(view.searchClicked().flatMapMaybe { name ->
            manager.getUser(name)
                .observeOn(viewScheduler)
                .doOnSuccess { user ->
                    if (!user.hasError) {
                        view.addUser(user)
                    }
                }
        }
            .subscribe({}, { it.printStackTrace() }))
    }

    fun deleteUser() {
        subscriptions.add(
            view.userClicked()
                .doOnNext { position -> view.removeUser(position) }
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun saveData(users: List<User>) {
        subscriptions.add(
            manager.saveUsers(users)
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun clear() {
        subscriptions.clear()
    }
}