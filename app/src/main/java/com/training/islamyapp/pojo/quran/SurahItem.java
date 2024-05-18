package com.training.islamyapp.pojo.quran;

import com.google.gson.annotations.SerializedName;

public class SurahItem {

	@SerializedName("number")
	private int number;

	@SerializedName("englishName")
	private String englishName;

	@SerializedName("numberOfAyahs")
	private int numberOfAyahs;

	@SerializedName("revelationType")
	private String revelationType;

	@SerializedName("name")
	private String name;

	@SerializedName("englishNameTranslation")
	private String englishNameTranslation;

	public int getNumber(){
		return number;
	}

	public String getEnglishName(){
		return englishName;
	}

	public int getNumberOfAyahs(){
		return numberOfAyahs;
	}

	public String getRevelationType(){
		return revelationType;
	}

	public String getName(){
		return name;
	}

	public String getEnglishNameTranslation(){
		return englishNameTranslation;
	}
}