package com.gp.AutomationREST;

import static com.gp.AutomationREST.Constants.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gp.AutomationREST.ContractClasses.NegativeTest;
import com.gp.AutomationREST.ContractClasses.Post;
import static io.restassured.RestAssured.given;
import java.util.List;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * @author GP
 * This class has the tests for testing Posts endpoint
 */
public class PostsEndpointTest {
	
	RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();
	
	@Test (priority=1)
    //Test to make sure posts endpoint is a valid url for sanity check
    public void getPostsTestcheck() {
        	given(requestSpecification)
            	.get(POSTS_ENDPOINT)
            	.then().statusCode(200);
    }
	
	@Test (priority=2)
    @Parameters({"id", "userId", "title", "body"})
    //Test posts endpoint for POST
    public void postPostsTest(String id, String userId, String title, String body) {
    	
    	//Create a Post object
    	Post post = new Post(){{
      	     //setId(id);  //id should be assigned on the server, but the API is allowing the Id sent from the client. Is this by design? 
    	     setuserId(userId);
    	     settitle(title);
    	     setbody(body);
    	 }}; 
    	
    	 //Post the request and check the status code
    	given(requestSpecification).
    		body(post).
    	when().
    		post(POSTS_ENDPOINT).
    	then().
    		assertThat().statusCode(201); //Post created 
	 		 		  
    }
	
	@Test (priority=3)
	@Parameters("userId")
    //Test posts endpoint for GET
    public void getPostsTest(String userId) {   	 
    	
    	//Get and confirm the Post is actually created.
	   	 List<Post> posts = given(requestSpecification)
	   			 	.queryParam("userid", userId)
			        .when()
			        .get(POSTS_ENDPOINT)
			        .then().log().all()
			        .extract()
			        .body()
			        .jsonPath().getList(".", Post.class);

	   	 Assert.assertTrue(posts.size() > 0, "Is posts collection has any item in the response?");
		 
	 	 Assert.assertEquals(posts.get(0).getuserId(),userId);
    }
		
	 @Test (priority=4)
	 @Parameters("id")
	    //Test posts endpoint for DELETE
	    public void deletePostTest(String id) {   	 
	    	
	    	List<Post> posts;
	    	
	    	//Get the post created in POST test by name
	    	posts = given(requestSpecification)
	    			 	.queryParam("id", id)
	    		        .when()
	    		        .get(POSTS_ENDPOINT)
	    		        .then().log().all()
	    		        .extract()
	    		        .body()
	    		        .jsonPath().getList(".", Post.class);
	    	 
	    	 Assert.assertEquals(posts.get(0).getId(),id); 
	    	 
	    	 //delete by id.	    	 
    	 
	         given(requestSpecification)
	         .queryParam("id", id) 
	         .when()
	         .delete(POSTS_ENDPOINT)
	         .then()
	         .assertThat().statusCode(200);
	         
	         
	         //make sure the user with this id is deleted
	         posts = given(requestSpecification)
	 			 	.queryParam("id", id)
	 		        .when()
	 		        .get(POSTS_ENDPOINT)
	 		        .then().log().all()
	 		        .extract()
	 		        .body()
	 		        .jsonPath().getList(".", Post.class);
	 	 
	    	 Assert.assertEquals(posts.size(),0); 
	    }
	 
	    //negative tests - both should return bad request
	    
	    //post invalid posts request    
	    @Test (priority=5)
	    public void postInvalidPostTest() {
	    	
	    	//Create an invalid object and post it as a user request. This request is
	    	//expected to return a Bad Request http code
	    	NegativeTest invalidPost = new NegativeTest(){{
	      	     setNoPropA(123);
	    	     setNoPropB("InvalidInput");
	    	 }}; 
	    	
	    	 //Post the request and check the status code
	    	given(requestSpecification).
	    		body(invalidPost).
	    	when().
	    		post(POSTS_ENDPOINT).
	    	then().
	    		assertThat().statusCode(400); //Bad Request	  
	    }
	    
	    //get post by invalid query parameter name
	    @Test (priority=6)
	    public void getInvalidParamTest() {   	 
	    	
	    	//Make a get request with an invalid query paramter. This request is
	    	//expected to return a Bad Request http code
	    	 given(requestSpecification)
	    			 	.queryParam("Invalid", "InvalidRequest")
	    		        .when()
	    		        .get(POSTS_ENDPOINT)
	    		        .then().log().all()
	    		        .assertThat().statusCode(400);
	    }
}
