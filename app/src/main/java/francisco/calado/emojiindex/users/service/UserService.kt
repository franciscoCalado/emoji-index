package francisco.calado.emojiindex.users.service

import francisco.calado.emojiindex.users.model.User
import io.reactivex.Maybe
import io.reactivex.Scheduler
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class UserService(private val retrofit: Retrofit, private val ioScheduler: Scheduler) {

    fun getUser(name: String) {
        retrofit.create(UserServiceImpl::class.java).getUser(name).subscribeOn(ioScheduler)
    }

    interface UserServiceImpl {

        @GET("/users/{name}")
        fun getUser(@Path("name") name: String): Maybe<User>
    }
}