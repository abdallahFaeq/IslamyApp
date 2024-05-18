package com.training.islamyapp.adapters.azkar;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.training.islamyapp.databinding.ItemZekrBinding;
import com.training.islamyapp.pojo.azkar.AzkarItem;

import java.util.ArrayList;
import java.util.List;

public class AzkarDetailsAdapter extends RecyclerView.Adapter<AzkarDetailsAdapter.AzkarHolder> {

    List<String> items;

    public AzkarDetailsAdapter(List<String> items){
        if (items != null){
            this.items = items;
        }else{
            this.items = new ArrayList<>();
        }
    }


    public void setItems(List<String> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AzkarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AzkarHolder(
          ItemZekrBinding.inflate(
                  LayoutInflater.from(parent.getContext()),
                  parent,
                  false
          )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AzkarHolder holder, int position) {
        String item = items.get(position);

        holder.bind(item);

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }


    public class AzkarHolder extends RecyclerView.ViewHolder{
        private ItemZekrBinding binding;
        public AzkarHolder(ItemZekrBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(String item){
            binding.zekrName.setText(item);
        }
    }
}
