package francisco.calado.emojiindex

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import francisco.calado.emojiindex.di.ActivityComponent
import francisco.calado.emojiindex.di.NavigationModule

abstract class BaseActivity : AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun getActivityComponent(): ActivityComponent {
        if (activityComponent == null) {
            activityComponent = (application as EmojiIndexApplication).appComponent.plus(
                NavigationModule(
                    this,
                    R.id.fragment_container
                )
            )
        }
        return activityComponent as ActivityComponent
    }


}