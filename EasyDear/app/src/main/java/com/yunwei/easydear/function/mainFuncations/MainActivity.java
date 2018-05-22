package com.yunwei.easydear.function.mainFuncations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.google.zxing.client.android.CaptureActivity;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.dialog.DialogFactory;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.common.eventbus.EventConstant;
import com.yunwei.easydear.common.eventbus.NoticeEvent;
import com.yunwei.easydear.function.business.BusinessListFragment;
import com.yunwei.easydear.function.dynamic.DynamicListFragment;
import com.yunwei.easydear.function.mainFuncations.data.soure.MainRemoteRepo;
import com.yunwei.easydear.function.mainFuncations.membershipFuncation.MembershipCodeFragment;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.MineFragment;
import com.yunwei.easydear.function.mainFuncations.findFuncation.FindFragment;
import com.yunwei.easydear.function.mainFuncations.qrcode.ScanQrFragment;
import com.yunwei.easydear.utils.IActivityManage;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.ISpfUtil;
import com.yunwei.easydear.view.MainBottomNavigationBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations
 * @Description:主界面
 * @date 2016/11/22 15:40
 */

public class MainActivity extends BaseActivity implements MainBottomNavigationBar.BottomTabSelectedListener, MainContract.MainView {
    private final String TAG = getClass().getSimpleName();

    /*模块标记*/
    private final int TAB_HOME = 0;
    private final int TAB_FIND = 1;
    private final int TAB_TRAINGCODE = 2;
    private final int TAB_MESSAGE = 3;
    private final int TAB_MINE = 4;


    @BindView(R.id.main_bottom_navigationBar)
    MainBottomNavigationBar mainBottomNavigationBar;

    private MainPresenter mMainPresenter;

    private int currentTab=0;

    public static final int HOME_SELECT_CITY_REQUEST_CODE = 1001;
    public static final int HOME_SEARCH_KEY_REQUEST_CODE = 1002;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        ButterKnife.bind(this);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 初始化
     */
    private void init() {
        initBottomNavigationBar();
//        initPresenter();
    }

    /**
     * 初始化BottomNavigationBar
     */
    private void initBottomNavigationBar() {
        mainBottomNavigationBar.initConfig(this, R.id.main_container_FrameLayout);
        mainBottomNavigationBar.addTabItem(R.mipmap.icon_main_tab_home_pr, R.string.main_home_tab)
                .addTabItem(R.mipmap.ic_dongtai, R.string.main_dynamic_tab)
                .addTabItem(R.mipmap.ic_car, R.string.main_block_tab)
                .addTabItem(R.mipmap.ic_scan, R.string.main_qr_tab)
                .addTabItem(R.mipmap.icon_main_tab_mine_pr, R.string.main_mine_tab);
        mainBottomNavigationBar.addFragment(new BusinessListFragment())
                .addFragment(new DynamicListFragment())
                .addFragment(MembershipCodeFragment.newInstance())
                .addFragment(ScanQrFragment.newInstance())
                .addFragment(MineFragment.newInstance());
        mainBottomNavigationBar.setDefaultFragment(0);
        mainBottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mMainPresenter = new MainPresenter(MainRemoteRepo.newInstance(), this);
//        mMainPresenter.reqQiNiuToken();
        mMainPresenter.startLocation();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
          /*将这一行注释掉，阻止activity保存fragment的状态*/
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        NoticeEvent event = new NoticeEvent();
        if (resultCode == RESULT_OK){
            switch (requestCode) {
                case HOME_SELECT_CITY_REQUEST_CODE:
                    event.setFlag(EventConstant.NOTICE_HOME_UPDATE_CITY);
                    event.setObj(data);
                    EventBus.getDefault().post(event);
                    break;
                case HOME_SEARCH_KEY_REQUEST_CODE:
                    event.setFlag(EventConstant.NOTICE_HOME_SEARCH);
                    event.setObj(data);
                    EventBus.getDefault().post(event);
                    break;
            }
        }
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case TAB_HOME:
                this.currentTab=position;
                setToolbarCenterTitle(R.string.main_home_tab);
                setToolbarVisibility(View.GONE);
                break;
            case TAB_FIND:
                setToolbarCenterTitle("商家动态");
                setToolbarVisibility(View.VISIBLE);
                break;
            case TAB_TRAINGCODE:
                this.currentTab=position;
                setToolbarCenterTitle("我的权益");
                setToolbarVisibility(View.VISIBLE);
                break;
            case TAB_MESSAGE:
                this.currentTab=position;
                setToolbarCenterTitle(R.string.main_message_tab);
                setToolbarVisibility(View.GONE);
                ISkipActivityUtil.startIntent(this, CaptureActivity.class);
                break;
            case TAB_MINE:
                this.currentTab=position;
                setToolbarCenterTitle(R.string.main_mine_tab);
                setToolbarVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void locationSuccess(AMapLocation location) {
        DataApplication.getInstance().setLocation(location);
        ISpfUtil.setValue(Constant.AMAP_LOCATION_CITY, location.getCity());
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE11);
        EventBus.getDefault().post(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showExitDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出Dialog
     */
    private void showExitDialog() {
        DialogFactory.showMsgDialog(this, getString(R.string.dialog_title_exit), getString(R.string.exit_msg) + getString(R.string.app_name) + "?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.gc();
                IActivityManage.getInstance().exit();
                System.exit(0);
            }
        });
    }
}
