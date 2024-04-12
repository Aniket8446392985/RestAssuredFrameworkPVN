package StepDefn;

import Payloads.User_Put_Pojo;
import RestOpr.User_Opr;
import URI.UserURI;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;

public class Put_User
{
    Response response;
    User_Put_Pojo userPutPojo= new User_Put_Pojo();
    String lastname ="Dhawale";
    @Before
    public void before_put()
    {
        userPutPojo.setLastname(lastname);
    }
    @Given("User hits Put request")
    public void user_hits_put_request()
    {
        response=User_Opr.put_opr(userPutPojo,"kent.watsica");
        Assert.assertNotNull(response);
    }
    @When("success response is received")
    public void success_response_is_received()
    {
      Assert.assertEquals(200, response.getStatusCode());
    }
    @Then("Last name is validated")
    public void last_name_is_validated()
    {
        Assert.assertEquals(response.then().extract().path("lastname"),lastname);
    }



}
