package com.yunwei.easydear.function.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.function.mainFuncations.MainActivity;
import com.yunwei.easydear.function.mainFuncations.findFuncation.FindViewPagerAdater;
import com.yunwei.easydear.function.mainFuncations.locationFunction.LocationActivity;
import com.yunwei.easydear.function.mainFuncations.messageFunction.MessageActivity;
import com.yunwei.easydear.function.mainFuncations.searchFunction.SearchActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.ISpfUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主面商家列表
 * <p>
 * Created by Administrator on 2018/5/20.
 */

public class BusinessListFragment extends BaseFragment {

    @BindView(R.id.Home3_Title)
    TextView mLocationTextView;
    @BindView(R.id.Home3_TabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.Home3_ViewPager)
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
        View rootView = inflater.inflate(R.layout.fragment_home_v3, null);
        ButterKnife.bind(this, rootView);

        initView();

        initTabLayout();

        return rootView;
    }

    private void initView() {
        AMapLocation location = DataApplication.getInstance().getLocation();
        if (location != null) {
            mLocationTextView.setText(location.getCity());
        }
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.setAdapter(new FindViewPagerAdater(getChildFragmentManager(), types));
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
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            }
            tab.setCustomView(textView);
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.colorAccent));
//                ((TextView) tab.getCustomView()).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.gray));
//                ((TextView) tab.getCustomView()).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @OnClick({R.id.Home3_Search, R.id.Home3_Message, R.id.Home3_Title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Home3_Search:
                ISkipActivityUtil.startIntent(getActivity(), SearchActivity.class);
                break;
            case R.id.Home3_Message:
                ISkipActivityUtil.startIntent(getActivity(), MessageActivity.class);
                break;

            case R.id.Home3_Title:
                Bundle bundle = new Bundle();
                bundle.putString("city", ISpfUtil.getValue(Constant.AMAP_LOCATION_CITY, "").toString());
                ISkipActivityUtil.startIntentForResult(getActivity(), LocationActivity.class, bundle, MainActivity.HOME_SELECT_CITY_REQUEST_CODE);
                break;
        }
    }
}
