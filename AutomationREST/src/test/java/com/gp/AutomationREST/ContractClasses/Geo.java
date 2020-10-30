package com.gp.AutomationREST.ContractClasses;

public class Geo {

	private String lat;
	private String lng;
	
	public String getlat() {
		return this.lat;
	}
	
	public void setlat(String lat) {
		this.lat = lat;
	}
	
	public String getlng() {
		return this.lng;
	}
	
	public void setlng(String lng) {
		this.lng = lng;
	}
	/*
	 *            "geo": {
                "lat": "-38.2386",
                "lng": "57.2232"
            }
	 */
}
