package com.training.islamyapp.viewModels.quran;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.training.islamyapp.pojo.quran.SurahDetailsResponse;
import com.training.islamyapp.repositories.quran.SuraDetailsRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuraDetailsViewModel extends ViewModel {

    private SuraDetailsRepository suraDetailsRepository = new SuraDetailsRepository();

    private MutableLiveData<SurahDetailsResponse> suraDetailsMutableLiveData = new MutableLiveData<>();
    public LiveData<SurahDetailsResponse> suraDetailsLiveData = suraDetailsMutableLiveData;


    public void getSuraDetails(String lan, String suraNumber){
        suraDetailsRepository.getSuraDetails(lan, suraNumber)
                .enqueue(new Callback<SurahDetailsResponse>() {
                    @Override
                    public void onResponse(Call<SurahDetailsResponse> call, Response<SurahDetailsResponse> response) {
                        if (response.isSuccessful()){
                            suraDetailsMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<SurahDetailsResponse> call, Throwable throwable) {

                    }
                });
    }
}
