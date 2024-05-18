package com.training.islamyapp.pojo.prayer_times;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("latitude")
	private Object latitude;

	@SerializedName("longitude")
	private Object longitude;

	public Object getLatitude(){
		return latitude;
	}

	public Object getLongitude(){
		return longitude;
	}
}