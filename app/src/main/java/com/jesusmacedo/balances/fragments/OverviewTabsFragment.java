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

/**
 * A simple {@link Fragment} subclass for adding tabs according to the previously stored cards.
 */
public class OverviewTabsFragment extends Fragment {

    private OverviewPagerAdapter pagerAdapter;
    private ViewPager pager;
    private TextView tvNoCards;

    public OverviewTabsFragment() {
        // Required empty public constructor
    }

    /**
     * Create view, load cards and create pager.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview_tabs, container, false);

        pagerAdapter = new OverviewPagerAdapter(getActivity().getSupportFragmentManager(), Card.getCards(view.getContext()));

        pager = (ViewPager) view.findViewById(R.id.vp_overview);
        pager.setAdapter(pagerAdapter);

        Log.e("DFA", "Cuenta" + pager.getChildCount());

        if (pager.getChildCount() != 0) {
            Log.e("DFA", "display message");
            tvNoCards = (TextView) getActivity().findViewById(R.id.tv_no_cards);
            tvNoCards.setVisibility(View.VISIBLE);
            //pager.setVisibility(View.);
        }

        // add pager to tabs
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tl_tabs);
        tabLayout.setupWithViewPager(pager);
        return view;
    }

}
