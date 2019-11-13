package com.example.college_navigator_10.Data_Management_Colleges;


import com.fasterxml.jackson.annotation.JsonProperty;

public class College {

	@JsonProperty( "school.name")public String schoolname;

	@JsonProperty("school.zip")public String zip;

	@JsonProperty("school.state")public String state;

	@JsonProperty("id")String id;
	@JsonProperty("school.school_url")public String url;

	@JsonProperty("school.city")String schoolcity;

	@JsonProperty("latest.cost.tuition.out_of_state") public String tuition_outstate;

	@JsonProperty("latest.cost.tuition.in_state")public String instate;
	@JsonProperty("latest.admissions.sat_scores.25th_percentile.critical_reading")String Readingpercentile_25;
	@JsonProperty("latest.admissions.sat_scores.25th_percentile.math")String Mathpercentile_25;
	@JsonProperty("latest.admissions.sat_scores.75th_percentile.critical_reading")public String Readingpercentile_75;
	@JsonProperty("latest.admissions.sat_scores.75th_percentile.math")public String Mathpercentile_75;

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
}
