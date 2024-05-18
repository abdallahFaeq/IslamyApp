package com.training.islamyapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.training.islamyapp.pojo.prayer_times.MyTimings;
import com.training.islamyapp.pojo.prayer_times.PrayerTimesResponse;
import com.training.islamyapp.pojo.prayer_times.Timings;
import com.training.islamyapp.repositories.PrayerTimesRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTimesViewModel extends ViewModel {
    // declaration
    PrayerTimesRepository repository;
    private MutableLiveData<ArrayList<MyTimings>> times;
    public LiveData<ArrayList<MyTimings>> timesLive;

    public PrayerTimesViewModel(){
        repository = new PrayerTimesRepository();
        times = new MutableLiveData<>();
        timesLive = times;
    }

    public void getPrayerTimes(
            String cit,
            String country,
            int day,
            int method,
            int month,
            int year
    ){
        repository
                .getPrayerTimes(cit, country, method+1, month, year)
                .enqueue(new Callback<PrayerTimesResponse>() {
                    @Override
                    public void onResponse(Call<PrayerTimesResponse> call, Response<PrayerTimesResponse> response) {
                        if (response.isSuccessful()){
                            Timings timings = response.body().getData().get(day - 1).getTimings();
                            ArrayList<MyTimings> myTimings = convertFromTimings(timings);
                            times.setValue(myTimings);
                        }
                    }

                    @Override
                    public void onFailure(Call<PrayerTimesResponse> call, Throwable throwable) {

                    }
                });
    }

    public ArrayList<MyTimings> convertFromTimings(Timings timings) {
        ArrayList<MyTimings> result = new ArrayList<>();
        result.add(new MyTimings(timings.getFajr(), "Fajr","الفجر"));
        result.add(new MyTimings(timings.getSunrise(), "Sunrise","الشروق"));
        result.add(new MyTimings(timings.getDhuhr(), "Duhr","الظهر"));
        result.add(new MyTimings(timings.getAsr(), "Asr","العصر"));
        result.add(new MyTimings(timings.getMaghrib(), "Maghreb","المغرب"));
        result.add(new MyTimings(timings.getIsha(), "Isha","العشاء"));
        return result;
    }
}
