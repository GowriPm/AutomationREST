package com.gp.AutomationREST.ContractClasses;

public class Comment {
	
	private long id;
	private long postId;
	private String name;
	private String email;
	private String body; 
	
	public long getid() {
		return id;
	}
	   
	public void setid(long id) {
		this.id = id;
	}
	
	public long getpostId() {
		return postId;
	}
	   
	public void setpostId(long postId) {
		this.postId = postId;
	}
	
	public String getname() {
		return name;
	}
	   
	public void setname(String name) {
		this.name = name;
	}
	
	public String getemail() {
		return email;
	}
	   
	public void setbody(String body) {
		this.body = body;
	}
	
	public String getbody() {
		return body;
	}
	   
	public void setemail(String email) {
		this.email = email;
	}
	
	
/*
    {
        "postId": 2,
        "id": 9,
        "name": "serkan sam serdar",
        "email": "serkanserdar@gmail.com",
        "body": "Atlanta is great Area"
    }
 */
	
}
