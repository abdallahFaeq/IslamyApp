package com.training.islamyapp.fragments.quran;

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

import com.training.islamyapp.R;
import com.training.islamyapp.activities.MainActivity;
import com.training.islamyapp.adapters.quran.SurahAdapter;
import com.training.islamyapp.databinding.FragmentSurahBinding;
import com.training.islamyapp.pojo.quran.SurahItem;
import com.training.islamyapp.pojo.quran.SurahResponse;
import com.training.islamyapp.viewModels.quran.SurahViewModel;

import java.util.ArrayList;
import java.util.List;

public class SurahFragment extends Fragment {
    // declaration
    private FragmentSurahBinding binding;
    private SurahViewModel surahViewModel;
    private SurahAdapter surahAdapter;
    private List<SurahItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // initialization
        binding = FragmentSurahBinding.inflate(inflater, container, false);
        surahViewModel = new ViewModelProvider(this).get(SurahViewModel.class);
        items = new ArrayList<>();
        surahAdapter = new SurahAdapter(getContext(),items);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRv();
        surahViewModel.getSurahResponse();

    }


    @Override
    public void onStart() {
        super.onStart();
        observeToLiveData();
        surahAdapter.setOnItemClickListener(new SurahAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String suraNumber) {
                // send number to suraDetailsFragment
                sendData(suraNumber);
            }
        });
    }


    public void sendData(String suraNumber){
        SuraDetailsFragment suraDetailsFragment = new SuraDetailsFragment();
        Bundle args = new Bundle();
        args.putString("suraNumber", suraNumber);
        suraDetailsFragment.setArguments(args);

        // inflate surahDetailsFragment
        MainActivity.getMyFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, suraDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume:" );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }
    private String TAG = "SurahFragment";

    private void initRv(){
        binding.surahRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.surahRV.setAdapter(surahAdapter);
    }

    private void observeToLiveData(){
        surahViewModel._responseSurahLiveData
                .observe(this, new Observer<SurahResponse>() {
                    @Override
                    public void onChanged(SurahResponse surahResponse) {
                        surahAdapter.setItemsList(surahResponse.getData());
                    }
                });
    }


}