package com.yunwei.easydear.function.mainFuncations.findFuncation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2016-12-24
 * Time: 12:15
 * Version:1.0
 */

public class FindViewPagerAdater extends FragmentPagerAdapter {

    private String[] types;

    public FindViewPagerAdater(FragmentManager fm){
        super(fm);
    }

    public FindViewPagerAdater(FragmentManager fm,String[] tabNames){
        super(fm);
        this.types=tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putString("type",types[position]);

        Fragment  fragment=new ChildTabContentFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return types.length;
    }
}
