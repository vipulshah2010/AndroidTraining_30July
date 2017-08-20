package com.microsoft.fragmentbackstack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText number1EditText = view.findViewById(R.id.numberEditText);
        Button submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1 = number1EditText.getText().toString();

                if (getActivity() instanceof Fragment1Listener) {
                    Fragment1Listener listener = (Fragment1Listener) getActivity();
                    listener.onNumberEntered(number1);
                }
            }
        });
    }

    interface Fragment1Listener {
        void onNumberEntered(String number);
    }
}
