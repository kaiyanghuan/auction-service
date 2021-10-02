package features.stepdefs;

import features.commons.Authentication;
import features.context.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CommonStepDef {

    @Given("When login with {string} {string}")
    public void login(String username, String password) throws IOException {
        Authentication.login(username, password);
    }

    @Then("Client will receives status code of {int}")
    public void expects_status_code_of(int statusCode) {
        assertEquals(statusCode, Context.response.code());
    }
}
