package francisco.calado.emojiindex.home.view

import francisco.calado.emojiindex.home.EmojiListManager
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class EmojiListPresenter(
    private val emojiListView: EmojiListView,
    private val emojiListManager: EmojiListManager,
    private val viewScheduler: Scheduler,
    private val subscriptions: CompositeDisposable
) {

    fun populateView() {
        subscriptions.add(
            emojiListManager.getEmojis()
                .observeOn(viewScheduler)
                .doOnSuccess { emojis -> emojiListView.addEmojis(emojis) }
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun handleItemClicked() {
        subscriptions.add(
            emojiListView.emojiClicked()
                .observeOn(viewScheduler)
                .doOnNext { position -> emojiListView.removeEmoji(position) }
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun handleRefresh() {
        subscriptions.add(
            emojiListView.refreshTriggered()
                .flatMapMaybe { emojiListManager.getEmojis() }
                .observeOn(viewScheduler)
                .doOnNext { emojis -> emojiListView.refresh(emojis) }
                .subscribe({}, { it.printStackTrace() })
        )
    }

    fun clear() {
        subscriptions.clear()
    }
}