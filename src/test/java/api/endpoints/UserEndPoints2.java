package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;


//created for CRUD - create, return, update, delete operations
public class UserEndPoints2 {


    //method created for getting urls from properties file
    static ResourceBundle getUrl()
    {
        ResourceBundle routes = ResourceBundle.getBundle("routes");     //load properties file
        return routes;

    }

    public static Response createUser(User payload){

        String post_url = getUrl().getString("post_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(post_url);

        return response;
    }

    public static Response readUser(String userId){

        String get_url = getUrl().getString("get_url");

        Response response = given()
                .log().all()
                .pathParam("userid", userId)
                .when()
                .get(get_url);

        return response;
    }

    public static Response updateUser(String userId, User payload){

        String update_url = getUrl().getString("update_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userid", userId)
                .body(payload)
                .when()
                .put(update_url);

        return response;
    }

    public static Response deleteUser(String userId){

        String delete_url = getUrl().getString("delete_url");

        Response response = given()
                .pathParam("userid", userId)
                .when()
                .delete(delete_url);

        return response;
    }


}
