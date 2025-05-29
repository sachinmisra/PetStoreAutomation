package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


//created for CRUD - create, return, update, delete operations
public class UserEndPoints {

    public static Response createUser(User payload){

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(Routes.post_url);

        return response;
    }

    public static Response readUser(String userId){

        Response response = given()
                .log().all()
                .pathParam("userid", userId)
                .when()
                .get(Routes.get_url);

        return response;
    }

    public static Response updateUser(String userId, User payload){

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userid", userId)
                .body(payload)
                .when()
                .put(Routes.update_url);

        return response;
    }

    public static Response deleteUser(String userId){

        Response response = given()
                .pathParam("userid", userId)
                .when()
                .delete(Routes.delete_url);

        return response;
    }


}
