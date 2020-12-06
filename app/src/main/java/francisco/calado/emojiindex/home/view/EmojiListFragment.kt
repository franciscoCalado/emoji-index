package francisco.calado.emojiindex.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import francisco.calado.emojiindex.BaseFragment
import francisco.calado.emojiindex.R
import francisco.calado.emojiindex.home.model.Emoji
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_emoji_list.*
import javax.inject.Inject
import javax.inject.Named

class EmojiListFragment : BaseFragment(), EmojiListView {

    @field:[Inject Named("emojiListClick")]
    lateinit var emojiListClickSubject: PublishSubject<Int>

    @Inject
    lateinit var adapter: EmojiListAdapter

    @Inject
    lateinit var presenter: EmojiListPresenter

    private val refreshSubject = PublishSubject.create<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFragmentComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_emoji_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emoji_list.layoutManager = GridLayoutManager(context, 5)
        emoji_list.adapter = adapter
        swipe_refresh.setOnRefreshListener {
            refreshSubject.onNext(true)
            swipe_refresh.isRefreshing = false
        }

    }

    override fun onStart() {
        super.onStart()
        presenter.populateView()
        presenter.handleItemClicked()
        presenter.handleRefresh()
    }

    override fun onStop() {
        super.onStop()
        presenter.clear()
    }

    override fun addEmojis(emojis: List<Emoji>) {
        adapter.addData(emojis)
    }

    override fun emojiClicked(): Observable<Int> {
        return emojiListClickSubject
    }

    override fun removeEmoji(position: Int) {
        adapter.removeItem(position)
    }

    override fun refreshTriggered(): Observable<Boolean> {
        return refreshSubject
    }

    override fun refresh(emojis: List<Emoji>) {
        adapter.refresh(emojis)
    }
}