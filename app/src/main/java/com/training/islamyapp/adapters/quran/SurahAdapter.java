package com.training.islamyapp.adapters.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.training.islamyapp.databinding.ItemSurahBinding;
import com.training.islamyapp.pojo.quran.SurahItem;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahHolder> {

        private Context context;
        private List<SurahItem> surahItems;

        public SurahAdapter(Context context, List<SurahItem> surahItems){
            this.context = context;
            this.surahItems = surahItems;
        }

        @Override
        public int getItemCount() {
            return surahItems.size();
        }

        @NonNull
        @Override
        public SurahHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SurahHolder(
                    ItemSurahBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }

        @Override
        public void onBindViewHolder(@NonNull SurahHolder holder, int position) {
            SurahItem item = this.surahItems.get(position);
            holder.bind(item);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null){
                        onItemClickListener.onItemClick(String.valueOf(position+1));
                    }
                }
            });
        }

        public void setItemsList(List<SurahItem> items){
            this.surahItems = items;
            notifyDataSetChanged();
        }

        class SurahHolder extends RecyclerView.ViewHolder{
            private ItemSurahBinding binding;
            public SurahHolder(@NonNull ItemSurahBinding binding){
                super(binding.getRoot());

                this.binding = binding;
            }
            public void bind(SurahItem item){
                binding.arabicSurahNameTv.setText(item.getName());
                binding.englishSurahNameTv.setText(item.getEnglishName());
                binding.numberAyatTv.setText(String.valueOf(item.getNumberOfAyahs()));
                binding.numberTv.setText(String.valueOf(item.getNumber()));
            }
        }

        private OnItemClickListener onItemClickListener;
        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener = onItemClickListener;
        }
        public interface OnItemClickListener{
            public void onItemClick(String suraNumber);
        }

    }


