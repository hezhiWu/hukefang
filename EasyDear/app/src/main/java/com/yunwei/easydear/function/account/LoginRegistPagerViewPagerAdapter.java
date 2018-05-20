package com.yunwei.easydear.function.account;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 09:13
 * Version:1.0
 */

public class LoginRegistPagerViewPagerAdapter extends FragmentPagerAdapter {
    public static final int VALUE_LOGIN = 0;
    public static final int VALUE_REGIST = 1;

    public LoginRegistPagerViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new LoginFragment();
                break;
            case 1:
                fragment = new RegistFragment();
                break;
        }
        return fragment;
    }
}
