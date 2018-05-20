package com.yunwei.easydear.function.mainFuncations.findFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.common.eventbus.EventConstant;
import com.yunwei.easydear.common.eventbus.NoticeEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:任务主界面
 * @date 2016/11/22 18:12
 */

public class FindFragment extends BaseFragment {

    private final String TAG = getClass().getSimpleName();

    private static FindFragment fragment;

    @BindView(R.id.find_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.find_viewPager)
    ViewPager mViewPager;

    private String[] tabNames;

    public static FindFragment newInstance() {
        if (fragment == null) {
            fragment = new FindFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabNames=getResources().getStringArray(R.array.tab_tiltle);
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
                ((TextView)tab.getCustomView()).setTextColor(getResources().getColor(R.color.colorAccent));
//                ((TextView)tab.getCustomView()).setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
//                int tabPosition = tab.getPosition();
//                requestArticleList(tabPosition);
//                Log.d(TAG, "----------> Find selected!! tabPosition = "  + tabPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView)tab.getCustomView()).setTextColor(getResources().getColor(R.color.gray));
//                ((TextView)tab.getCustomView()).setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 初始化TabView
     * @param tabLayout
     */
    private void initTabView(TabLayout tabLayout){
        for (int i=0;i<tabNames.length;i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.title_tab_layout, null);
            TextView textView=(TextView)view.findViewById(R.id.title_tab_textView);
            textView.setText(tabNames[i]);
            /*设置默认选择*/
            if (i==0){
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
//                requestArticleList(0);
            }
            tab.setCustomView(textView);
        }
    }

    /**
     * 通知ChildTabFragment获取首页文章列表
     */
    private void requestArticleList(int position) {
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE12);
        event.setNum(position);
        EventBus.getDefault().post(event);
    }
}
