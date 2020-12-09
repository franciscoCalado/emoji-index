package francisco.calado.emojiindex.home.view

import francisco.calado.emojiindex.home.model.Emoji
import io.reactivex.Observable

interface EmojiListView {

    fun addEmojis(emojis: List<Emoji>)

    fun emojiClicked(): Observable<Int>

    fun removeEmoji(position: Int)

    fun refreshTriggered(): Observable<Boolean>

    fun refresh(emojis: List<Emoji>)
}