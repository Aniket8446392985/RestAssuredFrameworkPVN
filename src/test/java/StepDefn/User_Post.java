package StepDefn;

import Payloads.User_Post_Pojo;
import RestOpr.User_Opr;
import com.github.javafaker.Faker;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class User_Post {
    Response response;
    public static User_Post_Pojo UPPayload = new User_Post_Pojo();
    ObjectMapper mapper = new ObjectMapper();
    String request;
    String WrappedString;

    @Before()
    public void TestData() throws JsonProcessingException {
        Faker fake = new Faker();
        UPPayload.setId(fake.idNumber().hashCode());
        UPPayload.setUsername(fake.name().username());
        UPPayload.setPassword(fake.internet().password());
        UPPayload.setFirstName(fake.name().firstName());
        UPPayload.setLastName(fake.name().lastName());
        UPPayload.setEmail(fake.internet().emailAddress());
        UPPayload.setPhone(fake.phoneNumber().phoneNumber());
        UPPayload.setUserStatus(fake.hashCode());

        request = mapper.writeValueAsString(UPPayload);
        WrappedString = "["+request+"]";
    }
    @Given("user hits the post request")
    public void user_hits_the_post_request() {
        response = User_Opr.post_opr(WrappedString);
    }
    @Then("Response code should be pass")
    public void response_code_should_be() {
        response.then().statusCode(200).log().all();
    }
}
