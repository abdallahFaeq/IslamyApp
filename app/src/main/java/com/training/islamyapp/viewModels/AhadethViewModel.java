package com.training.islamyapp.viewModels;

import androidx.lifecycle.ViewModel;

import com.training.islamyapp.pojo.ahadeth.AhadeethList;
import com.training.islamyapp.pojo.ahadeth.Hadith;
import com.training.islamyapp.repositories.AhadethRepository;

import java.util.List;

public class AhadethViewModel extends ViewModel {

    AhadethRepository repository = new AhadethRepository();
    public List<AhadeethList> getAhadeth(){
        return repository.getAhadeth();
    }

    public List<Hadith> getAhadethDetails(int index) {
        return repository.getAhadethDetails(index);
    }
}
