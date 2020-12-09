package francisco.calado.emojiindex.home.service

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class EmojiMoshiAdapter {

    @ToJson
    fun toJson(emojis: List<EmojiResponse>): Map<String, String> {
        val result = HashMap<String, String>()
        for (emoji in emojis) {
            result.put(emoji.name, emoji.url)
        }
        return result
    }

    @FromJson
    fun fromJson(value: Map<String, String>): List<EmojiResponse> {
        val result = ArrayList<EmojiResponse>()
        for (entry in value) {
            result.add(EmojiResponse(entry.key, entry.value))
        }
        return result
    }
}