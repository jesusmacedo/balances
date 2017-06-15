package com.jesusmacedo.balances.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jesusmacedo.balances.R;
import com.jesusmacedo.balances.models.Card;

/**
 * A simple {@link Fragment} subclass for displaying the cards overview.
 */
public class OverviewFragment extends Fragment {

    private static final String ARG_CARD_PARAM = "paramCard";

    private Card card;

    private TextView tvCurrentBalance;

    public OverviewFragment() {
        // Required empty public constructor
    }

    /**
     * For receiving data before creating the view.
     *
     * @return
     */
    public static OverviewFragment newInstance(Card card) {
        Bundle args = new Bundle();

        // add data
        args.putSerializable(ARG_CARD_PARAM, card);

        OverviewFragment fragment = new OverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * For handling the received data.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // if something was received, get card
        if (getArguments() != null) {
            card = (Card) getArguments().getSerializable(ARG_CARD_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        // get views
        tvCurrentBalance = (TextView) view.findViewById(R.id.tv_overview_current_balance);

        // set data
        tvCurrentBalance.setText("$" + card.getCurrentBalance());

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_new_record);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewRecordDialogFragment dialog = new NewRecordDialogFragment();
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);

                // pass data to the dialog fragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("paramCard", card);
                dialog.setArguments(bundle);

                dialog.show(getActivity().getSupportFragmentManager(), NewRecordDialogFragment.TAG);
            }
        });
        return view;
    }

}
