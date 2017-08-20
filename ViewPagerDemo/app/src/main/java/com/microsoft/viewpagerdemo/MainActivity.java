package com.microsoft.viewpagerdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new RedColorFragment());
        fragments.add(new GreenColorFragment());
        fragments.add(new BlueColorFragment());
        fragments.add(new RedColorFragment());
        fragments.add(new GreenColorFragment());
        fragments.add(new BlueColorFragment());
        fragments.add(new RedColorFragment());
        fragments.add(new GreenColorFragment());
        fragments.add(new BlueColorFragment());

        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    class MyPageAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "RED";
                case 1:
                    return "GREEN";
                default:
                    return "BLUE";
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
