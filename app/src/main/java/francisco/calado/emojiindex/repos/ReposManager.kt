package francisco.calado.emojiindex.repos

import io.reactivex.Maybe

class ReposManager(private val repository: ReposRepository) {

    private var page = 1

    fun getRepos(): Maybe<List<String>> {
        return repository.getRepos(page, LIMIT).doOnSuccess { page++ }
    }

    companion object {
        const val LIMIT = 10
    }
}