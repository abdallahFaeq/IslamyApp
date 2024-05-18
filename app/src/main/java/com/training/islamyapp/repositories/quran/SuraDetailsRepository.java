package com.training.islamyapp.repositories.quran;

import com.training.islamyapp.network.ApiClient;
import com.training.islamyapp.pojo.quran.SurahDetailsResponse;

import retrofit2.Call;

public class SuraDetailsRepository {

    public Call<SurahDetailsResponse> getSuraDetails(String lan, String surahNumber){
        ApiClient.getInstanceForSurahDetails();
        return ApiClient.getSurahDetailsServices().getSurahDetails(lan, surahNumber);
    }
}
