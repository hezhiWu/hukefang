package com.yunwei.easydear.function.mainFuncations.mineFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.dialog.CommPopupWindow;
import com.yunwei.easydear.function.account.data.UserInfoEntity;
import com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.CardEntity;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.adapter.BusinessAdapter;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment.MineContact;
import com.yunwei.easydear.function.mainFuncations.mycardlistFunction.MyCardActivity;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.BusinessContract;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.MemberBusinessPresenter;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.MyMemberActivity;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;
import com.yunwei.easydear.function.mainFuncations.myorderlistFunction.MyOrderActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.ISystemUtil;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:我的主界面
 * @date 2016/11/22 18:12
 */

public class MineFragment extends BaseFragment implements MineContact.MineView, BusinessContract.BusinessView {

    private static MineFragment fragment;
    @BindView(R.id.mineFragment_user_nick_tv)
    TextView mineFragmentUserNickTv;
    @BindView(R.id.mineFragment_user_headview_iv)
    ImageView mineFragmentUserHeadviewIv;
    @BindView(R.id.mine_card_amount)
    TextView mMineCardAmount;
    @BindView(R.id.mine_business_amount)
    TextView mMineBusinessAmount;
    @BindView(R.id.mineFragment_userInfo_layout)
    RelativeLayout mineFragmentUserInfoLayout;
    @BindView(R.id.mineFragment_business_gridView)
    GridView mineFragmentBusinessGridView;
    @BindView(R.id.mineFragment_tontact_layout)
    RelativeLayout mineFragmentTontactLayout;

    private CommPopupWindow commPopupWindow;

    private MinePresenter minePresenter;
    private MemberBusinessPresenter memberBusinessPresenter;

    private BusinessAdapter businessAdapter;

    public static MineFragment newInstance() {
        if (fragment == null) {
            fragment = new MineFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memberBusinessPresenter = new MemberBusinessPresenter(this);
        minePresenter = new MinePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_mine, null);
        ButterKnife.bind(this, rootView);
        initGridView();
        requestMyCardBusinessAmount();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUI();
    }

    private void initUI() {
        UserInfoEntity entity = DataApplication.getInstance().getUserInfoEntity();
        mineFragmentUserNickTv.setText(entity.getNickName());
        Glide.with(getActivity()).load(BuildConfig.DOMAI + entity.getImagery()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(mineFragmentUserHeadviewIv));
    }

    private void requestMyCardBusinessAmount() {
        UserInfoEntity entity = DataApplication.getInstance().getUserInfoEntity();
        if (entity == null || mineFragmentUserInfoLayout.getVisibility() == View.GONE) {
            return;
        }
        minePresenter.requestMyCardAmount();
        minePresenter.requestMyBusinessAmount();
    }

    /**
     * 初始化会员默认列表
     */
    private void initGridView() {
        businessAdapter = new BusinessAdapter(getActivity());
        mineFragmentBusinessGridView.setAdapter(businessAdapter);
        memberBusinessPresenter.reqBusinessListAction();
    }

    @Override
    public void setCardAmount(CardEntity cardEntity) {
        mMineCardAmount.setText("" + cardEntity.getCardSize());
    }

    @Override
    public void setBusinessAmount(com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.BusinessEntity businessEntity) {
        mMineBusinessAmount.setText("" + businessEntity.getBusinessSize());
    }

    @OnClick({R.id.mineFragment_setting_tv, R.id.mine_card_amount_container, R.id.mine_business_amount_container,
            R.id.mineFragment_all_order_layout, R.id.mineFragment_all_business_layout, R.id.mineFragment_tontact_layout, R.id.mineFragment_into_business_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mineFragment_setting_tv:
                ISkipActivityUtil.startIntent(getActivity(), SetingInfoActivity.class);
                break;
            case R.id.mine_card_amount_container:
                ISkipActivityUtil.startIntent(getActivity(), MyCardActivity.class);
                break;
            case R.id.mine_business_amount_container:
                ISkipActivityUtil.startIntent(getActivity(), MyMemberActivity.class);
                break;
            case R.id.mineFragment_all_order_layout:
                ISkipActivityUtil.startIntent(getActivity(), MyOrderActivity.class);
                break;
            case R.id.mineFragment_all_business_layout:/*会员商家*/
                ISkipActivityUtil.startIntent(getActivity(), MyMemberActivity.class);
                break;
            case R.id.mineFragment_tontact_layout:/*联系客户*/
                View tontactView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_tontact_layout, null);
                final TextView tell11 = (TextView) tontactView.findViewById(R.id.dialog_tontact_tel1);
                final TextView tell22 = (TextView) tontactView.findViewById(R.id.dialog_tontact_tel2);
                tontactView.findViewById(R.id.dialog_tontact_calcel_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        commPopupWindow.dismiss();
                    }
                });
                tell11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ISystemUtil.callTelAction(getContext(), tell11.getText().toString());
                    }
                });
                tell22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ISystemUtil.callTelAction(getContext(), tell22.getText().toString());
                    }
                });
                commPopupWindow = new CommPopupWindow(tontactView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                commPopupWindow.showAtButton(getView());
                break;
            case R.id.mineFragment_into_business_layout:/*联系商家*/
                View businessView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_tontact_layout, null);
                final TextView tell1 = (TextView) businessView.findViewById(R.id.dialog_tontact_tel1);
                final TextView tell2 = (TextView) businessView.findViewById(R.id.dialog_tontact_tel2);
                businessView.findViewById(R.id.dialog_tontact_calcel_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        commPopupWindow.dismiss();
                    }
                });
                tell1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ISystemUtil.callTelAction(getContext(), tell1.getText().toString());
                    }
                });
                tell2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ISystemUtil.callTelAction(getContext(), tell1.getText().toString());
                    }
                });
                commPopupWindow = new CommPopupWindow(businessView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                commPopupWindow.showAtButton(getView());
                break;
        }
    }

    @Override
    public void onBusinessStart() {

    }

    @Override
    public void onBusinessEnd() {

    }

    @Override
    public void onBusinessSuccess(List<BusinessEntity> list) {
        businessAdapter.addData(list);
    }

    @Override
    public void onBusinessFaliure(String error) {

    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getUserNo();
    }

    @Override
    public int getPageSize() {
        return 1;
    }

    @Override
    public int getPageCount() {
        return 4;
    }

}
