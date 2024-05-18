package com.training.islamyapp.fragments;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.training.islamyapp.adapters.RadioAdapter;
import com.training.islamyapp.databinding.FragmentRadioBinding;
import com.training.islamyapp.pojo.radio.RadiosItem;
import com.training.islamyapp.viewModels.RadioViewModel;

import java.io.IOException;
import java.util.ArrayList;

public class RadioFragment extends Fragment {
    // declaration
    private FragmentRadioBinding binding;
    private RadioAdapter adapter;
    private RadioViewModel viewModel;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRadioBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initialization
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());

        viewModel = new ViewModelProvider(this).get(RadioViewModel.class);
        initRv();

        viewModel.getRadioChannels();


    }

    @Override
    public void onStart() {
        super.onStart();
        observeToData();

        adapter.setOnPlayClickListener(new RadioAdapter.OnPlayClickListener() {
            @Override
            public void onPlayClick(RadiosItem item) {

                try {
                       stopMediaPlayer();
                       mediaPlayer.setDataSource(getContext(), Uri.parse(item.getRadioUrl()));
                       mediaPlayer.prepareAsync();
                       mediaPlayer.setOnPreparedListener(it->{
                        it.start();
                       });



               }catch (IOException e){
                   Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
               }

            }

        });

        adapter.setOnStopClickListener(radioItem->{
            stopMediaPlayer();
        });
    }
    private void stopMediaPlayer(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
    }

    private void observeToData(){
        viewModel.radioItemsLive.observe(getViewLifecycleOwner(), (radioItems)-> {
            binding.progress.setVisibility(View.GONE);
            adapter.setItems(radioItems);
        });
    }

    private  void initRv(){
        adapter = new RadioAdapter(new ArrayList<>());
        binding.channelsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.channelsRv.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}