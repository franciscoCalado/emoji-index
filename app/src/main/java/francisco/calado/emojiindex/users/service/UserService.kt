package francisco.calado.emojiindex.users.service

import io.reactivex.Maybe
import io.reactivex.Scheduler
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class UserService(private val retrofit: Retrofit, private val ioScheduler: Scheduler) {

    fun getUser(name: String): Maybe<UserResponse> {
        return retrofit.create(UserServiceImpl::class.java).getUser(name).subscribeOn(ioScheduler)
            .onErrorResumeNext(Maybe.just(UserResponse("", "", "")))
    }

    interface UserServiceImpl {

        @GET("/users/{name}")
        fun getUser(@Path("name") name: String): Maybe<UserResponse>
    }
}