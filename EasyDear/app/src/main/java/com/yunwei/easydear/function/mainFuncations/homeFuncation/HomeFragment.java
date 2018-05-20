package com.yunwei.easydear.function.mainFuncations.homeFuncation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.yunwei.easydear.utils.ILog;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.ISpfUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:首页
 * @date 2016/11/22 18:12
 */

public class HomeFragment extends BaseFragment implements HomeContract.HomeView {
    private final String TAG = getClass().getSimpleName();

    private static HomeFragment fragment;

    @BindView(R.id.main_home_location_textView)
    TextView mLocationTextView;
    @BindView(R.id.main_home_search_textView)
    TextView mSearchTextView;
    @BindView(R.id.home_scroll_vp)
    ViewPager mScrollViewPager;
    @BindView(R.id.home_scroll_dot_layout)
    LinearLayout mDotLayout;
    @BindView(R.id.home_scroll_container)
    LinearLayout mScrollLayout;
    @BindView(R.id.home_select_district_textView)
    TextView mSelectDistrictTextView;
    @BindView(R.id.find_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.find_viewPager)
    ViewPager mViewPager;
//    @BindView(R.id.tab_child_recyclerView)
//    PullToRefreshRecyclerView mRecyclerView;

    private String[] tabNames;
    private HomePresenter mHomePresenter;
    private ArrayList<ArticleItemEntity> mTopScrollArticles;

    private List<ImageView> dots = new ArrayList<ImageView>();
    private static final int HOME_SCROLL_IMAGE = 1010;

    private String mCity;
    private String mCityCode;
    private String mDistrict;
    private String mDistrictCode;
    private String mSearchKey = "";

    public static HomeFragment newInstance() {
        if (fragment == null) {
            fragment = new HomeFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tabNames = getResources().getStringArray(R.array.tab_tiltle);
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_home, null);
        ButterKnife.bind(this, rootView);

        initLocationCity();
        initTabLayout();
        setScrollViewListener();
        requestTopScrollArticles();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mHomePresenter = new HomePresenter(HomeRemoteRepo.getInstance(), this);
    }

    private void requestTopScrollArticles() {
        mHomePresenter.requestTopScrollArticles();
    }

