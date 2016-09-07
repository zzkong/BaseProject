package org.zzk.example.ui.gank;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.zzk.example.R;
import org.zzk.example.adapter.ContentPagerAdapter;
import org.zzk.example.ui.BaseLazyFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * Created by zwl on 16/9/6.
 */
public class ContentFragment extends BaseLazyFragment {
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.tablayout)
    TabLayout mTablayout;

    private ContentPagerAdapter mPagerAdapter;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initEventAndData() {
        List<String> contentList = new ArrayList<>();
        String[] contents = getResources().getStringArray(R.array.gank_content);
        Collections.addAll(contentList, contents);
        mPagerAdapter = new ContentPagerAdapter(getChildFragmentManager(), contentList);
        mViewpager.setAdapter(mPagerAdapter);
        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.setTabsFromPagerAdapter(mPagerAdapter);
    }

    @Override
    protected void lazyLoadData() {

    }
}
