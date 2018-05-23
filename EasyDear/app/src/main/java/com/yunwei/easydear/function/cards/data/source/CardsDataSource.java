package com.yunwei.easydear.function.cards.data.source;

import com.yunwei.easydear.function.cards.data.CardEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public interface CardsDataSource {

    interface QueryCardListCallBack{
        void queryCardListSuccess(List<CardEntity> list);

        void queryCardListFailure(int code,String msg);
    }

    void queryCardList(int pageSize,int pageCount,String keywords,QueryCardListCallBack callBack);
}
