package com.training.islamyapp.repositories.quran;

import com.training.islamyapp.network.ApiClient;
import com.training.islamyapp.pojo.quran.SurahResponse;

import retrofit2.Call;

public class SurahRepository {

    public Call<SurahResponse> getSurah(){

        ApiClient.getInstanceForSurah();
        return ApiClient.getSurahServices().getSurah();
    }

}
