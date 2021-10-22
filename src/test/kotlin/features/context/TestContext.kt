package features.context

import features.common.TestUser
import features.models.UserResponse
import okhttp3.Response

object TestContext {
    var user: TestUser? = null
    var response: Response? = null
    var userContext: UserContext = UserContext(emptyList(), UserResponse())

    fun getToken() = user!!.token
    fun getUserResponse() = userContext.userResponse
    fun getUserResponses() = userContext.userResponses
    fun setUserResponse(userResponse: UserResponse) { userContext.userResponse = userResponse }
    fun setUserResponses(userResponses: List<UserResponse>) { userContext.userResponses = userResponses }
}

data class UserContext(
        var userResponses: List<UserResponse>,
        var userResponse: UserResponse
)