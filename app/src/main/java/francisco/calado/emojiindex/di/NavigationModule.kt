package francisco.calado.emojiindex.di

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import francisco.calado.emojiindex.FragmentNavigator

@Module
class NavigationModule(private val activity: AppCompatActivity, private val containerId: Int) {

    @ActivityScope
    @Provides
    fun provideActivity() = activity

    @ActivityScope
    @Provides
    fun provideContainerId() = containerId

    @ActivityScope
    @Provides
    fun provideFragmentNavigator(): FragmentNavigator {
        return FragmentNavigator(activity.supportFragmentManager, containerId)
    }
}