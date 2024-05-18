package com.training.islamyapp.pojo.radio;

import com.google.gson.annotations.SerializedName;

public class RadiosItem{

	@SerializedName("name")
	private String name;

	@SerializedName("radio_url")
	private String radioUrl;

	public String getName(){
		return name;
	}

	public String getRadioUrl(){
		return radioUrl;
	}
}