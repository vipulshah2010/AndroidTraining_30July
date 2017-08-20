package com.microsoft.fragmentbackstack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class MyDialogFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Hello There!");
        builder.setMessage("How are you?");

        builder.setPositiveButton("Ok", null);
        builder.setNegativeButton("Dismiss", null);

        return builder.create();
    }
}
