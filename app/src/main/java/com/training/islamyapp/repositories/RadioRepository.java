package com.training.islamyapp.repositories;

import com.training.islamyapp.network.ApiClient;
import com.training.islamyapp.pojo.radio.RadioResponse;

import retrofit2.Call;

public class RadioRepository {

    public Call<RadioResponse> getRadioChannels(){
        ApiClient.getRadioInstance();
        return ApiClient.getRadioServices().getRadioChannels();
    }

}
