package francisco.calado.emojiindex.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import francisco.calado.emojiindex.repos.ReposManager
import francisco.calado.emojiindex.repos.ReposRepository
import francisco.calado.emojiindex.repos.service.ReposService
import francisco.calado.emojiindex.repos.view.ReposFragment
import francisco.calado.emojiindex.repos.view.ReposListAdapter
import francisco.calado.emojiindex.repos.view.ReposPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

@Module
class ReposModule(private val fragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideReposListAdapter(): ReposListAdapter {
        return ReposListAdapter(ArrayList())
    }

    @FragmentScope
    @Provides
    fun provideReposService(retrofit: Retrofit): ReposService {
        return ReposService(
            retrofit,
            Schedulers.io()
        )
    }

    @FragmentScope
    @Provides
    fun provideReposRepository(service: ReposService): ReposRepository {
        return ReposRepository(service)
    }

    @FragmentScope
    @Provides
    fun providesReposManager(reposRepository: ReposRepository): ReposManager {
        return ReposManager(reposRepository)
    }

    @FragmentScope
    @Provides
    fun providesReposPresenter(reposManager: ReposManager): ReposPresenter {
        return ReposPresenter(
            fragment as ReposFragment,
            reposManager,
            CompositeDisposable(),
            AndroidSchedulers.mainThread()
        )
    }

}