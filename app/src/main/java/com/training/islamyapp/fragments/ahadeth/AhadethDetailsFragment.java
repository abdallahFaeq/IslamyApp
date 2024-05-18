package com.training.islamyapp.fragments.ahadeth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.islamyapp.R;
import com.training.islamyapp.databinding.FragmentAhadethDetailsBinding;
import com.training.islamyapp.pojo.ahadeth.Hadith;
import com.training.islamyapp.viewModels.AhadethViewModel;

import java.util.ArrayList;
import java.util.List;

public class AhadethDetailsFragment extends Fragment {
    // declaration
    private FragmentAhadethDetailsBinding binding;
    private AhadethViewModel viewModel;
    private int index;
    List<Hadith> ahadeth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            index = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAhadethDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initialization
        viewModel = new ViewModelProvider(this).get(AhadethViewModel.class);
        ahadeth = new ArrayList<>();

        ahadeth = viewModel.getAhadethDetails(index);

        binding.HadethText.setText(ahadeth.get(0).getHadethName());
    }
}