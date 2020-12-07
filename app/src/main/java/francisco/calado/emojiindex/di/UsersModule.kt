package francisco.calado.emojiindex.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import francisco.calado.emojiindex.users.service.UserService
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

@Module
class UsersModule(fragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return UserService(retrofit, Schedulers.io())
    }

}