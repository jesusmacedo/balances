package com.jesusmacedo.balances.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jesusmacedo.balances.R;
import com.jesusmacedo.balances.adapters.OverviewPagerAdapter;
import com.jesusmacedo.balances.models.Card;

import java.util.List;

/**
 * A simple {@link Fragment} subclass for adding tabs according to the previously stored cards.
 */
public class OverviewTabsFragment extends Fragment {

    /**
     * In order to find the fragment when trying yo reload its data after a new card has been added.
     */
    public static final String TAG = "OverviewTabsFragment";

    private View view;
    private OverviewPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private List<Card> cards;
    private TextView tvNoCards;

    public OverviewTabsFragment() {
        // Required empty public constructor
    }

    /**
     * Create view, load cards and create viewPager.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_overview_tabs, container, false);

        tvNoCards = (TextView) view.findViewById(R.id.tv_no_cards);

        cards = Card.getCards(view.getContext());
        pagerAdapter = new OverviewPagerAdapter(getActivity().getSupportFragmentManager(), cards);

        viewPager = (ViewPager) view.findViewById(R.id.vp_overview);
        viewPager.setAdapter(pagerAdapter);

        displayElements();

        // add viewPager to tabs
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tl_tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    /**
     * Display either the "NO CARDS" message or display overview for at least one.
     */
    private void displayElements() {
        if (cards.size() == 0) {
            tvNoCards.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
        } else {
            viewPager.setVisibility(View.VISIBLE);
            tvNoCards.setVisibility(View.GONE);
        }
    }

    /**
     * Reload data, called from main activity.
     */
    public void reloadCards() {
        cards = Card.getCards(view.getContext());
        pagerAdapter = new OverviewPagerAdapter(getActivity().getSupportFragmentManager(), cards);
        viewPager.setAdapter(pagerAdapter);
        displayElements();
    }

}
