package com.yunwei.easydear.function.dynamic;

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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.common.dialog.CommPopupWindow;
import com.yunwei.easydear.common.dialog.DialogFactory;
import com.yunwei.easydear.function.dynamic.data.DynamicDetailEntity;
import com.yunwei.easydear.function.dynamic.data.soure.DynamicDataSource;
import com.yunwei.easydear.function.dynamic.data.soure.DynamicRemoteRepo;
import com.yunwei.easydear.function.mainFuncations.articleFunction.AppInfo;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ShareAppAdapter;
import com.yunwei.easydear.utils.IAppUtil;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动态详情
 *
 * Created by Administrator on 2018/5/21.
 */

public class DynamicDetailsActivity extends BaseActivity implements DynamicDataSource.QueryDynamicDetailsCallBack {

    @BindView(R.id.Dynamic_Details_WebView)
    WebView DynamicDetailsWebView;
    @BindView(R.id.Dynamic_Details_DianZhan)
    TextView DynamicDetailsDianZhan;
    @BindView(R.id.Dynamic_Details_ChanKan)
    TextView DynamicDetailsChanKan;
    @BindView(R.id.Dynamic_Details_Title)
    TextView DynamicDetailsTitle;
    @BindView(R.id.Dynamic_Details_Logo)
    ImageView DynamicDetailsLogo;
    @BindView(R.id.Dynamic_Details_BusinessName)
    TextView DynamicDetailsBusinessName;
    @BindView(R.id.Dynamic_Details_ArticleImage)
    ImageView DynamicDetailsArticleImage;

    private int articleId = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_details_activity);
        ButterKnife.bind(this);

        setToolbarTitle("详情");

        setToolbarRightImage(R.mipmap.icon_shap);

        articleId = getIntent().getIntExtra("articleId", -1);
        DynamicRemoteRepo.getRemoteRepo().queryDynamicDetails(articleId, this);
    }

    private void initView(DynamicDetailEntity entity) {
        if (entity == null) {
            showToast("空数据");
            finish();
            return;
        }

        DynamicDetailsTitle.setText(entity.getTitle());
        DynamicDetailsBusinessName.setText(entity.getBusinessName());
        DynamicDetailsDianZhan.setText(entity.getArticleGood()+"");
        DynamicDetailsChanKan.setText(entity.getArticleForward()+"");

        DynamicDetailsWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        DynamicDetailsWebView.loadData(entity.getContent(),"text/html; charset=UTF-8", null);

        Glide.with(this).load(BuildConfig.IMG_DOMAI + entity.getLogo()).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(DynamicDetailsLogo));
        Glide.with(this).load(BuildConfig.IMG_DOMAI + entity.getArticleImage()).into(DynamicDetailsArticleImage);
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        shareAction();
    }

    /**
     * 分享
     */
    private void shareAction() {
        List<ResolveInfo> resolveInfos = IAppUtil.getShareAppInfos();
        final List<AppInfo> appInfoList = IAppUtil.getArticleShareAppInfos(resolveInfos);
        ShareAppAdapter shareAppAdapter = new ShareAppAdapter(this, appInfoList);

        View sharePopWinView = LayoutInflater.from(this).inflate(R.layout.dialog_share_popwin_layout, null);
        GridView shareGridView = (GridView) sharePopWinView.findViewById(R.id.dialog_share_app_gridview);
        shareGridView.setAdapter(shareAppAdapter);

        final CommPopupWindow commPopupWindow = new CommPopupWindow(sharePopWinView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        commPopupWindow.showAtButton(getCurrentFocus());

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
    @Override
    public void queryDynamicDetailsStart() {
        loadDialog = DialogFactory.createLoadingDialog(this);
    }

    @Override
    public void queryDynamicDetailsSuccess(DynamicDetailEntity entity) {
        initView(entity);
    }

    @Override
    public void queryDynamicDetailsFailure(int code, String msg) {
        showToast(msg);
        finish();
    }

    @Override
    public void queryDynamicDetailsEnd() {
        DialogFactory.dimissDialog(loadDialog);
    }
}
