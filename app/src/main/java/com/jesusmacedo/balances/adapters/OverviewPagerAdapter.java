package com.jesusmacedo.balances.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jesusmacedo.balances.fragments.OverviewFragment;
import com.jesusmacedo.balances.models.Card;

import java.util.List;

/**
 * Handle tabs and card data visualization.
 */

public class OverviewPagerAdapter extends FragmentPagerAdapter {

    private List<Card> cards;

    public OverviewPagerAdapter(FragmentManager fm, List<Card> cards) {
        super(fm);
        this.cards = cards;
    }

    /**
     * Pass selected card to fragment in order to display its data.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return OverviewFragment.newInstance(cards.get(position));
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    /**
     * Set tab title according to the received cards.
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return cards.get(position).getName();
    }
}
