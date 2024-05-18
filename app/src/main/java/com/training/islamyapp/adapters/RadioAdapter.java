package com.training.islamyapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.training.islamyapp.databinding.ItemRadioBinding;
import com.training.islamyapp.pojo.radio.RadiosItem;

import java.util.List;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.RadioHolder> {

    private List<RadiosItem> items;
    public RadioAdapter(List<RadiosItem> items){
        this.items = items;
    }

    public void setItems(List<RadiosItem> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RadioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RadioHolder(
          ItemRadioBinding.inflate(
                  LayoutInflater.from(parent.getContext()),
                  parent,
                  false
          )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RadioHolder holder, int position) {
        RadiosItem item = items.get(position);

        holder.bind(item);

        holder.binding.channelPlay.setOnClickListener(view -> {
            if(onPlayClickListener != null){
                onPlayClickListener.onPlayClick(item);
            }
        });

        holder.binding.channelStop.setOnClickListener(view -> {
            if(onStopClickListener != null){
                onStopClickListener.onStopClick(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class RadioHolder extends RecyclerView.ViewHolder{
        ItemRadioBinding binding;
        public RadioHolder(ItemRadioBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(RadiosItem item){
            binding.channelName.setText(item.getName());
        }
    }

    private OnPlayClickListener onPlayClickListener;
    public void setOnPlayClickListener(OnPlayClickListener onPlayClickListener){
        this.onPlayClickListener = onPlayClickListener;
    }

    private OnStopClickListener onStopClickListener;
    public void setOnStopClickListener(OnStopClickListener onStopClickListener){
        this.onStopClickListener = onStopClickListener;
    }

    public interface OnPlayClickListener{
        public void onPlayClick(RadiosItem item);
    }
    public interface OnStopClickListener{
        public void onStopClick(RadiosItem item);
    }

}
