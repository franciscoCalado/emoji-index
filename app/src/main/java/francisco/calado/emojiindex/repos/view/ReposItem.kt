package francisco.calado.emojiindex.repos.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ReposItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(repo: String)
}