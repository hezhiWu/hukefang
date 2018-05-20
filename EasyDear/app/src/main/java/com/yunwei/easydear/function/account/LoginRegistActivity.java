package com.yunwei.easydear.function.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe:登录注册界面
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 09:00
 * Version:1.0
 */

public class LoginRegistActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.loginRegistActivity_viewPager)
    ViewPager loginRegistViewPager;
    @BindView(R.id.loginRegist_login_line)
    TextView loginRegistLoginLine;
    @BindView(R.id.loginRegist_regist_line)
    TextView loginRegistRegistLine;
    @BindView(R.id.loginResist_login_textView)
    TextView loginResistLoginTextView;
    @BindView(R.id.loginResist_regist_textView)
    TextView loginResistRegistTextView;

    private LoginRegistPagerViewPagerAdapter adapter;
    private static int currentPage = 0;

    public static void startIntent(Context context, int page) {
        currentPage = page;
        ISkipActivityUtil.startIntent(context, LoginRegistActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login_regist);
        setToolbarVisibility(View.GONE);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        loginRegistViewPager.setAdapter(new LoginRegistPagerViewPagerAdapter(getSupportFragmentManager()));
        loginRegistViewPager.setPageMargin(10);
        switchPage(currentPage);
        loginRegistViewPager.addOnPageChangeListener(this);
    }

    @OnClick({R.id.loginRegist_finsh_iv, R.id.loginRegist_login_layout, R.id.loginRegist_regist_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginRegist_finsh_iv:
                finish();
                break;
            case R.id.loginRegist_login_layout:
                switchPage(LoginRegistPagerViewPagerAdapter.VALUE_LOGIN);
                break;
            case R.id.loginRegist_regist_layout:
                switchPage(LoginRegistPagerViewPagerAdapter.VALUE_REGIST);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switchPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void switchPage(int page) {
        switch (page) {
            case LoginRegistPagerViewPagerAdapter.VALUE_LOGIN:
                loginRegistLoginLine.setVisibility(View.VISIBLE);
                loginRegistRegistLine.setVisibility(View.GONE);

                loginResistLoginTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                loginResistRegistTextView.setTextColor(getResources().getColor(R.color.gray));
                break;
            case LoginRegistPagerViewPagerAdapter.VALUE_REGIST:
                loginRegistLoginLine.setVisibility(View.GONE);
                loginRegistRegistLine.setVisibility(View.VISIBLE);

                loginResistLoginTextView.setTextColor(getResources().getColor(R.color.gray));
                loginResistRegistTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
        }
        loginRegistViewPager.setCurrentItem(page);
    }
}
