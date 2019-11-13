package com.example.college_navigator_10.Data_Management_Colleges;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MetaData {
	@JsonProperty("total")
	public String total;
	
	@JsonProperty("page")
	public String page;
	
	@JsonProperty("per_page")
	public String per_page;
	
	int getPage_int() {
		return Integer.parseInt(page);
	}
	int getTotal_int() {
		return Integer.parseInt(total);
	}
	
	int getPerPage_int() {
		return Integer.parseInt(per_page);
	}


}
