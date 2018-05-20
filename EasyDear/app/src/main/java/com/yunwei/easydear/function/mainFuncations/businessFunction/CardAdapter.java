package com.yunwei.easydear.function.mainFuncations.businessFunction;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseRecyclerViewAdapter;
import com.yunwei.easydear.function.mainFuncations.cardDetailFunction.CardDetailActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LJH on 2017/2/12.
 */

public class CardAdapter extends BaseRecyclerViewAdapter<CardItemEntity> {

    private Context mContext;

    public CardAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        CardAdapter.ItemViewHolder viewHolder = new CardAdapter.ItemViewHolder(inflater.inflate(R.layout.item_card_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        final CardItemEntity entity = mLists.get(position);
        viewHolder.cardNameText.setText(entity.getCardName());
        viewHolder.cardAssociateNameText.setText(entity.getAssociateName());
        viewHolder.cardPriceText.setText("¥" + entity.getCardPrice());
        viewHolder.cardOldPriceText.setText(entity.getCardOldPrice() == null ? "" : mContext.getString(R.string.article_old_price) + entity.getCardOldPrice());
        Glide.with(mContent).load(BuildConfig.DOMAI + entity.getLogo()).into(viewHolder.logoView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCardDetailActivity(entity.getCardNo());
            }
        };
        viewHolder.cardDetailText.setOnClickListener(listener);
        viewHolder.cardPurchaseBtn.setOnClickListener(listener);
    }

    private void startCardDetailActivity(String cardNo) {
        Bundle bundle = new Bundle();
        bundle.putString("cardNo", cardNo);
        ISkipActivityUtil.startIntent(mContext, CardDetailActivity.class, bundle);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.business_card_head)
        ImageView logoView;
        @BindView(R.id.business_card_detail)
        TextView cardDetailText;
        @BindView(R.id.business_card_name)
        TextView cardNameText;
        @BindView(R.id.business_card_associate_name)
        TextView cardAssociateNameText;
        @BindView(R.id.business_card_price)
        TextView cardPriceText;
        @BindView(R.id.business_card_old_price)
        TextView cardOldPriceText;
        @BindView(R.id.business_card_purchase)
        Button cardPurchaseBtn;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }
    }
}
