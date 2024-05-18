package com.training.islamyapp.viewModels.quran;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.training.islamyapp.pojo.quran.SurahResponse;
import com.training.islamyapp.repositories.quran.SurahRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahViewModel extends ViewModel {

    private SurahRepository surahRepository = new SurahRepository();

    private MutableLiveData<SurahResponse> responseSurahLiveData = new MutableLiveData();
    public LiveData<SurahResponse> _responseSurahLiveData = responseSurahLiveData;

    public void getSurahResponse(){
        surahRepository
                .getSurah()
                .enqueue(new Callback<SurahResponse>() {
                    @Override
                    public void onResponse(Call<SurahResponse> call, Response<SurahResponse> response) {
                        if (response.isSuccessful()){
                            responseSurahLiveData.postValue(response.body());
                            Log.e("response", "onResponse: ");
                        }else {
                            Log.e("response", " response but without data: ");
                        }
                    }

                    @Override
                    public void onFailure(Call<SurahResponse> call, Throwable throwable) {
                        Log.e("faulure", "onFailure: ");
                    }
                });
    }
}
