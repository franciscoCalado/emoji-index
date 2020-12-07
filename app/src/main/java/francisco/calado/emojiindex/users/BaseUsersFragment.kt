package francisco.calado.emojiindex.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import francisco.calado.emojiindex.BaseActivity
import francisco.calado.emojiindex.di.UsersComponent
import francisco.calado.emojiindex.di.UsersModule

abstract class BaseUsersFragment : Fragment() {

    private lateinit var fragmentComponent: UsersComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent =
            (activity as BaseActivity).getActivityComponent().plus(UsersModule(this))

    }

    fun getFragmentComponent() = fragmentComponent

}