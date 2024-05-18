package com.training.islamyapp.pojo.quran;

import com.google.gson.annotations.SerializedName;

public class SurahDetailsItem {

	@SerializedName("sura")
	private String sura;

	@SerializedName("aya")
	private String aya;

	@SerializedName("translation")
	private String translation;

	@SerializedName("id")
	private String id;

	@SerializedName("arabic_text")
	private String arabicText;

	@SerializedName("footnotes")
	private String footnotes;

	public String getSura(){
		return sura;
	}

	public String getAya(){
		return aya;
	}

	public String getTranslation(){
		return translation;
	}

	public String getId(){
		return id;
	}

	public String getArabicText(){
		return arabicText;
	}

	public String getFootnotes(){
		return footnotes;
	}
}