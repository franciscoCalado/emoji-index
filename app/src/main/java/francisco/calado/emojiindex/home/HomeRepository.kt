package francisco.calado.emojiindex.home

import androidx.room.RoomDatabase
import francisco.calado.emojiindex.database.AppDatabase
import francisco.calado.emojiindex.database.RoomEmoji
import francisco.calado.emojiindex.home.model.Emoji
import francisco.calado.emojiindex.home.service.EmojiResponse
import francisco.calado.emojiindex.home.service.EmojiService
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

class HomeRepository(private val database: RoomDatabase, private val service: EmojiService) {
    private var localCache = ArrayList<Emoji>()
    fun getEmojis(): Maybe<List<Emoji>> {
        return (database as AppDatabase).emojiDao()
            .getCount()
            .flatMap { count ->
                if (count == 0) {
                    return@flatMap service.getEmojis().map { result -> cacheResult(result) }
                        .toMaybe()
                } else
                    return@flatMap database.emojiDao().getEmojis().map { mapToModel(it) }
            }
            .subscribeOn(Schedulers.io())
    }

    private fun mapToModel(emojis: List<RoomEmoji>): ArrayList<Emoji> {
        val result = ArrayList<Emoji>()

        for (emoji in emojis) {
            result.add(Emoji(emoji.name, emoji.url))
        }
        return result
    }

    private fun cacheResult(emojis: List<EmojiResponse>): List<Emoji> {
        val result = ArrayList<Emoji>()
        for (emoji in emojis) {
            result.add(Emoji(emoji.name, emoji.url))
            (database as AppDatabase).emojiDao().addEmoji(RoomEmoji(emoji.name, emoji.url))
        }
        localCache = result
        return result
    }
}