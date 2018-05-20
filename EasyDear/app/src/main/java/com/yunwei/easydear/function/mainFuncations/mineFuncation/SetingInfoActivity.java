package com.yunwei.easydear.function.mainFuncations.mineFuncation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.dialog.CommPopupWindow;
import com.yunwei.easydear.function.account.LoginRegistActivity;
import com.yunwei.easydear.function.account.LoginRegistPagerViewPagerAdapter;
import com.yunwei.easydear.function.account.data.UserInfoEntity;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment.ModiflyNickFragment;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment.ModiflyPasswordFragment;
import com.yunwei.easydear.function.pictureFuncation.SelectPictureActivity;
import com.yunwei.easydear.function.pictureFuncation.data.PictureEntity;
import com.yunwei.easydear.utils.IActivityManage;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.ISpfUtil;
import com.yunwei.easydear.utils.ISystemUtil;
import com.yunwei.easydear.utils.IUtil;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.mineFuncation
 * @Description:设置界面
 * @date 2016/11/28 10:18
 */

public class SetingInfoActivity extends BaseActivity {

    @BindView(R.id.SetingInfoHeadView_iamgeView)
    ImageView headImageView;
    @BindView(R.id.setting_info_number)
    TextView umberText;
    @BindView(R.id.setting_info_nickname)
    TextView nickName;

    private CommPopupWindow commPopupWindow;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_seting_info);
        setToolbarTitle("设置");
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUI();
    }

    private void initUI() {
        UserInfoEntity entity = DataApplication.getInstance().getUserInfoEntity();
        if (entity == null) {
            return;
        }
        nickName.setText(entity.getNickName());
        umberText.setText(entity.getMobile());
        Glide.with(this).load(BuildConfig.DOMAI + entity.getImagery()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(headImageView));
    }

    @OnClick({R.id.SetingInfoHeadView_layout, R.id.SetingInfo_exit_btn, R.id.setting_info_about_us_container, R.id.setting_info_change_password_container, R.id.setting_info_nickname_container, R.id.setting_info_contact_us_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SetingInfoHeadView_layout:
                SelectPictureActivity.startIntent(this,1);
                break;
            case R.id.SetingInfo_exit_btn:
                IActivityManage.getInstance().exit();
                ISpfUtil.setValue(Constant.PSSWORD_KEY,"");
                LoginRegistActivity.startIntent(this, LoginRegistPagerViewPagerAdapter.VALUE_LOGIN);
                break;
            case R.id.setting_info_about_us_container:/*关于我们*/
                ISkipActivityUtil.startIntent(this,AboutActivity.class);
                break;
            case R.id.setting_info_change_password_container:/*修改密码*/
                UpdateActivity.startIntent(this, ModiflyPasswordFragment.MODIFLY_PASSWORD_FLAG,"密码修改",901);
                break;
            case R.id.setting_info_nickname_container:/*修改Nick*/
                UpdateActivity.startIntent(this, ModiflyNickFragment.MODIFLY_NICK_FLAG,"昵称修改",900);
                break;
            case R.id.setting_info_contact_us_container:/*联系我们*/
                View businessView = LayoutInflater.from(this).inflate(R.layout.dialog_tontact_layout, null);
                final TextView tell1=(TextView)businessView.findViewById(R.id.dialog_tontact_tel1);
                final TextView tell2=(TextView)businessView.findViewById(R.id.dialog_tontact_tel2);
                businessView.findViewById(R.id.dialog_tontact_calcel_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        commPopupWindow.dismiss();
                    }
                });
               tell1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ISystemUtil.callTelAction(SetingInfoActivity.this,tell1.getText().toString());
                    }
                });
                tell2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ISystemUtil.callTelAction(SetingInfoActivity.this,tell2.getText().toString());
                    }
                });

                commPopupWindow = new CommPopupWindow(businessView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                commPopupWindow.showAtButton(findViewById(R.id.Seting_root_view));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==SelectPictureActivity.SELECT_PICTURE_RESULT_CODE){
            List<PictureEntity> list=(List<PictureEntity>) data.getSerializableExtra("result");
            if (list!=null&&list.size()>0){
                Glide.with(this).load(IUtil.fitterUrl(list.get(0).getUrl())).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(headImageView));
            }
        }
    }
}