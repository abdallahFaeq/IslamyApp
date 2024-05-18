package com.training.islamyapp.repositories;

import com.training.islamyapp.network.ApiClient;
import com.training.islamyapp.pojo.prayer_times.PrayerTimesResponse;

import retrofit2.Call;

public class PrayerTimesRepository {

    public Call<PrayerTimesResponse> getPrayerTimes(
            String city,
            String country,
            int method,
            int month,
            int year
    ){
        ApiClient.getPrayerTimesInstance();
        return ApiClient.getPrayerTimesServices().getPrayerTimes(city, country, method, month, year);
    }
}
