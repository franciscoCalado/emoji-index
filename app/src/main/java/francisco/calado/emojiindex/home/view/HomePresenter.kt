package francisco.calado.emojiindex.home.view

import francisco.calado.emojiindex.home.HomeManager
import francisco.calado.emojiindex.home.model.Emoji
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(
    private val view: HomeView,
    private val manager: HomeManager,
    private val subscriptions: CompositeDisposable,
    private val viewScheduler: Scheduler
) {

    fun getFirstEmoji() {
        subscriptions.add(
            showRandomEmoji()
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun handleRandomClick() {
        subscriptions.add(
            view.randomClicked()
                .flatMapMaybe { showRandomEmoji() }
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun handleListClick() {
        subscriptions.add(
            view.listClicked()
                .doOnNext { manager.navigateToEmojiList() }
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun clear() {
        subscriptions.clear()
    }

    fun dispose() {
        subscriptions.dispose()
    }

    private fun showRandomEmoji(): Maybe<Emoji> {
        return manager.getRandomEmoji()
            .observeOn(viewScheduler)
            .doOnSuccess { emoji ->
                view.showEmoji(emoji)
            }

    }
}