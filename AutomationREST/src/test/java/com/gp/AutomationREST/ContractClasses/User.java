package com.gp.AutomationREST.ContractClasses;

public class User {

	
	private long id;
	private String name;
	private String username;
	private String email;
	private Address address;
	private String phone;
	private String website;
	private Company company;
	
	
	public long getId() {
		return id;
	}
	   
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	   
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return username;
	}
	   
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	   
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}
	   
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	   
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getWebsite() {
		return website;
	}
	   
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public Company getCompany() {
		return company;
	}
	   
	public void setCompany(Company company) {
		this.company = company;
	}
	
	/*
	  {
        "id": 10,
        "name": "Clementina DuBuque",
        "username": "Moriah.Stanton",
        "email": "Rey.Padberg@karina.biz",
        "address": {
            "street": "Kattie Turnpike",
            "suite": "Suite 198",
            "city": "Lebsackbury",
            "zipcode": "31428-2261",
            "geo": {
                "lat": "-38.2386",
                "lng": "57.2232"
            }
        },
        "phone": "024-648-3804",
        "website": "ambrose.net",
        "company": {
            "name": "Hoeger LLC",
            "catchPhrase": "Centralized empowering task-force",
            "bs": "target end-to-end models"
        }
    }
	 */
}
