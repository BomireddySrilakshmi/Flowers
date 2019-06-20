package com.vvitguntur.flowers;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.vvitguntur.flowers.Fragments.Flowers;
import com.vvitguntur.flowers.Fragments.MyFlowers;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContainerActivity extends AppCompatActivity {

    @BindView(R.id.tablayout_tv)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_tv)
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        ButterKnife.bind(this);
        ContainerActivity.MyViewPager mvp = new ContainerActivity.MyViewPager(getSupportFragmentManager());
        viewPager.setAdapter(mvp);
        tabLayout.setupWithViewPager(viewPager);
    }

    class MyViewPager extends FragmentStatePagerAdapter {

        public MyViewPager(FragmentManager fm) {
            super(fm);

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.flowers_title);
                case 1:
                    return getString(R.string.myflowers_title);
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Flowers();
                case 1:
                    return new MyFlowers();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
