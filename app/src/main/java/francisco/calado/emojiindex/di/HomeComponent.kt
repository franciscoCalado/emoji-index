package francisco.calado.emojiindex.di

import dagger.Subcomponent
import francisco.calado.emojiindex.home.view.EmojiListHomeFragment
import francisco.calado.emojiindex.home.view.HomeFragment

@Subcomponent(modules = [HomeModule::class])
@FragmentScope
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    fun inject(emojiListFragment: EmojiListHomeFragment)
}