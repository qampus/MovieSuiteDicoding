package me.papercom.moviedb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.papercom.moviedb.fragment.NowPlayingFragment;
import me.papercom.moviedb.fragment.SearchFragment;
import me.papercom.moviedb.fragment.UpComingFragment;

/**
 * Created by Hasan on 7/13/18.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NowPlayingFragment tabNowPlaying = new NowPlayingFragment();
                return tabNowPlaying;
            case 1:
                UpComingFragment tabUpComing = new UpComingFragment();
                return tabUpComing;
            case 2:
                SearchFragment tabSearch = new SearchFragment();
                return tabSearch;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}