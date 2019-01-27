/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.romain.mathieu.mynews.controller.fragment.ArticleSearchFragment;
import com.romain.mathieu.mynews.controller.fragment.MostPopularFragment;
import com.romain.mathieu.mynews.controller.fragment.TopStoriesPageFragment;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TopStoriesPageFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return ArticleSearchFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return (3); // number of page to show
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "TOP STORIES";
            case 1:
                return "MOST POPULAR";
            case 2:
                return "CATÉGORIE";
            default:
                return null;
        }
    }
}
