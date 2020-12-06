package francisco.calado.emojiindex

import android.os.Bundle
import androidx.fragment.app.Fragment
import francisco.calado.emojiindex.di.FragmentComponent
import francisco.calado.emojiindex.di.HomeModule

abstract class BaseFragment : Fragment() {

    private lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent = (activity as BaseActivity).getActivityComponent().plus(HomeModule(this))

    }

    fun getFragmentComponent() = fragmentComponent
}