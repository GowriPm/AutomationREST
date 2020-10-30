package com.gp.AutomationREST.ContractClasses;

public class Company {
	
	private String name;
	private String catchPhrase;
	private String bs;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCatchPhrase() {
		return this.catchPhrase;
	}
	
	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}
	
	public String getBs() {
		return this.bs;
	}
	
	public void setBs(String bs) {
		this.bs = bs;
	}
	
	
	/*
	         "company": {
            "name": "Hoeger LLC",
            "catchPhrase": "Centralized empowering task-force",
            "bs": "target end-to-end models"
        }
	 */

}
