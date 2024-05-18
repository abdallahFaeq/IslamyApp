package com.training.islamyapp.pojo.quran;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SurahDetailsResponse{

	@SerializedName("result")
	private List<SurahDetailsItem> result;

	public List<SurahDetailsItem> getResult(){
		return result;
	}
}