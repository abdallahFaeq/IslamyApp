package com.training.islamyapp.pojo.quran;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurahResponse {

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<SurahItem> data;

	@SerializedName("status")
	private String status;

	public int getCode(){
		return code;
	}

	public List<SurahItem> getData(){
		return data;
	}

	public String getStatus(){
		return status;
	}
}