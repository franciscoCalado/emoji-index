package francisco.calado.emojiindex.users.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import francisco.calado.emojiindex.users.model.User
import kotlinx.android.synthetic.main.user_grid_item.view.*

class UserGridItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(user: User) {
        Glide.with(itemView).load(user.avatar).into(itemView.user_avatar)
    }
}