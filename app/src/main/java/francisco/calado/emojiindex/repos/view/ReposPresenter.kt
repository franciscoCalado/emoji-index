package francisco.calado.emojiindex.repos.view

import francisco.calado.emojiindex.repos.ReposManager
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class ReposPresenter(
    private val view: ReposView,
    private val manager: ReposManager,
    private val subscriptions: CompositeDisposable,
    private val viewScheduler: Scheduler
) {

    fun populateView() {
        subscriptions.add(manager.getRepos()
            .observeOn(viewScheduler)
            .doOnSuccess { repos ->
                view.loadRepos(repos)
            }
            .subscribe({}, {})
        )
    }

    fun handleLoadMore() {
        subscriptions.add(manager.getRepos()
            .observeOn(viewScheduler)
            .doOnSuccess { repos ->
                view.removeLoadMore()
                view.loadRepos(repos)
            }
            .subscribe({}, { it.printStackTrace() })
        )
    }

    fun clear() {
        subscriptions.clear()
    }

}
