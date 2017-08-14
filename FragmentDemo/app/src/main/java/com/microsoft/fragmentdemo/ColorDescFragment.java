package com.microsoft.fragmentdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ColorDescFragment extends Fragment {
    private static final String KEY_COLOR = "color";

    public static ColorDescFragment newInstance(String color) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_COLOR, color);
        ColorDescFragment fragment = new ColorDescFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).
                inflate(R.layout.fragment_color_desc,
                container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout colorView = view
                .findViewById(R.id.colorView);

        Bundle bundle = getArguments();
        String stringColor = bundle.getString(KEY_COLOR);
        colorView.setBackgroundColor(Color.parseColor(stringColor));
    }
}
