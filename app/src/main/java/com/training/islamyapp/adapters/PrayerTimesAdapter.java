package com.training.islamyapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.training.islamyapp.databinding.ItemPrayerBinding;
import com.training.islamyapp.pojo.prayer_times.MyTimings;

import java.util.ArrayList;
import java.util.List;

public class PrayerTimesAdapter extends RecyclerView.Adapter<PrayerTimesAdapter.PrayerTimesHolder> {
    private List<MyTimings> timingList;

    public PrayerTimesAdapter(){
        timingList = new ArrayList<>();
    }

    public void setMyTimings(List<MyTimings> timingList) {
        this.timingList = timingList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PrayerTimesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrayerTimesHolder(
                ItemPrayerBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PrayerTimesHolder holder, int position) {
        holder.bind(timingList.get(position));

    }

    @Override
    public int getItemCount() {
        return timingList.size();
    }

    class PrayerTimesHolder extends RecyclerView.ViewHolder{
        ItemPrayerBinding binding;
        public PrayerTimesHolder(@NonNull ItemPrayerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MyTimings timing) {
            binding.prayerName.setText(timing.getPrayerName());
            binding.prayerTime.setText(timing.getPrayerTime());
            binding.prayerArabicName.setText(timing.getPrayerArabicN());
        }
    }
}
