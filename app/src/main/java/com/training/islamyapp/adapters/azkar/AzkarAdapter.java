package com.training.islamyapp.adapters.azkar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.training.islamyapp.databinding.ItemZekrBinding;
import com.training.islamyapp.pojo.azkar.AzkarItem;

import java.util.List;

public class AzkarAdapter extends RecyclerView.Adapter<AzkarAdapter.AzkarHolder> {

    List<AzkarItem> items;

    public AzkarAdapter(List<AzkarItem> items){
        this.items = items;
    }


    public void setItems(List<AzkarItem> items){

        // comparison if items category equal or not
        for (int i = 0; i < items.size() - 1; i++){
            if (i>0){
                if (items.get(i).getCategory().equals(items.get(i-1).getCategory())){
                    continue;
                }else{
                    this.items.add(items.get(i));
                }
            }else {
                this.items.add(items.get(i));
            }
        }
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
        AzkarItem item = items.get(position);

        holder.bind(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(item, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class AzkarHolder extends RecyclerView.ViewHolder{
        private ItemZekrBinding binding;
        public AzkarHolder(ItemZekrBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(AzkarItem item){
            binding.zekrName.setText(item.getCategory());
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        public void onItemClick(AzkarItem azkarItem,int position);
    }
}
