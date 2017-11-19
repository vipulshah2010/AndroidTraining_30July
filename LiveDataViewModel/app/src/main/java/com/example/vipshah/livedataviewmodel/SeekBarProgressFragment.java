package com.example.vipshah.livedataviewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SeekBarProgressFragment extends Fragment {

    private static final String ARG_PROGRESS = "progress";

    public static SeekBarProgressFragment newInstance(int progress) {

        Bundle args = new Bundle();
        args.putInt(ARG_PROGRESS, progress);
        SeekBarProgressFragment fragment = new SeekBarProgressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        int progress = getArguments().getInt(ARG_PROGRESS);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText(String.format("Value is %d", progress));
        return textView;
    }
}
