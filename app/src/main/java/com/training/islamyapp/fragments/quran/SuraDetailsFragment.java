package com.training.islamyapp.fragments.quran;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.islamyapp.R;
import com.training.islamyapp.adapters.quran.SurahDetailsAdapter;
import com.training.islamyapp.databinding.FragmentSuraDetailsBinding;
import com.training.islamyapp.pojo.quran.SurahDetailsItem;
import com.training.islamyapp.pojo.quran.SurahDetailsResponse;
import com.training.islamyapp.viewModels.quran.SuraDetailsViewModel;

import java.util.ArrayList;
import java.util.List;


public class SuraDetailsFragment extends Fragment {

    // declaration
    private FragmentSuraDetailsBinding binding;
    private SurahDetailsAdapter adapter;
    private List<SurahDetailsItem> items;
    private SuraDetailsViewModel viewModel;
    private String suraNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSuraDetailsBinding.inflate(inflater, container, false);

        if (getArguments()!=null){
            suraNumber = getArguments().getString("suraNumber");
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialization
        viewModel = new ViewModelProvider(this).get(SuraDetailsViewModel.class);
        items = new ArrayList<>();
        adapter = new SurahDetailsAdapter(getContext(), items);

        initRv();
        viewModel.getSuraDetails("english_saheeh",suraNumber);
    }

    @Override
    public void onStart() {
        super.onStart();
        observeToLiveData();
    }

    public void observeToLiveData(){
        viewModel.suraDetailsLiveData.observe(this, new Observer<SurahDetailsResponse>() {
            @Override
            public void onChanged(SurahDetailsResponse surahDetailsResponse) {
                adapter.setItems(surahDetailsResponse.getResult());
            }
        });
    }

    public void initRv(){
        binding.suraDetailsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.suraDetailsRv.setAdapter(adapter);
    }
}