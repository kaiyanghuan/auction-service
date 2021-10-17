package features.common

import com.auction.service.controllers.requests.LoginRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.ocbc.auctionservice.controllers.responses.JwtAuthenticationResponse
import features.constant.UrlConstant
import features.context.TestContext
import okhttp3.MediaType
import okhttp3.RequestBody

data class TestUser(
        val isAuthenticated: Boolean,
        val token: String,
        val username: String
)

object Auth {
    private val authUrl = UrlConstant.baseUrl + "/auth/login"
    private val objectMapper = ObjectMapper();

    fun login(username: String, password: String){
        val loginRequest = LoginRequest(username, password)
        val requestBody = RequestBody.create(MediaType.get("application/json")
                , objectMapper.writeValueAsString(loginRequest))
        val response = CommonApis.login(authUrl, requestBody)
        assert(200 == response.code())
        if(response.isSuccessful && response.body() != null){
            val authResponse = objectMapper.readValue(response.body()!!.string(), JwtAuthenticationResponse::class.java)
            TestContext.user = TestUser(true, authResponse.accessToken, username)
        }
    }


//    fun login(username: String?, password: String?) {
//        val loginRequest = com.ocbc.auctionservice.controllers.requests.LoginRequest(username, password)
//        try {
//            val requestBody = RequestBody.create(MediaType.get("application/json"), Authentication.objectMapper.writeValueAsString(loginRequest))
//            val response = CommonApis.login(Constant.baseUrl.toString() + Authentication.authUrl, requestBody)
//            if (response.isSuccessful && response.body() != null) {
//                val authenticationResponse = Authentication.objectMapper.readValue(response.body()!!.string(), AuthenticationResponse::class.java)
//                Context.user = TestUser(true, authenticationResponse.accessToken, username!!)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
}