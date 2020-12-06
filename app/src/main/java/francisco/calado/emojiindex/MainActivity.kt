package francisco.calado.emojiindex

import android.os.Bundle
import francisco.calado.emojiindex.home.view.HomeFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getActivityComponent().inject(this)

        fragmentNavigator.navigateToInitialFragment(HomeFragment())

    }
}
