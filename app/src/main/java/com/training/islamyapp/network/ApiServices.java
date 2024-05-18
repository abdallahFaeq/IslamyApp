package com.training.islamyapp.network;

import com.training.islamyapp.pojo.prayer_times.PrayerTimesResponse;
import com.training.islamyapp.pojo.quran.SurahDetailsResponse;
import com.training.islamyapp.pojo.quran.SurahResponse;
import com.training.islamyapp.pojo.radio.RadioResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("surah")
    public Call<SurahResponse> getSurah();

    @GET("sura/{translation_key}/{sura_number}")
    public Call<SurahDetailsResponse> getSurahDetails(
            @Path("translation_key") String language,
            @Path("sura_number") String surahNumber
    );

    @GET("radios/radio_arabic.json")
    Call<RadioResponse> getRadioChannels();

    @GET("calendarByCity")
    Call<PrayerTimesResponse> getPrayerTimes(
        @Query("city") String city,
        @Query("country") String country,
        @Query("method") int method,
        @Query("month") int month,
        @Query("year") int year
    );
}
