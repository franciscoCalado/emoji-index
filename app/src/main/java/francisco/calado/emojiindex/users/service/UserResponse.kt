package francisco.calado.emojiindex.users.service

import com.squareup.moshi.Json

data class UserResponse(
    val login: String,
    val id: String,
    @field:Json(name = "avatar_url") val avatar: String
)