    /**
     * EventBus 更新 Location City
     */
    @Subscribe
    public void onEventMainThread(NoticeEvent event) {
        if (event == null) {
            return;
        }
        switch (event.getFlag()) {
            case EventConstant.NOTICE11:
                setLocationCity();
                break;
            case EventConstant.NOTICE_HOME_UPDATE_CITY:
                Intent intent = (Intent) event.getObj();
                mCity = intent.getStringExtra("city");
                mCityCode = intent.getStringExtra("city_code");
                if (intent.hasExtra("district") && intent.hasExtra("district_code")) {
                    mDistrictCode = intent.getStringExtra("district_code");
                    mDistrict = intent.getStringExtra("district");
                    mLocationTextView.setText(mDistrict);
                    mSelectDistrictTextView.setText(mDistrict);
                } else {
                    mLocationTextView.setText(mCity);
                    mSelectDistrictTextView.setText("");
                    mDistrict = "";
                    mDistrictCode = "";
                }
                mSearchKey = "";
                requestTopScrollArticles();
                break;
            case EventConstant.NOTICE_HOME_SEARCH:
                Intent search = (Intent) event.getObj();
                mSearchKey = search.getStringExtra("search_key");
                ILog.v(TAG, "Current Search Key = " + mSearchKey);
                requestTopScrollArticles();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化所在城市
     */
    private void initLocationCity() {
        String adCode = (String) ISpfUtil.getValue(Constant.AMAP_LOCATION_ADCODE, "");
        int len = adCode.length();
        if (len >= 4) {
            mCityCode = adCode.substring(0, 4);
        } else {
            mCityCode = adCode;
        }
        mCity = (String) ISpfUtil.getValue(Constant.AMAP_LOCATION_CITY, "");
        mLocationTextView.setText(mCity);
        ILog.v(TAG, "Init Location City: " + mCity + ", adCode = " + adCode);
    }

    /**
     * 设置所在城市
     */
    private void setLocationCity() {
        String city = (String) ISpfUtil.getValue(Constant.AMAP_LOCATION_CITY, "");
        mLocationTextView.setText(city);
        ILog.v(TAG, "setLocationCity: " + city);
    }

    @OnClick({R.id.main_home_location_textView, R.id.home_select_district, R.id.main_home_search_textView, R.id.main_home_msg_textView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_home_location_textView:
            case R.id.home_select_district:
                Bundle bundle = new Bundle();
                bundle.putString("city", mCity);
                bundle.putString("city_code", mCityCode);
                ISkipActivityUtil.startIntentForResult(getActivity(), LocationActivity.class, bundle, MainActivity.HOME_SELECT_CITY_REQUEST_CODE);
                break;
            case R.id.main_home_search_textView:
                ISkipActivityUtil.startIntentForResult(getActivity(), SearchActivity.class, MainActivity.HOME_SEARCH_KEY_REQUEST_CODE);
                break;
            case R.id.main_home_msg_textView:
                ISkipActivityUtil.startIntent(getActivity(), MessageActivity.class);
                break;
        }
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout() {
        mViewPager.setAdapter(new FindViewPagerAdater(getChildFragmentManager(), tabNames));
        mViewPager.setOffscreenPageLimit(tabNames.length);
        mViewPager.setPageMargin(10);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        initTabView(mTabLayout);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.colorAccent));
                ((TextView) tab.getCustomView()).setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.gray));
                ((TextView) tab.getCustomView()).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 初始化TabView
     *
     * @param tabLayout
     */
    private void initTabView(TabLayout tabLayout) {
        for (int i = 0; i < tabNames.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.title_tab_layout, null);
            TextView textView = (TextView) view.findViewById(R.id.title_tab_textView);
            textView.setText(tabNames[i]);
            /*设置默认选择*/
            if (i == 0) {
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            }
            tab.setCustomView(textView);
        }
    }

    /**
     * 设置顶部轮播文章列表
     */
    public void setTopScrollArticles(ArrayList<ArticleItemEntity> articleItems) {
        if (articleItems == null || articleItems.size() == 0) {
            return;
        }
        mTopScrollArticles = new ArrayList<ArticleItemEntity>();
        for (ArticleItemEntity entity : articleItems) {
            mTopScrollArticles.add(entity);
        }

        initScrollImagesLayout(mTopScrollArticles);
    }

    /**
     * 初始化ScrollImage
     *
     * @param articleList
     */
    private void initScrollImagesLayout(ArrayList<ArticleItemEntity> articleList) {
        ScrollPagerAdapter adapter = new ScrollPagerAdapter(getContext(), articleList);
        mScrollViewPager.setAdapter(adapter);
        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(getContext());
            if (i == 0) {
                img.setImageResource(R.drawable.dot_focus);
            } else {
                img.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 30);

            // 加载到布局容器
            mDotLayout.addView(img, params);
            dots.add(img);
        }
        mHandler.sendEmptyMessageDelayed(HOME_SCROLL_IMAGE, Constant.FIVE_SECONDES);
    }

    /**
     * 设置轮播ViewPager监听
     */
    private void setScrollViewListener() {
        mScrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                ILog.v(TAG, "onPageScrolled position = " + position);
            }

            @Override
            public void onPageSelected(int position) {
                int size = dots.size();
                ILog.d(TAG, "onPageSelected position = " + position);
                for (int i = 0; i < size; i++) {
                    ImageView img = dots.get(i);
                    if (i == position % size) {
                        img.setImageResource(R.drawable.dot_focus);
                    } else {
                        img.setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                ILog.v(TAG, "onPageScrollStateChanged state = " + state);
            }
        });
    }

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case HOME_SCROLL_IMAGE:
                mHandler.removeMessages(HOME_SCROLL_IMAGE);
                int pos = mScrollViewPager.getCurrentItem();
                mScrollViewPager.setCurrentItem(++pos);
                mHandler.sendEmptyMessageDelayed(HOME_SCROLL_IMAGE, Constant.FIVE_SECONDES);
                break;
            default:
                break;
        }
    }

    @Override
    public String getProvince() {
        return "";
    }

    @Override
    public String getCity() {
        return mCity;
    }

    @Override
    public String getArea() {
        return mDistrict == null ? "" : mDistrict;
    }
}
