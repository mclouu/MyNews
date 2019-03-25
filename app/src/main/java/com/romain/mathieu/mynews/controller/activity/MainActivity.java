/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.controller.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.utils.MyConstant;
import com.romain.mathieu.mynews.view.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.romain.mathieu.mynews.utils.MyConstant.BUNDLED_EXTRA;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_main_viewpager)
    ViewPager pager;
    @BindView(R.id.activity_main_tabs)
    TabLayout tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);

        setSupportActionBar(toolbar);
        setTitle("My News");


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        mNavigationView.setNavigationItemSelectedListener(this);

        this.configureViewPagerAndTabs();
    }


    //-----------------------------------||
    //          VIEW PAGER               ||
    //                                   ||
    //                                   ||
    //-----------------------------------||

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

    //-----------------------------------||
    //          NAVIGATION DRAWER        ||
    //                                   ||
    //                                   ||
    //-----------------------------------||

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // 6 - Show fragment after user clicked on a menu item
        switch (id) {
            case R.id.top_stories:
                pager.setCurrentItem(0);
                break;
            case R.id.most_popular:
                pager.setCurrentItem(1);
                break;
            case R.id.technologie:
                pager.setCurrentItem(3);
                break;

            case R.id.drawer_notif:
                this.onNotifSelected();
                break;
            case R.id.drawer_help:
                this.onHelpSelected();
                break;
            case R.id.drawer_about:
                this.onAboutSelected();
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    //-----------------------------------||
    //                                   ||
    //              MENU                 ||
    //                                   ||
    //-----------------------------------||

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_notif:
                this.onNotifSelected();
                return true;
            case R.id.menu_help:
                this.onHelpSelected();
                return true;
            case R.id.menu_about:
                this.onAboutSelected();
                return true;
            case R.id.menu_search:
                this.onSearchSelected();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSearchSelected() {
        Intent myIntentSearch = new Intent(MainActivity.this, SearchAndNotifyActivity.class);
        myIntentSearch.putExtra(BUNDLED_EXTRA, MyConstant.SEARCH_ID);
        this.startActivity(myIntentSearch);
    }

    public void onNotifSelected() {
        Intent myIntentNotif = new Intent(MainActivity.this, SearchAndNotifyActivity.class);
        myIntentNotif.putExtra(BUNDLED_EXTRA, MyConstant.NOTIF_ID);
        this.startActivity(myIntentNotif);
    }

    public void onHelpSelected() {
        // Build an AlertDialog for the About section
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Set Title and Message content
        builder.setTitle("Help");
        builder.setMessage("Empty");
        // Neutral button
        builder.setNeutralButton("ok !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void onAboutSelected() {
        // Build an AlertDialog for the About section
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Set Title and Message content
        builder.setTitle("About");
        builder.setMessage("Empty");
        // Neutral button
        builder.setNeutralButton("ok !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}