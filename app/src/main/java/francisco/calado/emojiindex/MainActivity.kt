package francisco.calado.emojiindex

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import francisco.calado.emojiindex.home.view.HomeFragment
import francisco.calado.emojiindex.users.view.UsersFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getActivityComponent().inject(this)

        fragmentNavigator.navigateToInitialFragment(HomeFragment())

        (bottom_navigation_bar as BottomNavigationView).setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.action_users -> {
                    fragmentNavigator.navigateToFragment(UsersFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_home -> {
                    fragmentNavigator.navigateToFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_repos -> {
                    //Come back
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
        (bottom_navigation_bar as BottomNavigationView).selectedItemId = R.id.action_home
    }
}
