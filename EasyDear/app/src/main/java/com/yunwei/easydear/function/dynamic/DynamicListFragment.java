package com.yunwei.easydear.function.dynamic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.function.mainFuncations.findFuncation.ChildTabContentFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动态列表
 *
 * Created by Administrator on 2018/5/20.
 */

public class DynamicListFragment extends BaseFragment {

    @BindView(R.id.find_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.find_viewPager)
    ViewPager mViewPager;

    private String[] tabNames;
    private String[] types;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabNames = getResources().getStringArray(R.array.tab_tiltle);
        types = getResources().getStringArray(R.array.tab_type);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_mission, null);
        ButterKnife.bind(this,rootView);

        initTabLayout();

        return rootView;
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout(){
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.setAdapter(new TabLayoutViewPagerAdapter(getChildFragmentManager()));
        mViewPager.setOffscreenPageLimit(tabNames.length);
        mViewPager.setPageMargin(10);

        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabNames.length; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.title_tab_layout, null);
            TextView textView = (TextView) view.findViewById(R.id.title_tab_textView);
            textView.setText(tabNames[i]);
            /*设置默认选择*/
            if (i == 0) {
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            tab.setCustomView(textView);
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.colorAccent));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.gray));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * TabLayout适配器
     */
    class TabLayoutViewPagerAdapter extends FragmentPagerAdapter{

        public TabLayoutViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle=new Bundle();
            bundle.putString("type",types[position]);

            Fragment  fragment=new DynamicChildFragment();
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }
    }
}
