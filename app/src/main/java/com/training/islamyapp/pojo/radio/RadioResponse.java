package com.training.islamyapp.pojo.radio;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RadioResponse{

	@SerializedName("radios")
	private List<RadiosItem> radios;

	public List<RadiosItem> getRadios(){
		return radios;
	}
}