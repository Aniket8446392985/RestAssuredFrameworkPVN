package RestOpr;

import Payloads.PutTestPojo;
import Payloads.User_Post_Pojo;
import Payloads.User_Put_Pojo;
import URI.UserURI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import com.github.javafaker.Faker;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class User_Opr {
    public static Response post_opr(String Payloads)
    {
        Response response =
                given().contentType(JSON).accept(JSON).
                body(Payloads).
                when().post(UserURI.Base_URL+UserURI.PostUrl);
        return response;
    }
    public static String reqforpost () throws JsonProcessingException {
        String request;
        String WrappedString;

        ObjectMapper mapper = new ObjectMapper();

        User_Post_Pojo UPPayload=new User_Post_Pojo();

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

        System.out.println(WrappedString);

        return WrappedString;

        //return UPPayload;
    }


    public static Response Get_opr(String USERNAME){
        Response response =given().contentType(JSON).accept(JSON).
                pathParams("username",USERNAME).log().all().
                when().get(UserURI.Base_URL+UserURI.getUrl);
        return response;
    }

    public static Response  put_opr(User_Put_Pojo Payload,String PUTUSERNAME)
    {
        Response response= given().contentType(JSON).accept(JSON).
                pathParams("PutUserName",PUTUSERNAME).
                body(Payload).log().all().
                when().put(UserURI.Base_URL+UserURI.PutUrl);
        return response;
    }

    public static Response PutTest(PutTestPojo Payload)
    {
       Response response = given().contentType(JSON).accept(JSON).
                body(Payload).
                when().put(UserURI.Base_URL+UserURI.PutUrl);
        return response;
    }







}
