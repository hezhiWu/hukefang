package com.yunwei.easydear.function.mainFuncations.myorderlistFunction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseRecyclerViewAdapter;
import com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data.OrderEntity;
import com.yunwei.easydear.utils.IStringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MyOrderAdapter extends BaseRecyclerViewAdapter<OrderEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    public MyOrderAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        MyOrderAdapter.ItemViewHolder viewHolder = new MyOrderAdapter.ItemViewHolder(inflater.inflate(R.layout.item_myorder_layout, parent, false));
        setOnItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.businessNameText.setText(mLists.get(position).getBusinessName());/*商家名*/
        viewHolder.cardTypeText.setText(mLists.get(position).getCardName());/*消费券信息*/
        viewHolder.validateText.setText("有效期至" + mLists.get(position).getCardEndTime());/*有效期*/
        viewHolder.priceText.setText("¥" + mLists.get(position).getBuyAmount());/*金额*/
        viewHolder.amountText.setText(mLists.get(position).getCardSize());
        viewHolder.sumPriceText.setText("合计: ¥" + IStringUtils.toInt(mLists.get(position).getCardSize()) * IStringUtils.toInt(mLists.get(position).getBuyAmount()));
        viewHolder.orderState.setText(mLists.get(position).getStatus());
        Glide.with(mContent).load(BuildConfig.DOMAI + mLists.get(position).getLogo()).into(viewHolder.headView);
    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.myorder_business_head_imageView)
        ImageView headView;
        @BindView(R.id.myorder_business_name)
        TextView businessNameText;
        @BindView(R.id.myorder_card_type_textView)
        TextView cardTypeText;
        @BindView(R.id.myorder_card_validate_textView)
        TextView validateText;
        @BindView(R.id.myorder_card_price)
        TextView priceText;
        @BindView(R.id.myorder_card_amount)
        TextView amountText;
        @BindView(R.id.myorder_card_sum_price)
        TextView sumPriceText;
        @BindView(R.id.myorder_state)
        TextView orderState;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }

        @OnClick({R.id.myorder_apply_refund})
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.myorder_apply_refund:
                    Log.d(TAG, "----------> onClick  Refund, position = " + position);
                    break;
            }
        }
    }
}
