package RestOpr;

import URI.UserURI;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class User_Opr {
    public static Response post_opr(String Payloads)
    {
        Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(Payloads).log().all().
                when().post(UserURI.Base_URL+UserURI.PostUrl);
        return response;
    }

    public static Response Get_opr(String USERNAME){
        Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON).
                pathParams("username",USERNAME).log().all().
                when().get(UserURI.Base_URL+UserURI.getUrl);
        return response;
    }





}
