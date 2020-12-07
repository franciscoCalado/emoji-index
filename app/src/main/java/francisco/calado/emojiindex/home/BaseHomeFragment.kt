package francisco.calado.emojiindex.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import francisco.calado.emojiindex.BaseActivity
import francisco.calado.emojiindex.di.HomeComponent
import francisco.calado.emojiindex.di.HomeModule

abstract class BaseHomeFragment : Fragment() {

    private lateinit var fragmentComponent: HomeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent = (activity as BaseActivity).getActivityComponent().plus(HomeModule(this))

    }

    fun getFragmentComponent() = fragmentComponent
}