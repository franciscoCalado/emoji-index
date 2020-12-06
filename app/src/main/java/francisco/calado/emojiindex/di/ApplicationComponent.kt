package francisco.calado.emojiindex.di

import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, RepositoryModule::class])
@Singleton
interface ApplicationComponent {

    fun plus(navigationModule: NavigationModule): ActivityComponent

}