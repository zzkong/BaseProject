package org.zzk.example.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import org.zzk.example.R;
import org.zzk.example.adapter.BasePagerAdapter;
import org.zzk.example.ui.base.BaseFragment;
import org.zzk.example.ui.meitu.MeituListFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * Created by zwl on 16/9/30.
 */

public class MeituMainFragment extends BaseFragment {
    @Bind(R.id.tablayout)
    TabLayout mTablayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;

    private List<Fragment> mFragments = new ArrayList<>();
    private BasePagerAdapter mPagerAdapter;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_meitu_main;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initEventAndData() {
        List<String> contentList = new ArrayList<>();
        String[] contents = getResources().getStringArray(R.array.images_category_list);
        Collections.addAll(contentList, contents);
        for (int i = 0; i < contentList.size(); i++) {
            mFragments.add(MeituListFragment.newInstance(contentList.get(i)));
        }
        mPagerAdapter = new BasePagerAdapter(getChildFragmentManager(), mFragments, contentList);
        mViewpager.setAdapter(mPagerAdapter);
        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.setTabsFromPagerAdapter(mPagerAdapter);
    }

    @Override
    protected void lazyLoadData() {
    }
}
