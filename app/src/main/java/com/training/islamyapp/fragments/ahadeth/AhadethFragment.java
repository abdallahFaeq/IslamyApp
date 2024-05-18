package com.training.islamyapp.fragments.ahadeth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.islamyapp.R;
import com.training.islamyapp.activities.MainActivity;
import com.training.islamyapp.adapters.AhadethAdapter;
import com.training.islamyapp.databinding.FragmentAhadethBinding;
import com.training.islamyapp.pojo.ahadeth.AhadeethList;
import com.training.islamyapp.pojo.ahadeth.AhadethDetailsProvider;
import com.training.islamyapp.viewModels.AhadethViewModel;

import java.util.ArrayList;
import java.util.List;

public class AhadethFragment extends Fragment {
    // declaration
    private FragmentAhadethBinding binding;
    private AhadethAdapter adapter;
    private AhadethViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAhadethBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialization
        viewModel = new ViewModelProvider(this).get(AhadethViewModel.class);
        initRv();

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.setOnHadeethClickListener(new AhadethAdapter.OnHadeethClickListener() {
            @Override
            public void onHadeethClick(int position) {
                goToAhadethDetials(position);
            }
        });
    }

    private void goToAhadethDetials(int position){
        AhadethDetailsFragment nextFragment = new AhadethDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("position",position);
        nextFragment.setArguments(args);

        MainActivity.getMyFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, nextFragment)
                .commit();
    }

    private void initRv(){
        adapter = new AhadethAdapter(viewModel.getAhadeth());
        binding.ahadeethRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.ahadeethRv.setAdapter(adapter);
    }
}