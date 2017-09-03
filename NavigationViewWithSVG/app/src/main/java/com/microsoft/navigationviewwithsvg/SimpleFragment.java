package com.microsoft.navigationviewwithsvg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SimpleFragment extends Fragment {

    private static final String ARG_UNIT_TYPE = "UNIT_TYPE";

    public static SimpleFragment newInstance(MainActivity.UnitType type) {

        Bundle args = new Bundle();
        args.putInt(ARG_UNIT_TYPE, type.ordinal());
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_simple_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        int ordinal = bundle.getInt(ARG_UNIT_TYPE);
        MainActivity.UnitType unitType = MainActivity.UnitType.values()[ordinal];

        TextView messageTextView = view.findViewById(R.id.messageTextView);

        switch (unitType) {
            case CURRENCY:
                messageTextView.setText("Currency Fragment");
                break;
            case WEIGHT:
                messageTextView.setText("Weight Fragment");
                break;
            case LENGTH:
                messageTextView.setText("Length Fragment");
                break;
        }
    }
}
