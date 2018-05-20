package com.yunwei.easydear.function.mainFuncations.homeFuncation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easydear.utils.ISkipActivityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJH on 2017/1/3.
 */

public class ScrollPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private List<ArticleItemEntity> mArticleList;
    private List<ImageView> mImageViewList;

    private int mImagePosition;

    public ScrollPagerAdapter(Context context, ArrayList<ArticleItemEntity> articleList) {
        mContext = context;
        mArticleList = articleList;
        mImageViewList = new ArrayList<>();
        initImageViewList();
    }

    private void initImageViewList() {
        if (mArticleList == null) {
            return;
        }
        for (ArticleItemEntity entity : mArticleList) {
//            entity.setArticleImage(BuildConfig.DOMAI + entity.getArticleImage());
//            ImageView iv = new ImageView(mContext);
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(mContext).load(entity.getArticleImage()).into(iv);
//            mImageViewList.add(iv);
        }
    }

    @Override
    public int getCount() {
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= mImageViewList.size();
        if (position < 0) {
            position += mImageViewList.size();
        }
        mImagePosition = position;
        ImageView iv = mImageViewList.get(position);
        ViewParent vp = iv.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(iv);
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
//                bundle.putString("id", mArticleList.get(mImagePosition).getArticleId());
//                bundle.putString("businessNo",mArticleList.get(mImagePosition).getBusinessNO());
                ISkipActivityUtil.startIntent(mContext, ArticleActivity.class, bundle);
            }
        });
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public void onClick(View view) {
        ISkipActivityUtil.startIntent(mContext, ArticleActivity.class);
    }
}
