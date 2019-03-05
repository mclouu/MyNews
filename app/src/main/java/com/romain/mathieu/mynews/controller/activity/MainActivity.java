/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.controller.fragment.MostPopularFragment;
import com.romain.mathieu.mynews.utils.MyConstant;
import com.romain.mathieu.mynews.view.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.romain.mathieu.mynews.utils.MyConstant.BUNDLED_EXTRA;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_main_viewpager)
    ViewPager pager;
    @BindView(R.id.activity_main_tabs)
    TabLayout tabs;

    private Fragment fragmentTopStories;
    private Fragment fragmentMostPopular;

    public static final int FRAGMENT_TOPSTORIES = 0;
    public static final int FRAGMENT_MOSTPOPULAR = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);

        setSupportActionBar(toolbar);
        setTitle("My News");
        this.configureViewPagerAndTabs();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    //-------------
    // VIEW PAGER
    //-------------

    private void configureViewPagerAndTabs() {

        //Get ViewPager from layout
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        //Get TabLayout from layout
        //Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        //Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    //-------------
    // NAVIGATION DRAWER
    //-------------

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //------------
    // MENU
    //------------

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_notif:
                Intent myIntentNotif = new Intent(MainActivity.this, SearchAndNotifyActivity.class);
                myIntentNotif.putExtra(BUNDLED_EXTRA, MyConstant.NOTIF_ID);
                this.startActivity(myIntentNotif);
                return true;
            case R.id.menu_help:

                return true;
            case R.id.menu_about:

                return true;
            case R.id.menu_search:
                Intent myIntentSearch = new Intent(MainActivity.this, SearchAndNotifyActivity.class);
                myIntentSearch.putExtra(BUNDLED_EXTRA, MyConstant.SEARCH_ID);
                this.startActivity(myIntentSearch);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //-------------
    // NAVIGATION DRAWER 2
    //-------------

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // 6 - Show fragment after user clicked on a menu item
        if (id == R.id.top_stories) {
            this.showFragment(FRAGMENT_TOPSTORIES);

        } else if (id == R.id.most_popular) {
            this.showFragment(FRAGMENT_MOSTPOPULAR);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // ---------------------
    // FRAGMENTS
    // ---------------------

    // 5 - Show fragment according an Identifier

    private void showFragment(int fragmentIdentifier) {
        switch (fragmentIdentifier) {
            case FRAGMENT_TOPSTORIES:
                this.showTopFragment();
                break;
            case FRAGMENT_MOSTPOPULAR:
                this.showMostFragment();
                break;
            default:
                break;
        }
    }

    // 4 - Create each fragment page and show it

    private void showTopFragment() {
        if (this.fragmentTopStories == null)
            this.fragmentTopStories = MostPopularFragment.newInstance();
        this.startTransactionFragment(this.fragmentTopStories);
    }

    private void showMostFragment() {
        if (this.fragmentMostPopular == null)
            this.fragmentMostPopular = MostPopularFragment.newInstance();
        this.startTransactionFragment(this.fragmentMostPopular);
    }

    // 3 - Generic method that will replace and show a fragment inside the MainActivity Frame Layout

    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }
}