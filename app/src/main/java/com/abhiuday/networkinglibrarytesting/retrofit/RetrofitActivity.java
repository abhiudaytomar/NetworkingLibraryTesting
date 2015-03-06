package com.abhiuday.networkinglibrarytesting.retrofit;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.abhiuday.networkinglibrarytesting.BaseActivity;
import com.abhiuday.networkinglibrarytesting.R;
import com.abhiuday.networkinglibrarytesting.widget.SlidingTabLayout;

/**
 Created by abhiuday.tomar on 26/02/15.
 */
public class RetrofitActivity extends BaseActivity{
    private static final String TAG = "RetrofitActivity";
    private ViewPager mViewPager;
    private ActionBar actionBar;
    private OurViewPagerAdapter mViewPagerAdapter;
    private SlidingTabLayout mSlidingTabLayout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPagerAdapter = new OurViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);


        Resources res = getResources();
        mSlidingTabLayout.setSelectedIndicatorColors(res.getColor(R.color.tab_selected_strip));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    private class OurViewPagerAdapter extends FragmentPagerAdapter {
        public OurViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new RetrofitTumblrImagesFragment();
                case 1:
                    return new RetrofitRPCallsFragment();
                case 2 :
                    return new RetrofitRPCallsFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tumblr Calls";
                case 1:
                    return "RP calls";
                case 2:
                    return "Get it done calls";
                default:
                    return "One";
            }
            
        }
    }
}
