package features.stepdefintions

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.ocbc.auctionservice.entities.User
import features.common.CommonApis
import features.models.UserResponse
import features.constant.UrlConstant
import features.context.TestContext
import features.models.UserRequest
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.And
import io.cucumber.java.en.When
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.IOException
import java.lang.reflect.Type

class UserStepDef {
    private val userUrl = UrlConstant.baseUrl + "/users"

    companion object {
        private val objectMapper = ObjectMapper()
    }

    @When("User calls GET all users")
    fun get_all_users() {
        CommonApis.get(userUrl, TestContext.user!!.token)
        val response = TestContext.response
        if (response != null && response.isSuccessful && response.body() != null) {
            val typeRef = object : TypeReference<List<UserResponse>>() {}
            TestContext.setUserResponses(objectMapper.readValue(response.body()!!.string(), typeRef))
        }
    }

    @When("User calls GET to view user")
    @Throws(IOException::class)
    fun get_user() {
        CommonApis.get(userUrl + "/" + TestContext.getUserResponse().id, TestContext.getToken())
    }

    @When("^User calls POST create user by map$")
    @Throws(IOException::class)
    fun post_create_user(table: DataTable) {
        val requestBody: RequestBody = RequestBody.create(MediaType.get("application/json"),
                objectMapper.writeValueAsString(generateUserRequestFromDataTable(table)))
        CommonApis.post(userUrl, TestContext.user!!.token, requestBody)
        val response = TestContext.response
        if (response != null && response.isSuccessful && response.body() != null) {
            TestContext.setUserResponse(objectMapper.readValue(response.body()!!.string(), UserResponse::class.java))
        }
    }

    @When("^User calls PUT update user by map$")
    @Throws(IOException::class)
    fun put_update_user(table: DataTable) {
        val requestBody: RequestBody = RequestBody.create(MediaType.get("application/json"),
                objectMapper.writeValueAsString(generateUserRequestFromDataTable(table)))
        CommonApis.put(userUrl + "/" + TestContext.getUserResponse().id, TestContext.getToken(), requestBody)
        val response = TestContext.response
        if (response != null && response.isSuccessful && response.body() != null) {
            TestContext.setUserResponse(objectMapper.readValue(response.body()!!.string(), UserResponse::class.java))
        }
    }

    @And("Client will receive user list size more than {int}")
    fun expects_user_list_size_to_be_more_than(minimum: Int) {
        assert(TestContext.userContext.userResponses.size > minimum)
    }

    @And("Client will be able to search for user {string}")
    fun expects_user_to_search_for(name: String) {
        assert(TestContext.userContext.userResponses.stream().anyMatch { userResponse -> userResponse.name == name })
    }

    @When("User calls DELETE to remove user")
    @Throws(IOException::class)
    fun delete_user() {
        CommonApis.delete(userUrl + "/" + TestContext.getUserResponse().id, TestContext.getToken())
    }


    @And("Client will be able to view user {string}")
    fun expects_user_to_view_for(name: String?) {
        assert(name == TestContext.getUserResponse().name)
    }

    @And("Client will NOT be able to find user {string}")
    fun expects_to_verify_user_not_in_list_anymore(name: String?) {
        assert(TestContext.getUserResponses().stream().noneMatch { userResponse -> userResponse.name == name })
    }

    @And("^Client will be able to verify user by map$")
    fun expects_to_verify_user(table: DataTable) {
        val user = generateUserFromDataTable(table)
        val userResponse = TestContext.getUserResponse()
        assert(userResponse.name == user.name)
        assert(userResponse.age == user.age)
        assert(userResponse.address == user.address)
    }

    private fun generateUserRequestFromDataTable(table: DataTable): UserRequest {
        val map = table.asMap<String, String>(String::class.java, String::class.java)
        val userRequest = UserRequest()
        val userRequestClass: Class<out UserRequest> = userRequest.javaClass
        map.forEach { (key: String?, value: String) ->
            try {
                val field = userRequestClass.getDeclaredField(key)
                field.isAccessible = true
                val type: Type = field.type
                if ((type as Class<*>).name == Integer::class.java.name) {
                    field[userRequest] = value.toInt()
                    return@forEach
                }
                field[userRequest] = value
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        return userRequest
    }

    private fun generateUserFromDataTable(table: DataTable): User {
        val map = table.asMap<String, String>(String::class.java, String::class.java)
        val user = User()
        val userClass: Class<out User> = user.javaClass
        map.forEach { (key: String?, value: String) ->
            try {
                val field = userClass.getDeclaredField(key)
                field.isAccessible = true
                val type: Type = field.type
                if ((type as Class<*>).name == Integer::class.java.name) {
                    field[user] = value.toInt()
                    return@forEach
                }
                field[user] = value
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        return user
    }
}