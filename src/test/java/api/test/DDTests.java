package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    String createdUserId;


    @Test (priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostuser(String userID, String userName, String fname, String lname, String email, String pwd, String ph)
    {

        User userPayload = new User();

        userPayload.setId(userID);
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(email);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 201);

    }

    //only for edcucation. this will not work as our api take id instead of username
    //@Test (priority = 1, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName)
    {

        Response response = UserEndPoints.deleteUser(createdUserId);
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
