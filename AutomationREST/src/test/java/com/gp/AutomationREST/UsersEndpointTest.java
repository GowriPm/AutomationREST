package com.gp.AutomationREST;

import static com.gp.AutomationREST.Constants.*;
import com.gp.AutomationREST.ContractClasses.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.util.List;

/**
 * @author GP
 * This class has the tests for testing Users endpoint
 */
public class UsersEndpointTest {
	
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();

    @Test (priority=1)
    //Test to make sure user endpoint is a valid url for sanity check
    public void getUsersTest() {
        	given(requestSpecification)
            	.get(USERS_ENDPOINT)
            	.then().statusCode(200);
    }
    
    @Test (priority=2)
    @Parameters({"name", "userName", "email"})
    //Test users endpoint for POST
    public void postUserTest(String name, String userName, String email) {
    	
    	//Create a User object
    	User user = new User(){{
      	     setName(name);
    	     setUserName(userName);
    	     setEmail(email);
    	 }}; 
    	
    	 //Post the request and check the status code
    	given(requestSpecification).
    		body(user).
    	when().
    		post(USERS_ENDPOINT).
    	then().
    		assertThat().statusCode(201); //user created
    	
    	//Get and confirm the user is actually created.
	   	 List<User> users = given(requestSpecification)
	   			 	.queryParam("username", userName)
			        .when()
			        .get(USERS_ENDPOINT)
			        .then().log().all()
			        .extract()
			        .body()
			        .jsonPath().getList(".", User.class);
	   	 
	   	 //<temp> search by username appear to fail </temp>
	   	 
	   	 Assert.assertTrue(users.size() > 0, "Is users collection has any item in the response?");
		 
		 Assert.assertEquals(users.get(0).getUserName(),userName);    
    }
    
    @Test (priority=3)
    @Parameters("name")
    //Test users endpoint for GET
    public void getUserTest(String name) {   	 
    	
    	 List<User> users = given(requestSpecification)
    			 	.queryParam("name", name)
    		        .when()
    		        .get(USERS_ENDPOINT)
    		        .then().log().all()
    		        .extract()
    		        .body()
    		        .jsonPath().getList(".", User.class);
    	 
    	 Assert.assertEquals(users.get(0).getName(),name);
    }
    
    @Test (priority=4)
    @Parameters("name")
    //Test users endpoint for DELETE
    public void deleteUserTest(String name) {   	 
    	
    	List<User> users;
    	
    	//Get the user created in POST test by name
    	 users = given(requestSpecification)
    			 	.queryParam("name", name)
    		        .when()
    		        .get(USERS_ENDPOINT)
    		        .then().log().all()
    		        .extract()
    		        .body()
    		        .jsonPath().getList(".", User.class);
    	 
    	 Assert.assertEquals(users.get(0).getName(),name); 
    	 
    	 //get the id of this user and delete by id.
    	 
    	 long id = users.get(0).getId();
    	 
    	 
         given(requestSpecification)
         .queryParam("id", id) 
         .when()
         .delete(USERS_ENDPOINT)
         .then()
         .assertThat().statusCode(200);
         
         
         //make sure the user with this id is deleted
    	 users = given(requestSpecification)
 			 	.queryParam("id", id)
 		        .when()
 		        .get(USERS_ENDPOINT)
 		        .then().log().all()
 		        .extract()
 		        .body()
 		        .jsonPath().getList(".", User.class);
 	 
    	 Assert.assertEquals(users.size(),0); 
    }
    
    //negative tests - both should return bad request
    
    //post invalid user request    
    @Test (priority=5)

    public void postInvalidUserTest() {
    	
    	//Create an invalid object and post it as a user request. This request is
    	//expected to return a Bad Request http code
    	NegativeTest invalidUser = new NegativeTest(){{
      	     setNoPropA(123);
    	     setNoPropB("InvalidInput");
    	 }}; 
    	
    	 //Post the request and check the status code
    	given(requestSpecification).
    		body(invalidUser).
    	when().
    		post(USERS_ENDPOINT).
    	then().
    		assertThat().statusCode(400); //Bad Request
  
    }
    
    //get user by invalid query parameter name
    @Test (priority=6)
    public void getInvalidParamTest() {   	 
    	
    	//Make a get request with an invalid query paramter. This request is
    	//expected to return a Bad Request http code
    	 given(requestSpecification)
    			 	.queryParam("Invalid", "InvalidRequest")
    		        .when()
    		        .get(USERS_ENDPOINT)
    		        .then().log().all()
    		        .assertThat().statusCode(400);
    }
}    
