package francisco.calado.emojiindex.di

import dagger.Subcomponent
import francisco.calado.emojiindex.home.view.HomeFragment

@Subcomponent(modules = [HomeModule::class])
@FragmentScope
interface FragmentComponent {

    fun inject(homeFragment: HomeFragment)
}