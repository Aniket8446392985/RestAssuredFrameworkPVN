package StepDefn;

import Payloads.PutTestPojo;
import RestOpr.User_Opr;
import io.cucumber.java.en.*;
import io.cucumber.messages.types.Background;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

public class PutTest {
    PutTestPojo ptp=new PutTestPojo();
    Response response;
    @Given("User create the request")
    public void requestCreation()
    {
        ptp.setLastName("Test");
    }
    @Given("User create the request and hits the API")
    public void user_create_the_request_and_hits_the_api()
    {
        response = User_Opr.PutTest(ptp);
        //response.then().log().all();
        Assert.assertNotNull(response);
    }
    @Then("Schema will be validated")
    public void schema_will_be_validated()
    {
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PutRequest.feature"));
    }

}
