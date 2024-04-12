package StepDefn;

import RestOpr.User_Opr;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;

public class GetUser {

    Response response;
    @Given("user hits get request")
    public void user_hits_get_request() {
       response= User_Opr.Get_opr("tommie.green");
       Assert.assertNotNull(response);
    }
    @When("success response received")
    public void success_response_received() {
        Assert.assertEquals( 200, response.getStatusCode());
    }
    @Then("first name is string")
    public void first_name_is_string() {
        Assert.assertTrue(response.then().extract().path("firstName")instanceof String);
    }
}
