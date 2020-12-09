package francisco.calado.emojiindex.users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding2.view.RxView
import francisco.calado.emojiindex.R
import francisco.calado.emojiindex.users.BaseUsersFragment
import francisco.calado.emojiindex.users.model.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_users.*
import javax.inject.Inject
import javax.inject.Named

class UsersFragment : BaseUsersFragment(), UsersView {

    @Inject
    lateinit var adapter: UsersGridAdapter

    @Inject
    lateinit var presenter: UsersPresenter

    @Inject
    @Named("usersSubject")
    lateinit var gridClickSubject: PublishSubject<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFragmentComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user_list.layoutManager = GridLayoutManager(context, 5)
        user_list.adapter = adapter
        presenter.populateView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.saveData(adapter.getData())
    }

    override fun onStart() {
        super.onStart()
        presenter.addUser()
        presenter.deleteUser()
    }

    override fun onStop() {
        super.onStop()
        presenter.clear()
    }

    override fun showUsers(users: List<User>) {
        adapter.addData(users)
    }

    override fun addUser(user: User) {
        adapter.addItem(user)
    }

    override fun searchClicked(): Observable<String> {
        return RxView.clicks(search_button).map { search_box.text.toString() }
    }

    override fun userClicked(): Observable<Int> {
        return gridClickSubject
    }

    override fun removeUser(postion: Int) {
        adapter.removeItem(postion)
    }

}