package StepDefn;

import Payloads.User_Post_Pojo;
import RestOpr.User_Opr;
import com.sun.source.tree.AssertTree;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class User_Post {
    Response response;
    public static User_Post_Pojo UPPayload = new User_Post_Pojo();
    ObjectMapper mapper = new ObjectMapper();

    // String request;
    String WrappedString;

    @Before()
    public void TestData() throws JsonProcessingException {
      /*  Faker fake = new Faker();
        UPPayload.setId(fake.idNumber().hashCode());
        UPPayload.setUsername(fake.name().username());
        UPPayload.setPassword(fake.internet().password());
        UPPayload.setFirstName(fake.name().firstName());
        UPPayload.setLastName(fake.name().lastName());
        UPPayload.setEmail(fake.internet().emailAddress());
        UPPayload.setPhone(fake.phoneNumber().phoneNumber());
        UPPayload.setUserStatus(fake.hashCode());

        request = mapper.writeValueAsString(UPPayload);
        WrappedString = "["+request+"]";*/

        WrappedString= User_Opr.reqforpost();
       // User_Opr.reqforpost();
    }
    @Given("user hits the post request")
    public void user_hits_the_post_request(){
        response = User_Opr.post_opr(WrappedString);
        //System.out.println(response.getBody().asString()); //To print response in console
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PostResponseSchema.json")).log().all();
        System.out.println("Response scheme is also validated");

        String Message=response.then().extract().path("message").toString();
        Assert.assertEquals(Message,"ok");
        System.out.println(Message + "is ok");
        //matchesJsonSchemaInClasspath("PostResponseSchema.json")
    }
    @Then("Response code should be pass")
    public void response_code_should_be() {
        response.then().statusCode(200).log().all();
       // response.then().extract().path("")
    }
}
