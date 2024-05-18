package com.training.islamyapp.adapters.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.training.islamyapp.databinding.ItemSurahDetailsBinding;
import com.training.islamyapp.pojo.quran.SurahDetailsItem;

import java.util.List;


public class SurahDetailsAdapter extends RecyclerView.Adapter<SurahDetailsAdapter.SurahDetailsHolder> {

    private ItemSurahDetailsBinding binding;
    private Context context;
    private List<SurahDetailsItem> items;
    public SurahDetailsAdapter(Context context, List<SurahDetailsItem> items){
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public SurahDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SurahDetailsHolder(
            ItemSurahDetailsBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
            )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SurahDetailsHolder holder, int position) {
        SurahDetailsItem suraDetailsItem = items.get(position);
        holder.bind(suraDetailsItem);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<SurahDetailsItem> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class SurahDetailsHolder extends RecyclerView.ViewHolder{

        public SurahDetailsHolder(@NonNull ItemSurahDetailsBinding aBinding){
            super(aBinding.getRoot());
            binding = aBinding;
        }

        public void bind(SurahDetailsItem item){
            binding.ayaArabicText.setText(item.getArabicText());
            binding.ayaNo.setText(item.getAya());
            binding.ayaTranslation.setText(item.getTranslation());
        }
    }
}
