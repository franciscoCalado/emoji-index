package francisco.calado.emojiindex.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import francisco.calado.emojiindex.database.AppDatabase
import francisco.calado.emojiindex.users.UsersManager
import francisco.calado.emojiindex.users.UsersRepository
import francisco.calado.emojiindex.users.model.User
import francisco.calado.emojiindex.users.service.UserService
import francisco.calado.emojiindex.users.view.UsersFragment
import francisco.calado.emojiindex.users.view.UsersGridAdapter
import francisco.calado.emojiindex.users.view.UsersPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Retrofit
import javax.inject.Named

@Module
class UsersModule(private val fragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return UserService(retrofit, Schedulers.io())
    }

    @FragmentScope
    @Provides
    fun provideUsersRepository(userService: UserService, database: AppDatabase): UsersRepository {
        return UsersRepository(userService, database)
    }

    @FragmentScope
    @Provides
    fun provideUsersManager(usersRepository: UsersRepository): UsersManager {
        return UsersManager(usersRepository)
    }

    @FragmentScope
    @Provides
    @Named("usersSubject")
    fun provideUsersSucbject(): PublishSubject<Pair<User, Int>> {
        return PublishSubject.create()
    }

    @FragmentScope
    @Provides
    fun provideUsersGridAdapter(@Named("usersSubject") clickSubject: PublishSubject<Pair<User, Int>>): UsersGridAdapter {
        return UsersGridAdapter(ArrayList(), clickSubject)
    }

    @FragmentScope
    @Provides
    fun provideUsersPresenter(usersManager: UsersManager): UsersPresenter {
        return UsersPresenter(
            fragment as UsersFragment,
            usersManager,
            CompositeDisposable(),
            AndroidSchedulers.mainThread()
        )
    }

}