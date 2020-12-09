package francisco.calado.emojiindex.home.service

import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET

class EmojiService(private val retrofit: Retrofit, private val ioScheduler: Scheduler) {

    fun getEmojis(): Single<List<EmojiResponse>> {
        return retrofit.create(EmojiServiceImpl::class.java).getEmojis().subscribeOn(ioScheduler)
    }

    interface EmojiServiceImpl {
        @GET("/emojis")
        fun getEmojis(): Single<List<EmojiResponse>>
    }
}