package com.yunwei.easydear.function.mainFuncations.businessFunction;

import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/2/11.
 */

public interface BusinessDataSource {

    void reqBusinessCardList(BusDetailCallBack callBack);

    void reqBusArticles(BusDetailCallBack callBack);

    interface BusDetailCallBack {
        void onReqBusDetailSuccess(BusinessDetailEntity entity);

        void onReqBusDetailFailure(String message);

        String getBusinessNo();

        String getUserNo();

        void onReqCardListSuccess(ArrayList<CardItemEntity> data);

        void onReqCardListFailure(String message);

        void onBusinessArticlesSuccess(ArrayList<ArticleItemEntity> data);

        void onReqBusinessArticlesFailure(String message);
    }
}
