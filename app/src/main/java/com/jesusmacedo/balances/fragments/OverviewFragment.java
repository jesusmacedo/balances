package com.jesusmacedo.balances.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    private TextView tvNoCards;

    public OverviewFragment() {
        // Required empty public constructor
    }

    /**
     * For receiving data before creating the view.
     * @return
     */
    public static OverviewFragment newInstance(Card card) {
        Bundle args = new Bundle();

        // add data
        args.putSerializable(ARG_CARD_PARAM, card);
        Log.e("DFA", card.getName());

        OverviewFragment fragment = new OverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * For handling the received data.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("DFA", "oncreate");
        tvNoCards = (TextView) getActivity().findViewById(R.id.tv_no_cards);

        // if something was received, get card
        if (getArguments() != null) {
            card = (Card) getArguments().getSerializable(ARG_CARD_PARAM);
        }
        Log.e("DFA", card.getName());
        if (card != null) {
            tvNoCards.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

}
