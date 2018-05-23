package com.yunwei.easydear.function.cards;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.yunwei.easydear.base.BaseRecyclerViewAdapter;
import com.yunwei.easydear.function.cards.data.CardEntity;

/**
 * Created by Administrator on 2018/5/22.
 */

public class CardsListAdapter extends BaseRecyclerViewAdapter<CardEntity> {


    public CardsListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
