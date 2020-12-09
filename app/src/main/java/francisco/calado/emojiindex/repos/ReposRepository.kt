package francisco.calado.emojiindex.repos

import francisco.calado.emojiindex.repos.service.ReposResponse
import francisco.calado.emojiindex.repos.service.ReposService
import io.reactivex.Maybe

class ReposRepository(private val usersService: ReposService) {

    fun getRepos(page: Int, limit: Int): Maybe<List<String>> {
        return usersService.getRepos(page, limit).map { response -> mapToModel(response) }
    }

    private fun mapToModel(response: List<ReposResponse>): List<String> {
        val result = ArrayList<String>()

        for (item in response) {
            result.add(item.name)
        }
        return result
    }
}