package com.jesusmacedo.balances.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jesusmacedo.balances.R;
import com.jesusmacedo.balances.fragments.NewCardDialogFragment;
import com.jesusmacedo.balances.fragments.NewRecordDialogFragment;
import com.jesusmacedo.balances.fragments.OverviewTabsFragment;
import com.jesusmacedo.balances.models.Card;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NewCardDialogFragment.Callback {

    /**
     * Changes according to the menu and flows.
     */
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // if it is the first time creating this activity
        if (savedInstanceState == null) {
            // set first fragment
            setTitle("Overview");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new OverviewTabsFragment(), OverviewTabsFragment.TAG).commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewRecordDialogFragment dialog = new NewRecordDialogFragment();
                dialog.setStyle( DialogFragment.STYLE_NORMAL, R.style.AppTheme );
                dialog.show(getSupportFragmentManager(),NewRecordDialogFragment.TAG);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_card) {
            NewCardDialogFragment dialog = new NewCardDialogFragment();
            dialog.setStyle( DialogFragment.STYLE_NORMAL, R.style.AppTheme );
            dialog.show(getSupportFragmentManager(),NewCardDialogFragment.TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Replace the current fragment.
     * @param fragment
     */
    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragment).commit();
    }

    /**
     * Receive signal from DialogFragment to reload the main fragment (OverviewTabsFragment).
     * @param card
     */
    @Override
    public void newCard(Card card) {
        OverviewTabsFragment fragment = (OverviewTabsFragment) getSupportFragmentManager().findFragmentByTag(OverviewTabsFragment.TAG);
        if (fragment!=null)
            fragment.reloadCards();
    }
}
