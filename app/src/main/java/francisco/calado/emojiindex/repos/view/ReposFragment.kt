package francisco.calado.emojiindex.repos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import francisco.calado.emojiindex.R
import francisco.calado.emojiindex.repos.BaseReposFragment
import kotlinx.android.synthetic.main.fragment_repos.*
import javax.inject.Inject

class ReposFragment : BaseReposFragment(), ReposView {

    @Inject
    lateinit var adapter: ReposListAdapter

    @Inject
    lateinit var presenter: ReposPresenter

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFragmentComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        google_repos_list.layoutManager = linearLayoutManager
        google_repos_list.adapter = adapter
        setRecyclerViewScrollListener()

    }

    override fun onResume() {
        super.onResume()
        presenter.populateView()
    }

    override fun onStop() {
        super.onStop()
        presenter.clear()
    }

    override fun loadRepos(repos: List<String>) {
        adapter.addItems(repos)
    }

    override fun removeLoadMore() {
        adapter.removeLoadMore()
    }

    private fun setRecyclerViewScrollListener() {
        var loading = true
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                if (loading) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loading = false
                        adapter.addLoadMore()
                        presenter.handleLoadMore()
                        loading = true
                    }
                }
            }
        }
        google_repos_list.addOnScrollListener(scrollListener)
    }
}