package com.yunwei.easydear.function.mainFuncations.findFuncation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseRecyclerViewAdapter;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe:tab child adapter
 * Author: hezhiWu
 * Date: 2016-12-25
 * Time: 15:02
 * Version:1.0
 */

public class ChildTabContentAdapter extends BaseRecyclerViewAdapter<ArticleItemEntity>{

    public ChildTabContentAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;

        ArticleItemEntity entity = mLists.get(position);
        viewHolder.contentText.setText(entity.getBusinessName());
        viewHolder.businessName.setText(entity.getBusinessName());
//        viewHolder.pubTime.setText(entity.getPubTime());
        viewHolder.mName.setText(entity.getBusinessName());

        List<ArticleItemEntity.ActivityQueryListBean> beans=entity.getActivityQueryList();
        if (beans!=null &&beans.size()>0){
            viewHolder.mLogo.setText(beans.get(0).getTitle());
            viewHolder.mName.setText(beans.get(0).getActivityName());

            viewHolder.mActivity.setText(beans.size()+"个活动");
        }
       entity.getActivityQueryList();
        Glide.with(mContent).load(BuildConfig.IMG_DOMAI + entity.getLogo()).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(viewHolder.headView));
        Glide.with(mContent).load(BuildConfig.IMG_DOMAI + entity.getSloganImages()).into(viewHolder.articleImageView);

//        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle=new Bundle();
//                bundle.putString("id",mLists.get(position).getBusinessNo());
//                bundle.putString("businessNo",mLists.get(position).getBusinessNo());
//                ISkipActivityUtil.startIntent(mContent, ArticleActivity.class,bundle);
//            }
//        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder = new ItemViewHolder(inflater.inflate(R.layout.item_tab_child_layout, parent, false));
        return viewHolder;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_tab_child_head_imageView)
        ImageView headView;
        @BindView(R.id.item_tab_child_content_textView)
        TextView contentText;
        @BindView(R.id.item_tab_child_business_textView)
        TextView businessName;
        @BindView(R.id.item_tab_child_date_textView)
        TextView pubTime;
        @BindView(R.id.item_tab_child_business_imageView)
        ImageView articleImageView;
        @BindView(R.id.item_tab_child_layout)
        LinearLayout layout;
        @BindView(R.id.item_tab_child_business_Logo)
        TextView mLogo;
        @BindView(R.id.item_tab_child_business_Name)
        TextView mName;
        @BindView(R.id.item_tab_child_business_Activity)
        TextView mActivity;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }
    }

}
