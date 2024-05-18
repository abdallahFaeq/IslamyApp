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

import com.training.islamyapp.R;
import com.training.islamyapp.activities.MainActivity;
import com.training.islamyapp.adapters.azkar.AzkarAdapter;
import com.training.islamyapp.databinding.FragmentAzkarBinding;
import com.training.islamyapp.pojo.azkar.AzkarItem;
import com.training.islamyapp.viewModels.AzkarViewModel;

import java.util.ArrayList;
import java.util.List;


public class AzkarFragment extends Fragment {
    private String TAG = "AzkarFragment";
    // declaration
    private FragmentAzkarBinding binding;
    private AzkarViewModel viewModel;
    private List<AzkarItem> items;
    private AzkarAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAzkarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialization
        viewModel = new ViewModelProvider(this).get(AzkarViewModel.class);
        items = new ArrayList<>();
        adapter = new AzkarAdapter(items);

        initRv();

        viewModel.getAllAzkar(getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        observeToLiveData();

        adapter.setOnItemClickListener(new AzkarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AzkarItem azkarItem, int position) {
                // send item and push fragment
                sendDataAndPushFragment(azkarItem, position);
            }
        });
    }

    private void sendDataAndPushFragment(AzkarItem azkarItem, int position){
        AzkarDetailsFragment publisherFragment = new AzkarDetailsFragment();
        Bundle args = new Bundle();

        args.putInt("position", position);
        args.putString("category",azkarItem.getCategory());
        publisherFragment.setArguments(args);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, publisherFragment)
                .addToBackStack(null)
                .commit();

    }

    private void initRv(){
        binding.azkarRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.azkarRv.setAdapter(adapter);
    }

    private void observeToLiveData(){
        viewModel.azkarStringLive.observe(getViewLifecycleOwner(), new Observer<List<AzkarItem>>() {
            @Override
            public void onChanged(List<AzkarItem> azkarItems) {
                Log.e(TAG, "onChanged: "+azkarItems.get(40).getCategory() );
                adapter.setItems(azkarItems);
            }
        });
    }
}