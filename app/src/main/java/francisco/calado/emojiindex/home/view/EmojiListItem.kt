package francisco.calado.emojiindex.home.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import francisco.calado.emojiindex.home.model.Emoji
import kotlinx.android.synthetic.main.emoji_list_item.view.*

class EmojiListItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(emoji: Emoji) {
        Glide.with(itemView).load(emoji.url).into(itemView.emoji)
        itemView.emoji.contentDescription = emoji.name
    }
}