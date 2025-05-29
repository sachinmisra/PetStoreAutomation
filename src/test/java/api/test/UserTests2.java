package api.test;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {

    Faker faker;
    User userPayload;
    String createdUserId;

    public Logger logger;

    @BeforeClass
    public void setup()
    {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId("125");            //String.valueOf(faker.idNumber().hashCode())
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = LogManager.getLogger(this.getClass());

    }

    @Test (priority = 1)
    public void testPostUser()
    {
        logger.info("******** Creating User **********");

        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();

        // Extract the id from the response
        createdUserId = response.jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 201);

        logger.info("******** User is created **********");
    }

    @Test (priority = 2)
    public void testGetUserById()
    {
        logger.info("******** Reading User info **********");

        // Use the id obtained from the POST response
        Response response = UserEndPoints2.readUser(createdUserId);
        response.then().log().all();
        //response.then().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200);

        // Optionally, verify that the retrieved user matches the created user
        String retrievedUsername = response.jsonPath().getString("username");
        Assert.assertEquals(retrievedUsername, userPayload.getUsername());

        logger.info("******** User info is displayed **********");
    }

    @Test (priority = 3)
    public void testUpdateUserById()
    {
        logger.info("******** Updating User **********");

        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());


        // Use the id obtained from the POST response
        Response response = UserEndPoints2.updateUser(createdUserId, userPayload);
        response.then().log().all();
        //response.then().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("******** User info is updated **********");

        // Optionally, verify that the retrieved user matches the updated user
        String retrievedFirstname = response.jsonPath().getString("firstName");
        Assert.assertEquals(retrievedFirstname, userPayload.getFirstName());

    }

    @Test (priority = 4)
    public void testDeleteUserById()
    {
        logger.info("******** Deleting User **********");

        Response response = UserEndPoints2.deleteUser(createdUserId);
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("******** User is deleted **********");
    }


}
