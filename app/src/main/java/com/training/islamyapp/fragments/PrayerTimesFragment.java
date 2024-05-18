package com.training.islamyapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.islamyapp.R;
import com.training.islamyapp.adapters.PrayerTimesAdapter;
import com.training.islamyapp.databinding.FragmentPrayerTimesBinding;
import com.training.islamyapp.pojo.prayer_times.MyTimings;
import com.training.islamyapp.viewModels.PrayerTimesViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class PrayerTimesFragment extends Fragment {
    // declaration
    private FragmentPrayerTimesBinding binding;
    private PrayerTimesViewModel viewModel;
    private PrayerTimesAdapter adapter;
    private Calendar calendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPrayerTimesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialization
        viewModel = new ViewModelProvider(this).get(PrayerTimesViewModel.class);
        calendar = Calendar.getInstance();
        initRv();
        setupCalendar();

        viewModel.getPrayerTimes(
                "Cairo",
                "Egypt",
                calendar.get(Calendar.DAY_OF_MONTH),
                5,
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        observeToData();
    }

    private void initRv(){
        adapter = new PrayerTimesAdapter();
        binding.prayersTimeRv.setAdapter(adapter);
    }

    private void observeToData(){
        viewModel
                .timesLive
                .observe(getViewLifecycleOwner(), new Observer<ArrayList<MyTimings>>() {
                    @Override
                    public void onChanged(ArrayList<MyTimings> myTimings) {
                        binding.progressBarPrayers.setVisibility(View.GONE);
                        adapter.setMyTimings(myTimings);
                    }
                });
    }


    private void setupCalendar(){
        binding.dataPicker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                (datPicker, year, month, day)->{
                    viewModel.getPrayerTimes(
                            "Cairo", "Egypt", day, 5, month, year
                    );
                });
    }
}