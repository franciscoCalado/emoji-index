package francisco.calado.emojiindex.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import francisco.calado.emojiindex.BaseFragment
import francisco.calado.emojiindex.R
import francisco.calado.emojiindex.home.model.Emoji
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    private val randomSubject = PublishSubject.create<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFragmentComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        random_button.setOnClickListener { randomSubject.onNext(true) }
    }

    override fun onStart() {
        super.onStart()
        presenter.getFirstEmoji()
        presenter.handleListClick()
        presenter.handleRandomClick()
    }

    override fun onStop() {
        super.onStop()
        presenter.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun randomClicked(): Flowable<Boolean> {
        return randomSubject.toFlowable(BackpressureStrategy.LATEST)
    }

    override fun listClicked(): Observable<Boolean> {
        return RxView.clicks(list_button).map { true }
    }

    override fun showEmoji(emoji: Emoji) {
        Glide.with(this).load(emoji.url).into(emoji_view)
    }


}