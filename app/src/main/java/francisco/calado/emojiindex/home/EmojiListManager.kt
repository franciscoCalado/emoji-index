package francisco.calado.emojiindex.home

import francisco.calado.emojiindex.home.model.Emoji
import io.reactivex.Maybe

class EmojiListManager(private val homeRepository: HomeRepository) {

    fun getEmojis(): Maybe<List<Emoji>> {
        return homeRepository.getEmojis()
    }
}