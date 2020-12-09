package francisco.calado.emojiindex.home.view

import francisco.calado.emojiindex.home.model.Emoji
import io.reactivex.Flowable
import io.reactivex.Observable

interface HomeView {

    fun randomClicked(): Flowable<Boolean>

    fun listClicked(): Observable<Boolean>

    fun showEmoji(emoji: Emoji)
}