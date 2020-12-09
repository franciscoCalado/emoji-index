package francisco.calado.emojiindex.repos

import android.os.Bundle
import androidx.fragment.app.Fragment
import francisco.calado.emojiindex.BaseActivity
import francisco.calado.emojiindex.di.ReposComponent
import francisco.calado.emojiindex.di.ReposModule

abstract class BaseReposFragment : Fragment() {

    private lateinit var fragmentComponent: ReposComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent =
            (activity as BaseActivity).getActivityComponent().plus(ReposModule(this))

    }

    fun getFragmentComponent() = fragmentComponent

}