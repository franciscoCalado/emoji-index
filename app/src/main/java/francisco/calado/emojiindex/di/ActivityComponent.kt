package francisco.calado.emojiindex.di

import dagger.Subcomponent
import francisco.calado.emojiindex.MainActivity

@Subcomponent(modules = [NavigationModule::class])
@ActivityScope
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun plus(homeModule: HomeModule): HomeComponent

    fun plus(usersModule: UsersModule): UsersComponent
}
