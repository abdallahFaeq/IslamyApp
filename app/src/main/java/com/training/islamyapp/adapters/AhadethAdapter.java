package com.training.islamyapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.training.islamyapp.R;
import com.training.islamyapp.pojo.ahadeth.AhadeethList;

import java.util.List;

public class AhadethAdapter extends RecyclerView.Adapter<AhadethAdapter.AhadethHolder> {
    private List<AhadeethList> ahadeethList;
    private OnHadeethClickListener onHadeethClickListener;

    public AhadethAdapter(List<AhadeethList> items){
        this.ahadeethList = items;
    }

    @NonNull
    @Override
    public AhadethHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hadeeth_name,parent,false);
        return new AhadethHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AhadethHolder holder, int position) {

        holder.bind(ahadeethList.get(position));
        if (onHadeethClickListener!=null){
            holder.itemView.setOnClickListener(view -> {
                onHadeethClickListener.onHadeethClick(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return ahadeethList.size();
    }

    class AhadethHolder extends RecyclerView.ViewHolder{
        TextView hadethTitle;
        public AhadethHolder(@NonNull View itemView) {
            super(itemView);
            hadethTitle= itemView.findViewById(R.id.hadeeth_title);
        }

        public void bind(AhadeethList ahadeethList) {
            hadethTitle.setText(ahadeethList.getName());
        }
    }

    public void setOnHadeethClickListener(OnHadeethClickListener onHadeethClickListener){
        this.onHadeethClickListener = onHadeethClickListener;
    }
    public interface OnHadeethClickListener{
        void onHadeethClick(int position);
    }
}
