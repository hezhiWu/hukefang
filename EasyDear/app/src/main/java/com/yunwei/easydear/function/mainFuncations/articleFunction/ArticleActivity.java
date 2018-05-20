package com.yunwei.easydear.function.mainFuncations.articleFunction;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.common.dialog.CommPopupWindow;
import com.yunwei.easydear.function.mainFuncations.cardDetailFunction.CardDetailActivity;
import com.yunwei.easydear.function.mainFuncations.businessFunction.BusinessActivity;
import com.yunwei.easydear.function.mainFuncations.businessFunction.CardItemEntity;
import com.yunwei.easydear.utils.IAppUtil;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.IViewUtil;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/12.
 */

public class ArticleActivity extends BaseActivity implements ArticleContact.ArticleView {

    private final String TAG = this.getClass().getSimpleName();
    /*文章Id*/
    private String articleId;
    private String businessNo;

    @BindView(R.id.article_title)
    TextView mArticleTitle;
    @BindView(R.id.article_content)
    TextView mArticleContent;
    @BindView(R.id.article_webView)
    WebView mWebView;
    @BindView(R.id.article_business_textView)
    TextView mBusinessName;
    @BindView(R.id.article_pubtime)
    TextView mPubTime;
    @BindView(R.id.article_business_logo)
    ImageView mBusinessLogo;
    @BindView(R.id.article_business_imageview)
    ImageView mArticleImage;
    @BindView(R.id.article_forward)
    TextView mArticleForward;

    @BindView(R.id.article_discount_coupon_imageview)
    ImageView mCardImage;
    @BindView(R.id.article_card_name)
    TextView mCardName;
    @BindView(R.id.article_card_brief_intro)
    TextView mCardIntroduce;
    @BindView(R.id.article_card_price)
    TextView mCardPrice;
    @BindView(R.id.article_card_old_price)
    TextView mCardOldPrice;
    @BindView(R.id.article_listview)
    ListView mArticleListView;

    List<ArticleItemEntity> mArticleList;

    private ArticlePresenter mArticlePresenter;
    private ArticleItemEntity mArticleItemEntity;
    private CardItemEntity cardItemEntity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_article);
        articleId = getIntent().getStringExtra("id");
        businessNo = getIntent().getStringExtra("businessNo");
        setToolbarTitle("潮铺正文");
        setToolbarRightImage(R.mipmap.icon_shap);
        ButterKnife.bind(this);
        initPresenter();
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mArticlePresenter = new ArticlePresenter(ArticleRemoteRepo.getInstance(), this);
        /*获取文章详情*/
        mArticlePresenter.requestArticleDetail(articleId);
        /*卡券信息*/
        mArticlePresenter.requestLatestCardInfo(businessNo);
        /*商家软文信息*/
        mArticlePresenter.requestBusinessArticles(businessNo);
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        shareAction();
    }

    /**
     * 设置文章详情信息
     */
    @Override
    public void setArticleDetail(ArticleItemEntity entity) {
        if (entity == null) {
            return;
        }
        mArticleItemEntity = entity;
        mArticleTitle.setText(entity.getBusinessName());
        mBusinessName.setText(entity.getBusinessName());
//        mPubTime.setText(entity.getPubTime());
//        mArticleContent.setText(entity.getContent());
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
//        mWebView.loadData(entity.getContent(),"text/html; charset=UTF-8", null);
//        mArticleForward.setText(getString(R.string.article_detail_forward) + entity.getArticleForward());
        Glide.with(this).load(BuildConfig.IMG_DOMAI + entity.getLogo()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(mBusinessLogo));
        Glide.with(this).load(BuildConfig.IMG_DOMAI + entity.getSloganImages()).into(mArticleImage);
    }


    /**
     * 设置最新卡券信息
     */
    @Override
    public void setLatestCardInfo(ArrayList<CardItemEntity> cardItems) {
        if (cardItems == null || cardItems.size() == 0) {
            return;
        }
        cardItemEntity = cardItems.get(0);
        mCardName.setText(cardItemEntity.getCardName());
        mCardIntroduce.setText(cardItemEntity.getAssociateName());
        mCardPrice.setText("¥ " + cardItemEntity.getCardPrice());
        mCardOldPrice.setText(cardItemEntity.getCardOldPrice() == null ? "" : getString(R.string.article_old_price) + cardItemEntity.getCardOldPrice());
        Glide.with(this).load(BuildConfig.IMG_DOMAI + cardItemEntity.getLogo()).into(mCardImage);
    }

    /**
     * 设置商家软文信息
     *
     * @param businessArticleItems
     */
    @Override
    public void setBusinessArticles(ArrayList<ArticleItemEntity> businessArticleItems) {
        if (businessArticleItems == null && businessArticleItems.size() == 0) {
            return;
        }
        mArticleList = businessArticleItems;
        ArticleListAdapter adapter = new ArticleListAdapter(this);
        adapter.setArticleItemList(businessArticleItems);
        mArticleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        IViewUtil.setListViewHeightBasedOnChildren(mArticleListView);

        mArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                ArticleItemEntity articleItem = mArticleList.get(i);
                bundle.putString("id", articleItem.getBusinessNo());
                bundle.putString("businessNo", articleItem.getBusinessNo());
                ISkipActivityUtil.startIntent(ArticleActivity.this, ArticleActivity.class, bundle);
                ArticleActivity.this.finish();
            }
        });
    }

    @OnClick({R.id.article_discount_purchase, R.id.article_more_info, R.id.article_to_discount_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.article_to_discount_detail:/*券详情*/
            case R.id.article_discount_purchase:
                Bundle bundle = new Bundle();
                bundle.putString("cardNo", cardItemEntity.getCardNo());
                ISkipActivityUtil.startIntent(this, CardDetailActivity.class, bundle);
                break;
            case R.id.article_more_info:
                Bundle businessBundle = new Bundle();
                businessBundle.putString("businessNo", mArticleItemEntity.getBusinessNo());
                businessBundle.putString("businessName", mArticleItemEntity.getBusinessName());
                businessBundle.putString("businessLogo", mArticleItemEntity.getLogo());
                ISkipActivityUtil.startIntent(this, BusinessActivity.class, businessBundle);
                break;
        }
    }

    private void shareAction() {
        List<ResolveInfo> resolveInfos = IAppUtil.getShareAppInfos();
        final List<AppInfo> appInfoList = IAppUtil.getArticleShareAppInfos(resolveInfos);
        ShareAppAdapter shareAppAdapter = new ShareAppAdapter(ArticleActivity.this, appInfoList);

        View sharePopWinView = LayoutInflater.from(ArticleActivity.this).inflate(R.layout.dialog_share_popwin_layout, null);
        GridView shareGridView = (GridView) sharePopWinView.findViewById(R.id.dialog_share_app_gridview);
        shareGridView.setAdapter(shareAppAdapter);

        final CommPopupWindow commPopupWindow = new CommPopupWindow(sharePopWinView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        commPopupWindow.showAtButton(ArticleActivity.this.findViewById(R.id.activity_article_root));

        shareGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setComponent(new ComponentName(appInfoList.get(i).getPackageName(), appInfoList.get(i).getActivityName()));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent.putExtra(Intent.EXTRA_TEXT, "喜欢我就点我吧");
                intent.putExtra(Intent.EXTRA_TEXT, "http://society.qq.com/a/20161222/035882.htm#p=1");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
                commPopupWindow.dismiss();
            }
        });
    }
}
