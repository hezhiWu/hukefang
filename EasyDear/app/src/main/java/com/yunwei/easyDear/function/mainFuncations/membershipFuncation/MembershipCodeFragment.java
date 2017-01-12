package com.yunwei.easyDear.function.mainFuncations.membershipFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.view.RoundedBitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 13:46
 * Version:1.0
 */

public class MembershipCodeFragment extends BaseFragment {

    private static MembershipCodeFragment fagment;
    @BindView(R.id.MembershiFrgment_bg_iv)
    ImageView MembershiFrgmentBgIv;
    @BindView(R.id.MembershipFragment_headView_iv)
    ImageView headView;
    @BindView(R.id.MembershipFragment_Name_textView)
    TextView userName;
    @BindView(R.id.MembershipFragment_qr_iv)
    ImageView qrImageView;

    public static MembershipCodeFragment newInstance() {
        if (fagment == null) {
            fagment = new MembershipCodeFragment();
        }
        return fagment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_frgment_membership, null);
        ButterKnife.bind(this, rootView);
        initUI();
        return rootView;
    }

    private void initUI(){
        Glide.with(getContext()).load("http://www.popoffices.com/design/pin/opn/006.jpg").into(MembershiFrgmentBgIv);
        Glide.with(getContext()).load("http://www.wendu.com/upload/12-02-06/dizhiqr.jpeg").into(qrImageView);
        Glide.with(getContext()).load("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg").asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(headView));
    }

    @OnClick({R.id.MembershipFragment_order_layout,R.id.MembershipFragment_block_layout,R.id.MembershipFragment_ll_layout})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.MembershipFragment_ll_layout:
            break;
            case R.id.MembershipFragment_block_layout:
                break;
            case R.id.MembershipFragment_order_layout:
                break;
        }
    }
}