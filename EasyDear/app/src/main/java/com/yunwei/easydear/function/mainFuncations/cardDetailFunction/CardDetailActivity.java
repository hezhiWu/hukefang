package com.yunwei.easydear.function.mainFuncations.cardDetailFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.common.dialog.DialogFactory;
import com.yunwei.easydear.common.dialog.ToastUtil;
import com.yunwei.easydear.function.mainFuncations.businessFunction.CardItemEntity;
import com.yunwei.easydear.function.mainFuncations.cardpayFunction.CardPayActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class CardDetailActivity extends BaseActivity implements CardDetailContact.CardDetailView {

    private final String TAG = this.getClass().getSimpleName();

    private String cartNo;

    @BindView(R.id.card_detail_business_logo)
    ImageView mBusinessLogo;
    @BindView(R.id.card_detail_business_name)
    TextView mBusinessName;
    @BindView(R.id.card_detail_intro)
    TextView mAssociateName;
    @BindView(R.id.card_detail_price)
    TextView mCardPrice;
    @BindView(R.id.card_detail_validity_date)
    TextView mCardEndTime;
    @BindView(R.id.card_detail_number)
    TextView mCardNoText;

    private CardDetailPresenter mCardDetailPresenter;

    private CardItemEntity entity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_card_detail);
        cartNo = getIntent().getStringExtra("cardNo");
        setToolbarTitle("券详情");
        ButterKnife.bind(this);
        initPresenter();
    }

    private void initPresenter() {
        mCardDetailPresenter = new CardDetailPresenter(CardDetailRemoteRepo.getInstance(), this);
        mCardDetailPresenter.requestCardDetail(cartNo);
    }

    @Override
    public void showDialog() {
        loadDialog= DialogFactory.createLoadingDialog(this,"获取详情...");
    }

    @Override
    public void dimissDialog() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void onCardDetailInfoFailure(String error) {
        ToastUtil.showToast(this,error);
    }

    /**
     * 设置券详情信息
     */
    @Override
    public void onCardDetailInfoSuccess(CardItemEntity entity) {
        if (entity == null) {
            return;
        }
        this.entity=entity;
        mBusinessName.setText(entity.getCardName());
        mAssociateName.setText(entity.getAssociateName());
        mCardPrice.setText("总价 " + entity.getCardPrice());
        mCardEndTime.setText("有效期至 " + entity.getCardEndTime());
        mCardNoText.setText(entity.getCardNo());
        Glide.with(getApplicationContext()).load(BuildConfig.DOMAI+entity.getLogo()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(mBusinessLogo));
    }


    @OnClick({R.id.card_purchase})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_purchase:
                Bundle bundle=new Bundle();
                bundle.putSerializable("entity",entity);
                ISkipActivityUtil.startIntent(this, CardPayActivity.class,bundle);
                break;
        }
    }
}
