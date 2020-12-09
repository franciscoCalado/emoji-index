package francisco.calado.emojiindex.repos.view

import android.view.View
import kotlinx.android.synthetic.main.repos_list_item.view.*

class ReposListItem(itemView: View) : ReposItem(itemView) {

    override fun bind(repo: String) {
        itemView.repo_name.text = repo
    }
}