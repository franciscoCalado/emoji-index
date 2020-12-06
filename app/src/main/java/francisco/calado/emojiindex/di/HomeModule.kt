package francisco.calado.emojiindex.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import francisco.calado.emojiindex.FragmentNavigator
import francisco.calado.emojiindex.home.EmojiListManager
import francisco.calado.emojiindex.home.HomeManager
import francisco.calado.emojiindex.home.HomeRepository
import francisco.calado.emojiindex.home.view.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Named

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

    @FragmentScope
    @Provides
    fun provideEmojiListPresenter(emojiListManager: EmojiListManager): EmojiListPresenter {
        return EmojiListPresenter(
            fragment as EmojiListView,
            emojiListManager,
            AndroidSchedulers.mainThread(),
            CompositeDisposable()
        )
    }

    @FragmentScope
    @Provides
    fun provideEmojiListManager(homeRepository: HomeRepository): EmojiListManager {
        return EmojiListManager(homeRepository)
    }

    @FragmentScope
    @Provides
    @Named("emojiListClick")
    fun provideEmojiListClickSubject(): PublishSubject<Int> {
        return PublishSubject.create()
    }

    @FragmentScope
    @Provides
    fun provideEmojiListAdapter(
        @Named("emojiListClick") clickSubject: PublishSubject<Int>
    ): EmojiListAdapter {
        return EmojiListAdapter(ArrayList(), clickSubject)
    }
}