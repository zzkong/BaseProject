package org.zzk.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.zzk.example.ui.gank.GankFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwl on 16/9/6.
 */
public class ContentPagerAdapter extends FragmentPagerAdapter{

    private List<String> mTitles = new ArrayList<>();

    public ContentPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return GankFragment.newInstance(mTitles.get(position));
    }

    @Override
    public int getCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? null : mTitles.get(position);
    }
}
