package francisco.calado.emojiindex.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import francisco.calado.emojiindex.FragmentNavigator
import francisco.calado.emojiindex.home.HomeManager
import francisco.calado.emojiindex.home.HomeRepository
import francisco.calado.emojiindex.home.view.HomeFragment
import francisco.calado.emojiindex.home.view.HomePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

@Module
class HomeModule(private val fragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideHomePresenter(homeManager: HomeManager): HomePresenter {
        return HomePresenter(
            fragment as HomeFragment,
            homeManager,
            CompositeDisposable(),
            AndroidSchedulers.mainThread()
        )
    }

    @FragmentScope
    @Provides
    fun provideHomeManager(
        homeRepository: HomeRepository,
        fragmentNavigator: FragmentNavigator
    ): HomeManager {
        return HomeManager(homeRepository, fragmentNavigator)
    }
}