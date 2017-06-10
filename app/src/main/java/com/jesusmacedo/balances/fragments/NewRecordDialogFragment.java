package com.jesusmacedo.balances.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.jesusmacedo.balances.R;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewRecordDialogFragment extends DialogFragment {

    public static final String TAG = "NEW_RECORD";
    private View view;

    private SegmentedButtonGroup sbg;

    public NewRecordDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_record_dialog, container, false);

        sbg = (SegmentedButtonGroup) view.findViewById(R.id.sbg_record_types);
        return view;
    }

    /**
     * Set DialogFragment to fullscreen.
     */
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        window.setGravity(Gravity.CENTER);
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);
    }

}
