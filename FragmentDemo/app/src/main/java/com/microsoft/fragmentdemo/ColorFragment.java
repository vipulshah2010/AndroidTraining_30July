package com.microsoft.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ColorFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_color, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button redButton = view.findViewById(R.id.redButton);
        Button greenButton = view.findViewById(R.id.greenButton);
        Button blueButton = view.findViewById(R.id.blueButton);

        redButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.redButton:
                displayRedFragment();
                break;
            case R.id.greenButton:
                displayGreenFragment();
                break;
            case R.id.blueButton:
                displayBlueFragment();
                break;
        }
    }

    private void displayRedFragment() {
        ColorFragmentListener listener = (ColorFragmentListener) getActivity();

        if (listener != null) {
            listener.onColorSelected("#ff0000");
        }
    }

    private void displayGreenFragment() {
        ColorFragmentListener listener = (ColorFragmentListener) getActivity();

        if (listener != null) {
            listener.onColorSelected("#00ff00");
        }
    }

    private void displayBlueFragment() {
        ColorFragmentListener listener = (ColorFragmentListener) getActivity();

        if (listener != null) {
            listener.onColorSelected("#0000ff");
        }
    }

    interface ColorFragmentListener {
        void onColorSelected(String colorName);
    }
}
