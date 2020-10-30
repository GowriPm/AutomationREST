package com.gp.AutomationREST.ContractClasses;

public class Post {
	
	private String id;
	private String userId;
	private int postId;
	private String title;
	private String body;
		
	public String getId() {
		return id;
	}
	   
	public void setId(String id) {
		this.id = id;
	}
	
	public String getuserId() {
		return userId;
	}
	   
	public void setpostId(int postId) {
		this.postId = postId;
	}
	
	public int getpostId() {
		return postId;
	}
	   
	public void setuserId(String userId) {
		this.userId = userId;
	}
	
	public String gettitle() {
		return title;
	}
	   
	public void settitle(String title) {
		this.title = title;
	}
	
	public String getbody() {
		return body;
	}
	   
	public void setbody(String body) {
		this.body = body;
	}
	
	/*
	 {
        "userId": 1,
        "id": 2,
        "title": "blah blah",
        "body": "test updated"
    }
	 */
}
