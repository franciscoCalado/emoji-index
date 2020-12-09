package francisco.calado.emojiindex.di

import dagger.Subcomponent
import francisco.calado.emojiindex.repos.view.ReposFragment

@Subcomponent(modules = [ReposModule::class])
@FragmentScope
interface ReposComponent {

    fun inject(reposFragment: ReposFragment)
}