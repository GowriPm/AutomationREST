package com.gp.AutomationREST;

import static com.gp.AutomationREST.Constants.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.gp.AutomationREST.ContractClasses.Comment;
import com.gp.AutomationREST.ContractClasses.NegativeTest;

import static io.restassured.RestAssured.given;
import java.util.List;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * @author GP
 * This class has the tests for testing Comments endpoint
 */
public class CommentsEndpointTest {
	
	RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();
	
	@Test (priority=1)
    //Test to make sure posts endpoint is a valid url for sanity check
    public void getPostsTestcheck() {
        	given(requestSpecification)
            	.get(COMMENTS_ENDPOINT)
            	.then().statusCode(200);
    }
	
	@Test (priority=2)
	@Parameters({"id", "postId", "name","email","body"})
    //Test comments endpoint for POST
    public void postCommentsTest(long id, long postId, String name, String email, String body) {
		
    	//Create a Comment object
    	Comment comment = new Comment(){{
    		//setid(id); //id should be assigned on the server, but the API is allowing the Id sent from the client. Is this by design? 
      	    setpostId(postId);  
    	    setname(name);
    	    setemail("TestPost-GP");
    	    setbody("well said");
    	 }}; 
    	
    	 //Post the request and check the status code
    	given(requestSpecification).
    		body(comment).
    	when().
    		post(COMMENTS_ENDPOINT).
    	then().
    		assertThat().statusCode(201); //Comment created 
	 		 		  
    }
	
	@Test (priority=3)
	@Parameters("id")
    //Test comments endpoint for GET
    public void getCommentsTest(long id) {   	 
    	
    	//Get and confirm the Post is actually created.
	   	 List<Comment> comments = given(requestSpecification)
	   			 	.queryParam("id", id)
			        .when()
			        .get(COMMENTS_ENDPOINT)
			        .then().log().all()
			        .extract()
			        .body()
			        .jsonPath().getList(".", Comment.class);
		 
	 	 Assert.assertEquals(comments.get(0).getid(),id);
    }
		
	 @Test (priority=4)
	 @Parameters("id")
	    //Test comments endpoint for DELETE
	    public void deleteCommentTest(long id) {   	 
	    	
	    	List<Comment> comments;
	    	
	    	//Get the comment created in POST test by name
	    	comments = given(requestSpecification)
	    			 	.queryParam("id", id)
	    		        .when()
	    		        .get(COMMENTS_ENDPOINT)
	    		        .then().log().all()
	    		        .extract()
	    		        .body()
	    		        .jsonPath().getList(".", Comment.class);
	    	 
	    	 Assert.assertEquals(comments.get(0).getid(),id); 
	    	 
	    	 //delete by id.	    	 
    	 
	         given(requestSpecification)
	         .queryParam("id", id) 
	         .when()
	         .delete(COMMENTS_ENDPOINT)
	         .then()
	         .assertThat().statusCode(200);
	         
	         
	         //make sure the user with this id is deleted
	         comments = given(requestSpecification)
	 			 	.queryParam("id", id)
	 		        .when()
	 		        .get(COMMENTS_ENDPOINT)
	 		        .then().log().all()
	 		        .extract()
	 		        .body()
	 		        .jsonPath().getList(".", Comment.class);
	 	 
	    	 Assert.assertEquals(comments.size(),0); 
	    }
	 
	    //negative tests - both should return bad request
	    
	    //post invalid comments request    
	    @Test (priority=5)
	    public void postInvalidCommentTest() {
	    	
	    	//Create an invalid object and post it as a comment request. This request is
	    	//expected to return a Bad Request http code
	    	NegativeTest invalidComment = new NegativeTest(){{
	      	     setNoPropA(123);
	    	     setNoPropB("InvalidInput");
	    	 }}; 
	    	
	    	 //Post the request and check the status code
	    	given(requestSpecification).
	    		body(invalidComment).
	    	when().
	    		post(COMMENTS_ENDPOINT).
	    	then().
	    		assertThat().statusCode(400); //Bad Request	  
	    }
	    
	    //get comment by invalid query parameter name
	    @Test (priority=6)
	    public void getInvalidParamTest() {   	 
	    	
	    	//Make a get request with an invalid query paramter. This request is
	    	//expected to return a Bad Request http code
	    	 given(requestSpecification)
	    			 	.queryParam("Invalid", "InvalidRequest")
	    		        .when()
	    		        .get(COMMENTS_ENDPOINT)
	    		        .then().log().all()
	    		        .assertThat().statusCode(400);
	    }
}