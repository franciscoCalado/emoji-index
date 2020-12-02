package francisco.calado.emojiindex.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApplicationComponent {

}