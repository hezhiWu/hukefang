package com.yunwei.easydear.function.mainFuncations.businessFunction;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleListAdapter;
import com.yunwei.easydear.function.mainFuncations.homeFuncation.ScrollPagerAdapter;
import com.yunwei.easydear.function.mainFuncations.membercenterFunction.MemberCenterActivity;
import com.yunwei.easydear.utils.ILog;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.IViewUtil;
import com.yunwei.easydear.view.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class BusinessActivity extends BaseActivity implements BusinessContact.BusinessView, PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.business_scroll_vp)
    ViewPager mScrollViewPager;
    @BindView(R.id.business_scroll_dot_layout)
    LinearLayout mDotLayout;

    @BindView(R.id.business_address)
    TextView mBusinessAddress;
    @BindView(R.id.business_tel)
    TextView mBusinessTelephone;
    @BindView(R.id.business_member_container)
    RelativeLayout mBusinessMemberContainer;
    @BindView(R.id.business_member_vip_level)
    TextView mBusinessMemberVipLevel;
    @BindView(R.id.business_member_card_amount)
    TextView mBusinessMemberCardAmount;
    @BindView(R.id.business_member_credit)
    TextView mBusinessMemberCredit;
    @BindView(R.id.business_cardlist_recyclerView)
    PullToRefreshRecyclerView mCardListRecyclerView;
    @BindView(R.id.business_article_listview)
    ListView mBusinessArticleListView;
    List<ArticleItemEntity> mBusinessArticleList;

    private String mBusinessNo;
    private String mBusinessName;
    private String mBusinessLogo;

    private BusinessPresenter mBusinessPresenter;
    private static final int BUSINESS_SCROLL_IMAGE = 1001;
    private List<ImageView> dots = new ArrayList<ImageView>();
    private ArrayList<ArticleItemEntity> mArticleItemList;
    private CardAdapter mCardAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_business);
        mBusinessNo = getIntent().getStringExtra("businessNo");
        mBusinessName = getIntent().getStringExtra("businessName");
        mBusinessLogo = getIntent().getStringExtra("businessLogo");

        setToolbarTitle(mBusinessName);
        setToolbarRightImage(R.mipmap.icon_add);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);
        initPresenter();
        initCardListRecyclerView();

        //TODO To be deleted!
        String[] urls = new String[4];
//        initScrollImages(urls);
//        setScrollViewListener();

        requestBusinessDetail();
        requestBusinessArticles();
    }

    private void initPresenter() {
        mBusinessPresenter = new BusinessPresenter(BusinessRemoteRepo.getInstance(), this);
    }

    /**
     * 初始化CardListRecyclerView
     */
    private void initCardListRecyclerView() {
        mCardAdapter = new CardAdapter(this);
        mCardListRecyclerView.setPullToRefreshListener(this);
        mCardListRecyclerView.setRecyclerViewAdapter(mCardAdapter);
        mCardListRecyclerView.startUpRefresh();
    }

    @Override
    public void onDownRefresh() {
        mBusinessPresenter.requestBusinessCardList();
    }

    @Override
    public void onPullRefresh() {

    }

    private void requestBusinessDetail() {
        mBusinessPresenter.requestBusinessDetail();
    }

    private void requestBusinessArticles() {
        mBusinessPresenter.requestBusinessArticles();
    }

    @Override
    public void setBusinessDetails(BusinessDetailEntity entity) {
        if (entity == null) {
            return;
        }
        String address = entity.getProvinceAdd() + entity.getCityAdd() + entity.getAreaAdd() + entity.getAddress();
        setToolbarTitle(entity.getBusinessName());
        mBusinessAddress.setText(address);
        mBusinessTelephone.setText(entity.getTelephone());
        if (entity.getIsVip().equalsIgnoreCase("Yes") || entity.getIsVip().equalsIgnoreCase("是")) {
            mBusinessMemberContainer.setVisibility(View.VISIBLE);
            mBusinessMemberVipLevel.setText(entity.getVipLevel() + "\n认证会员");
            mBusinessMemberCardAmount.setText("您有" + entity.getCardSize() + "张礼券");
            mBusinessMemberCredit.setText("您距离会员升级还需" + entity.getIntegral() + "积分");
        }
    }

    @Override
    public void setBusinessCardList(ArrayList<CardItemEntity> cardItems) {
        mCardAdapter.addItems(cardItems);
        mCardListRecyclerView.closeDownRefresh();
        mCardListRecyclerView.onLoadMoreFinish();
    }

    @Override
    public void setBusinessArticles(ArrayList<ArticleItemEntity> articleItems) {
        if (articleItems == null || articleItems.size() == 0) {
            return;
        }
        mBusinessArticleList = articleItems;
        ArticleListAdapter adapter = new ArticleListAdapter(this);
        adapter.setArticleItemList(articleItems);
        mBusinessArticleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        IViewUtil.setListViewHeightBasedOnChildren(mBusinessArticleListView);

        mBusinessArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                ArticleItemEntity articleItem = mBusinessArticleList.get(i);
//                bundle.putString("id", articleItem.getArticleId());
//                bundle.putString("businessNo", articleItem.getBusinessNO());
                ISkipActivityUtil.startIntent(BusinessActivity.this, ArticleActivity.class, bundle);
            }
        });
    }

    @Override
    public String getBusinessNo() {
        return mBusinessNo;
    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getUserNo();
    }

    /**
     * 初始化ScrollImage
     *
     * @param urls
     */
    private void initScrollImages(String[] urls) {

        ScrollPagerAdapter adapter = new ScrollPagerAdapter(this, mArticleItemList);
        mScrollViewPager.setAdapter(adapter);
        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(this);
            if (i == 0) {
                img.setImageResource(R.drawable.dot_focus);
            } else {
                img.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 30);

            // 加载到布局容器
            mDotLayout.addView(img, params);
            dots.add(img);
        }
        mHandler.sendEmptyMessageDelayed(BUSINESS_SCROLL_IMAGE, Constant.FIVE_SECONDES);
    }

    /**
     * 设置轮播ViewPager监听
     */
    private void setScrollViewListener() {
        mScrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                ILog.v(TAG, "onPageScrolled position = " + position);
            }

            @Override
            public void onPageSelected(int position) {
                int size = dots.size();
                ILog.d(TAG, "onPageSelected position = " + position);
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
//                ILog.v(TAG, "onPageScrollStateChanged state = " + state);
            }
        });
    }

    @OnClick({R.id.business_back, R.id.business_become_member})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.business_back:
                onBackPressed();
                break;
            case R.id.business_become_member:
                ISkipActivityUtil.startIntent(this, MemberCenterActivity.class);
                break;
        }
    }

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        ILog.v(TAG, "dispatchMessage msg.what = " + msg.what);
        switch (msg.what) {
            case BUSINESS_SCROLL_IMAGE:
                mHandler.removeMessages(BUSINESS_SCROLL_IMAGE);
                int pos = mScrollViewPager.getCurrentItem();
//                ILog.v(TAG, "dispatchMessage pos1 = " + pos);
                mScrollViewPager.setCurrentItem(++pos);
                ILog.d(TAG, "dispatchMessage pos2 = " + mScrollViewPager.getCurrentItem());
                mHandler.sendEmptyMessageDelayed(BUSINESS_SCROLL_IMAGE, Constant.FIVE_SECONDES);
                break;
            default:
                break;
        }
    }

}
