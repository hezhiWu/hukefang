package com.yunwei.easydear.function.cards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.function.cards.data.CardEntity;
import com.yunwei.easydear.function.cards.data.source.CardsDataSource;
import com.yunwei.easydear.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/22.
 */

public class CardsListFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener,CardsDataSource.QueryCardListCallBack {

    @BindView(R.id.Card_List_RecyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.card_list_activity, null);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    private void initRecyclerView(){

    }

    @Override
    public void onDownRefresh() {

    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void queryCardListSuccess(List<CardEntity> list) {

    }

    @Override
    public void queryCardListFailure(int code, String msg) {

    }
}
