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

import com.facebook.stetho.Stetho;
import com.romain.mathieu.mynews.R;
import com.romain.mathieu.mynews.utils.MyConstant;
import com.romain.mathieu.mynews.view.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.romain.mathieu.mynews.utils.MyConstant.BUNDLED_EXTRA;

public class MainActivity extends AppCompatActivity {

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
        this.configureViewPagerAndTabs();
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
}