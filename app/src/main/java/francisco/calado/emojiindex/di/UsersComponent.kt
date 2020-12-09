package francisco.calado.emojiindex.di

import dagger.Subcomponent
import francisco.calado.emojiindex.users.view.UsersFragment

@FragmentScope
@Subcomponent(modules = [UsersModule::class])
interface UsersComponent {

    fun inject(usersFragment: UsersFragment)

}