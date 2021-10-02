package features.stepdefs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.auctionservice.controllers.requests.UserRequest;
import com.ocbc.auctionservice.controllers.responses.UserResponse;
import com.ocbc.auctionservice.entities.User;
import features.commons.CommonApis;
import features.constants.Constant;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static features.context.Context.UserContext.userResponse;
import static features.context.Context.UserContext.userResponses;
import static features.context.Context.response;
import static features.context.Context.user;
import static org.junit.Assert.*;

@ScenarioScope
public class UserStepDef {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final String userUrl = Constant.baseUrl + "/users";

    @When("User calls GET all users")
    public void get_all_users() throws IOException {
        CommonApis.get(userUrl, user.getToken());
        if (response.isSuccessful() && response.body() != null) {
            userResponses = objectMapper.readValue(response.body().string(), new TypeReference<List<UserResponse>>() {
            });
        }
    }

    @When("^User calls POST create user by map$")
    public void post_create_user(DataTable table) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"),
                objectMapper.writeValueAsString(generateUserRequestFromDataTable(table)));
        CommonApis.post(userUrl, user.getToken(), requestBody);
        if (response.isSuccessful() && response.body() != null) {
            userResponse = objectMapper.readValue(response.body().string(), UserResponse.class);
        }
    }

    @When("^User calls PUT update user by map$")
    public void put_update_user(DataTable table) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"),
                objectMapper.writeValueAsString(generateUserRequestFromDataTable(table)));
        CommonApis.put(userUrl + "/" + userResponse.getId(), user.getToken(), requestBody);
        if (response.isSuccessful() && response.body() != null) {
            userResponse = objectMapper.readValue(response.body().string(), UserResponse.class);
        }
    }

    @When("User calls GET to view user")
    public void get_user() throws IOException {
        CommonApis.get(userUrl + "/" + userResponse.getId(), user.getToken());
    }

    @When("User calls DELETE to remove user")
    public void delete_user() throws IOException {
        CommonApis.delete(userUrl + "/" + userResponse.getId(), user.getToken());
    }

    @And("Client will receive user list size more than {int}")
    public void expects_user_list_size_to_be_more_than(int minimum) throws IOException {
        assertTrue(userResponses.size() > minimum);
    }

    @And("Client will be able to search for user {string}")
    public void expects_user_to_search_for(String name) {
        assertTrue(userResponses.stream().anyMatch(userResponse -> userResponse.getName().equals(name)));
    }

    @And("Client will be able to view user {string}")
    public void expects_user_to_view_for(String name) {
        assertEquals(userResponse.getName(), name);
    }

    @And("Client will NOT be able to find user {string}")
    public void expects_to_verify_user_not_in_list_anymore(String name) {
        assertFalse(userResponses.stream().anyMatch(userResponse -> userResponse.getName().equals(name)));
    }

    @And("^Client will be able to verify user by map$")
    public void expects_to_verify_user(DataTable table) {
        User user = generateUserFromDataTable(table);
        assertEquals(userResponse.getName(), user.getName());
        assertEquals(userResponse.getAge(), user.getAge());
        assertEquals(userResponse.getAddress(), user.getAddress());
    }

    private UserRequest generateUserRequestFromDataTable(DataTable table) {
        Map<String, String> map = table.asMap(String.class, String.class);
        UserRequest userRequest = new UserRequest();
        Class<? extends UserRequest> userRequestClass = userRequest.getClass();
        map.forEach((key, value) -> {
            try {
                Field field = userRequestClass.getDeclaredField(key);
                field.setAccessible(true);
                Type type = field.getType();
                if (((Class) type).getName().equals(Integer.class.getName())) {
                    field.set(userRequest, Integer.parseInt(value));
                    return;
                }
                field.set(userRequest, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return userRequest;
    }

    private User generateUserFromDataTable(DataTable table) {
        Map<String, String> map = table.asMap(String.class, String.class);
        User user = new User();
        Class<? extends User> userClass = user.getClass();
        map.forEach((key, value) -> {
            try {
                Field field = userClass.getDeclaredField(key);
                field.setAccessible(true);
                Type type = field.getType();
                if (((Class) type).getName().equals(Integer.class.getName())) {
                    field.set(user, Integer.parseInt(value));
                    return;
                }
                field.set(user, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return user;
    }
}
