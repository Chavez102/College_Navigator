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

	@JsonProperty("latest.cost.tuition.in_state")public String instate_tuition;
	@JsonProperty("latest.admissions.sat_scores.25th_percentile.critical_reading")String Readingpercentile_25;
	@JsonProperty("latest.admissions.sat_scores.25th_percentile.math")String mathpercentile_25;
	@JsonProperty("latest.admissions.sat_scores.75th_percentile.critical_reading")public String readingpercentile_75;
	@JsonProperty("latest.admissions.sat_scores.75th_percentile.math")public String mathpercentile_75;

	public String getSchoolname() {
		return schoolname;
	}

	public String getZip() {
		return zip;
	}

	public String getState() {
		return state;
	}

	public String getUrl() {
		return url;
	}

	public String getSchoolcity() {
		return schoolcity;
	}

	public String getTuition_outstate() {
		return tuition_outstate;
	}

	public String getinstate_tuition() {
		return instate_tuition;
	}

	public String getReadingpercentile_25() {
		return Readingpercentile_25;
	}

	public String getMathpercentile_25() {
		return mathpercentile_25;
	}

	public String getReadingpercentile_75() {
		return readingpercentile_75;
	}

	public String getMathpercentile_75() {
		return mathpercentile_75;
	}

	public String getId() {
		return id;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSchoolcity(String schoolcity) {
		this.schoolcity = schoolcity;
	}

	public void setTuition_outstate(String tuition_outstate) {
		this.tuition_outstate = tuition_outstate;
	}

	public void setinstate_tuition(String instate_tuition) {
		this.instate_tuition = instate_tuition;
	}

	public void setReadingpercentile_25(String readingpercentile_25) {
		Readingpercentile_25 = readingpercentile_25;
	}

	public void setMathpercentile_25(String mathpercentile_25) {
		mathpercentile_25 = mathpercentile_25;
	}

	public void setReadingpercentile_75(String readingpercentile_75) {
		readingpercentile_75 = readingpercentile_75;
	}

	public void setMathpercentile_75(String mathpercentile_75) {
		mathpercentile_75 = mathpercentile_75;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
}
