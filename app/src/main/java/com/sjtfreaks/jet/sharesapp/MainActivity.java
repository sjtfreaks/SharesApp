package com.sjtfreaks.jet.sharesapp;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.sjtfreaks.jet.sharesapp.activity.SearchActivity;
import com.sjtfreaks.jet.sharesapp.adapter.ViewPagerAdapter;
import com.sjtfreaks.jet.sharesapp.fragment.HomeFragment;
import com.sjtfreaks.jet.sharesapp.fragment.HotFragment;
import com.sjtfreaks.jet.sharesapp.fragment.NewsFragment;
import com.sjtfreaks.jet.sharesapp.fragment.UserFragment;
import com.sjtfreaks.jet.sharesapp.util.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private BottomNavigationView bottomNavigationView;
    private ViewPager mViewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private MenuItem menuItem;
    private List<Fragment> mFragment;
    private Button bt_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chushi();
    }
    //初始化
    private void chushi() {
        bt_search = (Button) findViewById(R.id.bt_search);
        bt_search.setOnClickListener(this);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //取消效果
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mFragment = new ArrayList<>();
        mFragment.add(HomeFragment.newInstance("首页"));
        mFragment.add(HotFragment.newInstance("股市"));
        mFragment.add(NewsFragment.newInstance("资讯"));
        mFragment.add(UserFragment.newInstance("我的"));
        viewPagerAdapter.setList(mFragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            menuItem = item;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_user:
                    mViewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }
}
