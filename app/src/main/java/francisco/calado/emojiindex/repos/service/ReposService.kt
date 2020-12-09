package francisco.calado.emojiindex.repos.service

import io.reactivex.Maybe
import io.reactivex.Scheduler
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class ReposService(private val retrofit: Retrofit, private val ioScheduler: Scheduler) {

    fun getRepos(page: Int, limit: Int): Maybe<List<ReposResponse>> {
        return retrofit.create(ReposServiceImpl::class.java).getRepos(page, limit)
            .subscribeOn(ioScheduler)
    }

    interface ReposServiceImpl {
        @GET("/users/google/repos")
        fun getRepos(
            @Query("page") page: Int,
            @Query("limit") limit: Int
        ): Maybe<List<ReposResponse>>
    }

}