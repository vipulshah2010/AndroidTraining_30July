package com.microsoft.twopanefragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {

    private static final String ARG_COLOR = "color";

    public static Fragment2 newInstance(String color) {

        Bundle args = new Bundle();
        args.putString(ARG_COLOR, color);
        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View backgroundView = view.findViewById(R.id.backgroundView);

        Bundle bundle = getArguments();

        if (bundle != null) {
            String color = bundle.getString(ARG_COLOR);
            backgroundView.setBackgroundColor(Color.parseColor(color));
        }
    }
}
