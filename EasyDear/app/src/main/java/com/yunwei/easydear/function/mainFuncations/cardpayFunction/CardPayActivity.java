package com.yunwei.easydear.function.mainFuncations.cardpayFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.common.alipay.AliPayService;
import com.yunwei.easydear.function.mainFuncations.businessFunction.CardItemEntity;
import com.yunwei.easydear.utils.IUtil;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/17.
 */

public class CardPayActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.card_pay_business_name)
    TextView businessNameTextView;
    @BindView(R.id.card_pay_base_content)
    TextView countentTextView;
    @BindView(R.id.card_pay_validity_date)
    TextView validityDateTextView;
    @BindView(R.id.card_pay_number)
    TextView billNumberTextView;
    @BindView(R.id.card_pay_qrcode)
    ImageView rqImageView;
    @BindView(R.id.card_pay_business_logo)
    ImageView businessLogo;

    private CardItemEntity entity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_card_pay);
        entity = (CardItemEntity) getIntent().getSerializableExtra("entity");
        setToolbarTitle("券二维码");
        ButterKnife.bind(this);
        initUI();
    }

    /**
     * 初始化界面
     */
    private void initUI() {
        if (entity == null) {
            return;
        }
        businessNameTextView.setText(entity.getBusinessName());
        countentTextView.setText(entity.getAssociateName() + " | 总价" + entity.getCardPrice());
        validityDateTextView.setText("有效期至 " + entity.getCardEndTime());
        billNumberTextView.setText(entity.getCardNo());
        rqImageView.setImageBitmap(IUtil.createQRImage(entity.getCardNo(), 80, 80));
        Glide.with(getApplicationContext()).load(BuildConfig.DOMAI + entity.getLogo()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(businessLogo));

    }


    @OnClick({R.id.card_pay_backto_business})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_pay_backto_business:
//                Bundle bundle = new Bundle();
//                bundle.putString("title", entity.getBusinessName());
//                bundle.putString("businessNo", entity.getBusinessNO());
//                ISkipActivityUtil.startIntent(this, BusinessActivity.class, bundle);
                AliPayService.getInstance().pay(this,"AAA");
                break;
        }
    }
}
