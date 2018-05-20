package com.yunwei.easydear.function.mainFuncations.mymemberlistFunction;

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
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MyMemberAdapter extends BaseRecyclerViewAdapter<BusinessEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    public MyMemberAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        MyMemberAdapter.ItemViewHolder viewHolder = new MyMemberAdapter.ItemViewHolder(inflater.inflate(R.layout.item_mymember_layout, parent, false));
        setOnItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.businessNameText.setText(mLists.get(position).getBusinessName());
            viewHolder.levelText.setText(mLists.get(position).getVipLevel());
            viewHolder.cardAmountText.setText("您有"+mLists.get(position).getCardSize()+"张礼券");
//            viewHolder.creditText.setText(mLists.get(position).getCredit());
            Glide.with(mContent).load(BuildConfig.DOMAI+mLists.get(position).getLogo()).into(viewHolder.logoView);
    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mymember_business_logo)
        ImageView logoView;
        @BindView(R.id.mymember_business_name)
        TextView businessNameText;
        @BindView(R.id.mymember_level)
        TextView levelText;
        @BindView(R.id.mymember_card_amount)
        TextView cardAmountText;
        @BindView(R.id.mymember_credit)
        TextView creditText;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }

        @OnClick({R.id.mymember_business_name})
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.mymember_business_name:
                    Log.d(TAG, "----------> onClick  position = " + position);
                    break;
            }
        }
    }
}
