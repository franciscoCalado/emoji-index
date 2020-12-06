package francisco.calado.emojiindex.home

import francisco.calado.emojiindex.FragmentNavigator
import francisco.calado.emojiindex.home.model.Emoji
import francisco.calado.emojiindex.home.view.EmojiListFragment
import io.reactivex.Maybe
import java.util.*

class HomeManager(
    private val repository: HomeRepository,
    private val fragmentNavigator: FragmentNavigator
) {
    fun getEmojis(): Maybe<List<Emoji>> {
        return repository.getEmojis()
    }

    fun getRandomEmoji(): Maybe<Emoji> {
        return getEmojis().map { it[Random().nextInt(it.size)] }
    }

    fun navigateToEmojiList() {
        fragmentNavigator.navigateToFragment(EmojiListFragment())
    }
}