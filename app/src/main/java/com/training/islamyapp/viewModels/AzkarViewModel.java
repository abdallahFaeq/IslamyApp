package com.training.islamyapp.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.training.islamyapp.pojo.azkar.AzkarItem;
import com.training.islamyapp.repositories.AzkarRepository;

import java.util.List;

public class AzkarViewModel extends ViewModel {
    AzkarRepository azkarRepository = new AzkarRepository();

    private MutableLiveData<List<AzkarItem>> azkarStringMutable  = new MutableLiveData<>();
    public LiveData<List<AzkarItem>> azkarStringLive = azkarStringMutable;

    public void getAllAzkar(Context context){
        List<AzkarItem> azkarItems = azkarRepository.getAllAzkar(context);
        azkarStringMutable.postValue(azkarItems);
    }
}
