package francisco.calado.emojiindex.users.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import francisco.calado.emojiindex.R
import francisco.calado.emojiindex.users.model.User
import io.reactivex.subjects.PublishSubject

class UsersGridAdapter(
    private val dataList: ArrayList<User>,
    private val clickSubject: PublishSubject<Int>
) : RecyclerView.Adapter<UserGridItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGridItem {
        return UserGridItem(
            LayoutInflater.from(parent.context).inflate(R.layout.user_grid_item, parent, false)
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: UserGridItem, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {
            clickSubject.onNext(position)
        }
    }

    fun addData(emojis: List<User>) {
        val lastPostion = dataList.size
        dataList.addAll(emojis)
        notifyItemRangeInserted(lastPostion, dataList.size)
    }

    fun addItem(user: User) {
        dataList.add(user)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyDataSetChanged()
    }

    fun getData() = dataList

}