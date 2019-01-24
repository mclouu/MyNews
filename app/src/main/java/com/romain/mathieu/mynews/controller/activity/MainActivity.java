/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.view.PageAdapter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        setTitle("My News");

        this.configureViewPagerAndTabs();
    }


    //-------------
    // VIEW PAGER
    //-------------

    private void configureViewPagerAndTabs() {
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        //Get ViewPager from layout
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        //Get TabLayout from layout
        //Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        //Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
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
        switch (item.getItemId()) {
            case R.id.menu_notif:

                return true;
            case R.id.menu_help:

                return true;
            case R.id.menu_about:

                return true;
            case R.id.menu_search:
                Intent myIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(myIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}