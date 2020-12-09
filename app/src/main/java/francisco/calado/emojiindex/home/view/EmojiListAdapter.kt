package francisco.calado.emojiindex.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import francisco.calado.emojiindex.R
import francisco.calado.emojiindex.home.model.Emoji
import io.reactivex.subjects.PublishSubject

class EmojiListAdapter(
    private val dataList: ArrayList<Emoji>,
    private val clickSubject: PublishSubject<Int>
) : RecyclerView.Adapter<EmojiListItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiListItem {
        return EmojiListItem(
            LayoutInflater.from(parent.context).inflate(R.layout.emoji_list_item, parent, false)
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: EmojiListItem, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener { clickSubject.onNext(position) }
    }

    fun addData(emojis: List<Emoji>) {
        val lastPostion = dataList.size
        dataList.addAll(emojis)
        notifyItemRangeInserted(lastPostion, dataList.size)
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyDataSetChanged()
    }

    fun refresh(emojis: List<Emoji>) {
        dataList.clear()
        dataList.addAll(emojis)
        notifyDataSetChanged()
    }
}