package com.training.islamyapp.repositories;

import com.training.islamyapp.pojo.ahadeth.AhadeethList;
import com.training.islamyapp.pojo.ahadeth.AhadeethListProvider;
import com.training.islamyapp.pojo.ahadeth.AhadethDetailsProvider;
import com.training.islamyapp.pojo.ahadeth.Hadith;

import java.util.List;

public class AhadethRepository {
    public List<AhadeethList> getAhadeth(){
        return AhadeethListProvider.getAhadeethList();
    }

    public List<Hadith> getAhadethDetails(int index) {
        return AhadethDetailsProvider.getAhadeeth(index);
    }
}
