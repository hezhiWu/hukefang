package com.yunwei.easydear.function.business;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.common.dialog.DialogFactory;
import com.yunwei.easydear.function.business.data.BusinessDetailEntity;
import com.yunwei.easydear.function.business.data.soure.BusinessDataSource;
import com.yunwei.easydear.function.business.data.soure.BusinessRemoreRepo;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easydear.function.mainFuncations.homeFuncation.ScrollPagerAdapter;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商家详情
 * <p>
 * Created by Administrator on 2018/5/21.
 */

public class BusinessDetailsActivity extends BaseActivity implements BusinessDataSource.QueryBusinessDetailCallBack {

    @BindView(R.id.BusinessDetalis_ViewPager)
    ViewPager BusinessDetalisViewPager;
    @BindView(R.id.BusinessDetalis_Dot_Layout)
    LinearLayout BusinessDetalisDotLayout;
    @BindView(R.id.BusinessDetalis_Logo_Image)
    ImageView BusinessDetalisLogoImage;
    @BindView(R.id.BusinessDetalis_BusinessName)
    TextView BusinessDetalisBusinessName;
    @BindView(R.id.BusinessDetalis_BrandName)
    TextView BusinessDetalisBrandName;
    @BindView(R.id.BusinessDetalis_Addr)
    TextView BusinessDetalisAddr;

    private List<ImageView> dots = new ArrayList<ImageView>();
    private ArticleItemEntity itemEntity;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            // 需要做的事:发送消息
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        }
    };

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case 1:
                BusinessDetalisViewPager.setCurrentItem(BusinessDetalisViewPager.getCurrentItem() + 1);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_details_activity);
        ButterKnife.bind(this);

        setToolbarVisibility(View.GONE);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("businessNo")) {
            BusinessRemoreRepo.getRemoreRepo().queryBusinessDetail(getIntent().getExtras().getString("businessNo"), this);
        }
    }

    private void initView(BusinessDetailEntity entity) {
        if (entity == null) {
            showToast("Data Null");
            return;
        }

        Glide.with(this).load(BuildConfig.IMG_DOMAI + entity.getLogo()).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(BusinessDetalisLogoImage));

        BusinessDetalisBusinessName.setText(entity.getBusinessName());
        BusinessDetalisBrandName.setText(entity.getBrandName());
        BusinessDetalisAddr.setText(entity.getAddress());

        ArrayList<ArticleItemEntity> list = new ArrayList<>();

        itemEntity = new ArticleItemEntity();
        itemEntity.setLogo(entity.getBusinessImages());
        list.add(itemEntity);

        itemEntity = new ArticleItemEntity();
        itemEntity.setLogo(entity.getBusinessImagesNext());
        list.add(itemEntity);

        itemEntity = new ArticleItemEntity();
        itemEntity.setLogo(entity.getBusinessImagesLast());
        list.add(itemEntity);

        initScrollImagesLayout(list);
    }

    /**
     * 初始化ScrollImage
     */
    private void initScrollImagesLayout(ArrayList<ArticleItemEntity> articleList) {
        BusinessDetalisViewPager.setAdapter(new ScrollPagerAdapter(this, articleList));

        for (int i = 0; i < articleList.size(); i++) {
            ImageView img = new ImageView(this);
            if (i == 0) {
                img.setImageResource(R.drawable.dot_focus);
            } else {
                img.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 30);

            // 加载到布局容器
            BusinessDetalisDotLayout.addView(img, params);

            dots.add(img);
        }

        BusinessDetalisViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int size = dots.size();
                for (int i = 0; i < size; i++) {
                    ImageView img = dots.get(i);
                    if (i == position % size) {
                        img.setImageResource(R.drawable.dot_focus);
                    } else {
                        img.setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        timer.schedule(task, 4000, 4000);

    }

    @OnClick({R.id.BusinessDetalis_Back_Image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BusinessDetalis_Back_Image:
                finish();
                break;
            case R.id.BusinessDetalis_Tel:
                break;
            case R.id.BusinessDetalis_Location:
                break;
        }
    }

    @Override
    public void queryBusinessDetailStart() {
        loadDialog = DialogFactory.createLoadingDialog(this);
    }

    @Override
    public void queryBusinessDetailSuccess(BusinessDetailEntity entity) {
        initView(entity);
    }

    @Override
    public void queryBusinessDetailFailure(int code, String msg) {
        showToast(msg);

        finish();
    }

    @Override
    public void queryBusinessDetailEnd() {
        DialogFactory.dimissDialog(loadDialog);
    }
}
