package francisco.calado.emojiindex.di

import dagger.Subcomponent
import francisco.calado.emojiindex.users.view.UsersFragment

@Subcomponent(modules = [UsersModule::class])
@FragmentScope
interface UsersComponent {

    fun inject(usersFragment: UsersFragment)

}