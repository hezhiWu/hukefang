package com.yunwei.easydear.function.dynamic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseRecyclerViewAdapter;
import com.yunwei.easydear.function.dynamic.data.DynamicEntity;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe:tab child adapter
 * Author: hezhiWu
 * Date: 2016-12-25
 * Time: 15:02
 * Version:1.0
 */

public class DynamicListAdapter extends BaseRecyclerViewAdapter<DynamicEntity> {

    public DynamicListAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;

        DynamicEntity entity = mLists.get(position);
        viewHolder.DynamicItemBusinessName.setText(entity.getBusinessName());
        viewHolder.DynamicItemPubTime.setText(entity.getPubTime());
        viewHolder.DynamicItemSummary.setText(entity.getSummary());

        Glide.with(mContent).load(BuildConfig.IMG_DOMAI + entity.getLogo()).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(viewHolder.DynamicItemLogo));
        Glide.with(mContent).load(BuildConfig.IMG_DOMAI + entity.getArticleImage()).into(viewHolder.DynamicItemArticleImage);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder = new ItemViewHolder(inflater.inflate(R.layout.item_dynamic_layout, parent, false));
        return viewHolder;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.Dynamic_Item_Logo)
        ImageView DynamicItemLogo;
        @BindView(R.id.Dynamic_Item_BusinessName)
        TextView DynamicItemBusinessName;
        @BindView(R.id.Dynamic_Item_ArticleImage)
        ImageView DynamicItemArticleImage;
        @BindView(R.id.Dynamic_Item_PubTime)
        TextView DynamicItemPubTime;
        @BindView(R.id.Dynamic_Item_Summary)
        TextView DynamicItemSummary;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }
    }
}
