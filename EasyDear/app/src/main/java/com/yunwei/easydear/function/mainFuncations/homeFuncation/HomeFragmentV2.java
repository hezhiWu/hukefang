package com.yunwei.easydear.function.mainFuncations.homeFuncation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.eventbus.EventConstant;
import com.yunwei.easydear.common.eventbus.NoticeEvent;
import com.yunwei.easydear.function.mainFuncations.MainActivity;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easydear.function.mainFuncations.findFuncation.FindViewPagerAdater;
import com.yunwei.easydear.function.mainFuncations.homeFuncation.data.HomeRemoteRepo;
import com.yunwei.easydear.function.mainFuncations.locationFunction.LocationActivity;
import com.yunwei.easydear.function.mainFuncations.messageFunction.MessageActivity;
import com.yunwei.easydear.function.mainFuncations.searchFunction.SearchActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.ISpfUtil;
import com.yunwei.easydear.widget.CoordinatorTabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-03-05
 * Time: 14:17
 * Version:1.0
 */

public class HomeFragmentV2 extends BaseFragment implements HomeContract.HomeView{
    private final String TAG=getClass().getSimpleName();

    private static HomeFragmentV2 instance;
    @BindView(R.id.HomeFragmentV2_viewPager)
    ViewPager mViewPager;
    @BindView(R.id.HomeFragmentV2_CoordinatorTabLayout)
    CoordinatorTabLayout HomeFragmentV2CoordinatorTabLayout;

    private TextView locationTextView;

    private String[] tabNames;
    private HomePresenter mHomePresenter;

    public static HomeFragmentV2 newInstance() {
        if (instance == null) {
            instance = new HomeFragmentV2();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tabNames = getResources().getStringArray(R.array.tab_tiltle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_v2, null);
        ButterKnife.bind(this, rootView);
        initTabLayout();
        initPresenter();
        initTitlteView();
        return rootView;
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mHomePresenter = new HomePresenter(HomeRemoteRepo.getInstance(), this);
        mHomePresenter.requestTopScrollArticles();
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout() {
        mViewPager.setAdapter(new FindViewPagerAdater(getChildFragmentManager(), tabNames));
        mViewPager.setOffscreenPageLimit(tabNames.length);
        mViewPager.setPageMargin(10);
        HomeFragmentV2CoordinatorTabLayout.setupWithViewPager(mViewPager,tabNames);
    }

    private void initTitlteView(){
        View view=LayoutInflater.from(getContext()).inflate(R.layout.home_title_layout,null);
        locationTextView=(TextView)view.findViewById(R.id.main_home_location_textView);
        locationTextView.setText(ISpfUtil.getValue(Constant.AMAP_LOCATION_CITY,"").toString());
        view.findViewById(R.id.main_home_msg_textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ISkipActivityUtil.startIntent(getActivity(), MessageActivity.class);
            }
        });
        view.findViewById(R.id.main_home_search_editText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ISkipActivityUtil.startIntent(getActivity(), SearchActivity.class);
                ((EditText)view).getText().clear();
                HomeFragmentV2CoordinatorTabLayout.requestFocus();
            }
        });
        locationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("city", ISpfUtil.getValue(Constant.AMAP_LOCATION_CITY,"").toString());
                ISkipActivityUtil.startIntentForResult(getActivity(), LocationActivity.class, bundle, MainActivity.HOME_SELECT_CITY_REQUEST_CODE);

            }
        });
        HomeFragmentV2CoordinatorTabLayout.setTitleView(view);
    }

    @Override
    public void setTopScrollArticles(ArrayList<ArticleItemEntity> articleItems) {
        ScrollPagerAdapter adapter=new ScrollPagerAdapter(getContext(),articleItems);
//        HomeFragmentV2CoordinatorTabLayout.setScrollPagerAdapter(adapter,articleItems.size());
    }

    @Subscribe
    public void onEventMainThread(NoticeEvent event) {
        switch (event.getFlag()) {
            case EventConstant.NOTICE_HOME_UPDATE_CITY:
                Intent intent = (Intent) event.getObj();
                String mCity = intent.getStringExtra("city");
                String mCityCode = intent.getStringExtra("city_code");
                if (intent.hasExtra("district") && intent.hasExtra("district_code")) {
                  String  mDistrictCode = intent.getStringExtra("district_code");
                    String  mDistrict = intent.getStringExtra("district");
                      locationTextView.setText(mDistrict);
                } else {
                    locationTextView.setText(mCity);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public String getProvince() {
        return null;
    }

    @Override
    public String getCity() {
        return null;
    }

    @Override
    public String getArea() {
        return null;
    }
}
