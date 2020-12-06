package francisco.calado.emojiindex

import android.app.Application
import francisco.calado.emojiindex.di.ApiModule
import francisco.calado.emojiindex.di.ApplicationComponent
import francisco.calado.emojiindex.di.DaggerApplicationComponent
import francisco.calado.emojiindex.di.RepositoryModule

class EmojiIndexApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        appComponent = DaggerApplicationComponent.builder()
            .apiModule(ApiModule())
            .repositoryModule(RepositoryModule((this)))
            .build()
        super.onCreate()
    }

}