package com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.utils.ISystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.mineFuncation.fragment
 * @Description:关于界面
 * @date 2016/11/28 10:23
 */

public class AboutFragment extends BaseFragment {

    public final static String FRAGMENT_FLAG = "aboutFragment";

    @BindView(R.id.version_text)
    TextView versionText;

    private static AboutFragment instance;

    public static AboutFragment newInstance() {
        if (instance == null) {
            instance = new AboutFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mine_about_fragment, null);
        ButterKnife.bind(this, rootView);
        versionText.setText(getString(R.string.app_name) + "V" + ISystemUtil.getVersionName(getActivity()));
        return rootView;
    }

    @OnClick(R.id.check_app_text)
    public void onClick() {
    }
}
