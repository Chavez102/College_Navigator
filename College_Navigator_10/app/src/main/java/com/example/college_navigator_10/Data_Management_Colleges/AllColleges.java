package com.example.college_navigator_10.Data_Management_Colleges;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AllColleges {
	@JsonProperty("metadata") public MetaData mymetadata;
	
	@JsonProperty("results") public College[] colleges;
	

}
