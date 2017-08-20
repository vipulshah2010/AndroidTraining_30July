package com.microsoft.twopanefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment1 extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment1,
                container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button1 = view.findViewById(R.id.button1);
        Button button2 = view.findViewById(R.id.button2);
        Button button3 = view.findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                onSelectColor("#ff0000");
                break;
            case R.id.button2:
                onSelectColor("#00ff00");
                break;
            case R.id.button3:
                onSelectColor("#0000ff");
                break;
        }
    }

    private void onSelectColor(String color) {
        if (getActivity() instanceof Fragment1Listener) {
            Fragment1Listener listener = (Fragment1Listener) getActivity();
            listener.onColorSelected(color);
        }
    }

    public interface Fragment1Listener {
        void onColorSelected(String color);
    }
}
