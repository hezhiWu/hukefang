package com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.dialog.DialogFactory;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.utils.ISpfUtil;
import com.yunwei.easydear.utils.IStringUtils;
import com.yunwei.easydear.view.MenuRadioView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.mineFuncation.fragment
 * @Description:足迹记录设置界面
 * @date 2016/11/28 10:22
 */

public class TrackSetingFragment extends BaseFragment {

    public final static String FRAGMENT_FLAG = "trackFragment";

    private static TrackSetingFragment instance;

    @BindView(R.id.trackSetingFragment_mode_text)
    TextView trackSetingFragmentModeText;
    private MenuRadioView menuSeletorView;

    private String[] modes;

    public static TrackSetingFragment newInstance() {
        if (instance == null) {
            instance = new TrackSetingFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modes = getResources().getStringArray(R.array.track_mode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mine_track_seting_fragment, null);
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        initUI();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        int mode = IStringUtils.toInt(ISpfUtil.getValue(Constant.TRACK_RECORD_MODE_KEY, Constant.TRACK_RECORD_MODE.DRIVE.getValue()).toString());
        if (mode == Constant.TRACK_RECORD_MODE.WALK.getValue()) {
            trackSetingFragmentModeText.setText(modes[0]);
        } else if (mode == Constant.TRACK_RECORD_MODE.RIDING.getValue()) {
            trackSetingFragmentModeText.setText(modes[1]);
        } else if (mode == Constant.TRACK_RECORD_MODE.DRIVE.getValue()) {
            trackSetingFragmentModeText.setText(modes[2]);
        }
    }

    @OnClick(R.id.trackSetingFragment_mode_layout)
    public void onClick() {
        menuSeletorView = new MenuRadioView(getActivity());
        menuSeletorView.setDataSource(modes);
        menuSeletorView.setSelectContent(trackSetingFragmentModeText.getText().toString().trim());
        DialogFactory.showMsgDialog(getActivity(), "记录方式", menuSeletorView, "确定", "取消", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackSetingFragmentModeText.setText(modes[menuSeletorView.getSelectePosition()]);
                ISpfUtil.setValue(Constant.TRACK_RECORD_MODE_KEY, menuSeletorView.getSelectePosition());
            }
        });
    }
}
