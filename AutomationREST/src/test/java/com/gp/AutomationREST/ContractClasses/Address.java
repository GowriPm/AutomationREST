package com.gp.AutomationREST.ContractClasses;

public class Address {

	private Geo geo;
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	
	public Geo getGeo() {
		return this.geo;
	}
	
	public void setGeo(Geo geo) {
		this.geo = geo;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getSuite() {
		return this.suite;
	}
	
	public void setSuite(String suite) {
		this.suite = suite;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZipcode() {
		return this.zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
		
	/*
	 *{
            "street": "Kattie Turnpike",
            "suite": "Suite 198",
            "city": "Lebsackbury",
            "zipcode": "31428-2261",
            "geo": {
                "lat": "-38.2386",
                "lng": "57.2232"
            }
	 */
}
