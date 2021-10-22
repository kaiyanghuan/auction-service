package features.stepdefintions

import features.common.Auth
import features.context.TestContext
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then

class CommonStepDef {

    @Given("When login with {string} {string}")
    fun login(username: String, password: String) {
        Auth.login(username, password)
    }

    @Then("Client will receives status code of {int}")
    fun expects_status_code_of(statusCode: Int) {
        val response = TestContext.response
        assert(response != null)
        assert(statusCode == response!!.code())
    }
}