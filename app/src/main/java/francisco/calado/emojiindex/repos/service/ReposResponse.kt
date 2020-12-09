package francisco.calado.emojiindex.repos.service

import com.squareup.moshi.Json

data class ReposResponse(@field:Json(name = "full_name") val name: String)