package com.training.islamyapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.training.islamyapp.pojo.radio.RadioResponse;
import com.training.islamyapp.pojo.radio.RadiosItem;
import com.training.islamyapp.repositories.RadioRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadioViewModel extends ViewModel {

    private MutableLiveData<List<RadiosItem>> radioItems = new MutableLiveData<>();
    public LiveData<List<RadiosItem>> radioItemsLive = radioItems;
    RadioRepository radioRepository = new RadioRepository();

    public void getRadioChannels(){

        radioRepository.getRadioChannels()
                .enqueue(new Callback<RadioResponse>() {
                    @Override
                    public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {
                        if (response.isSuccessful()){
                            radioItems.postValue(response.body().getRadios());
                        }
                    }

                    @Override
                    public void onFailure(Call<RadioResponse> call, Throwable throwable) {

                    }
                });

    }
}
