package com.example.vipshah.livedataviewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

public class SeekBarControlFragment extends Fragment {


    private static final String ARG_PROGRESS = "progress";

    public static SeekBarControlFragment newInstance(int progress) {

        Bundle args = new Bundle();
        args.putInt(ARG_PROGRESS, progress);
        SeekBarControlFragment fragment = new SeekBarControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_seekbar_control, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SeekBar seekBar = view.findViewById(R.id.seekBar);

        if (getArguments().containsKey(ARG_PROGRESS)) {
            seekBar.setProgress(getArguments().getInt(ARG_PROGRESS));
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.activityViewModel.PROGRESS.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
