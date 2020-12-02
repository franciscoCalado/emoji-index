package francisco.calado.emojiindex

import android.app.Application
import francisco.calado.emojiindex.di.ApiModule
import francisco.calado.emojiindex.di.ApplicationComponent
import francisco.calado.emojiindex.di.DaggerApplicationComponent

class EmojiIndexApplication : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .apiModule(ApiModule()).build()
    }
}