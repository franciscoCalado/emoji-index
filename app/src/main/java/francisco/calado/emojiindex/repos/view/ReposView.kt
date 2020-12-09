package francisco.calado.emojiindex.repos.view

interface ReposView {

    fun loadRepos(repos: List<String>)
    fun removeLoadMore()
}