package com.training.islamyapp.fragments.azkar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.training.islamyapp.adapters.azkar.AzkarAdapter;
import com.training.islamyapp.adapters.azkar.AzkarDetailsAdapter;
import com.training.islamyapp.databinding.FragmentAzkarDetailsBinding;
import com.training.islamyapp.pojo.azkar.AzkarItem;
import com.training.islamyapp.viewModels.AzkarViewModel;

import java.util.ArrayList;
import java.util.List;

public class AzkarDetailsFragment extends Fragment {
    // declaration
    private FragmentAzkarDetailsBinding binding;

    private AzkarViewModel viewModel;
    private AzkarDetailsAdapter adapter;
    private int position;
    private String retrievedItem;
    private List<String> azkarContents;

    private String TAG = "AzkarDetailsFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getInt("position",0) != 0){
            position = getArguments().getInt("position");
            retrievedItem = getArguments().getString("category");
            Log.e(TAG, "onCreate: "+retrievedItem );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAzkarDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialization
        viewModel = new ViewModelProvider(this).get(AzkarViewModel.class);
        azkarContents = new ArrayList<>();
        adapter = new AzkarDetailsAdapter(azkarContents);

        initRv();

        viewModel.getAllAzkar(getContext());

        observeToLiveData();

    }

    private void retrieveRelatedAzkarOfClickedItem(List<AzkarItem> items){
        for (int i = 1; i<items.size(); i++){
            if (retrievedItem.equals(items.get(i).getCategory())){
                azkarContents.add(items.get(i).getZekr());
            }
        }
        adapter.setItems(azkarContents);
    }

    private void initRv(){
        binding.azkarDetailsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.azkarDetailsRv.setAdapter(adapter);
    }

    private void observeToLiveData(){
        viewModel.azkarStringLive.observe(getViewLifecycleOwner(), new Observer<List<AzkarItem>>() {
            @Override
            public void onChanged(List<AzkarItem> azkarItems) {
                retrieveRelatedAzkarOfClickedItem(azkarItems);
            }
        });
    }
}