package francisco.calado.emojiindex.repos.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import francisco.calado.emojiindex.R

class ReposListAdapter(
    private val dataList: ArrayList<String>
) : RecyclerView.Adapter<ReposItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposItem {
        return if (viewType == VIEW_TYPE_ITEM) ReposListItem(
            LayoutInflater.from(parent.context).inflate(R.layout.repos_list_item, parent, false)
        )
        else
            ReposLoadingItem(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.repos_loading_item, parent, false)
            )

    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ReposItem, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == LOADING) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    fun addItems(repos: List<String>) {
        dataList.addAll(repos)
        notifyDataSetChanged()
    }

    fun addLoadMore() {
        if (!dataList.contains(LOADING)) {
            dataList.add(LOADING)
            notifyItemInserted(dataList.size - 1)
        }
    }

    fun removeLoadMore() {
        val size = dataList.size
        dataList.remove(LOADING)
        notifyItemRemoved(size)
    }


    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_ITEM = 1

        const val LOADING = "**LOADING**"
    }

}