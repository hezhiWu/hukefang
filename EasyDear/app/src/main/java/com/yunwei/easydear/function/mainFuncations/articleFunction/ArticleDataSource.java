package com.yunwei.easydear.function.mainFuncations.articleFunction;

import com.yunwei.easydear.function.mainFuncations.businessFunction.CardItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/21.
 */

public interface ArticleDataSource {

    void requestArticleDetail(String articleId, ArticleDetailCallBack callBack);

    void requestLatestCardInfo(String businessNo, LatestCardCallBack callBack);

    void requestBusinessArticles(String businessNo, BusinessArticleListCallBack callBack);

    interface ArticleDetailCallBack {

        void onReqArticleSuccess(ArticleItemEntity data);

        void onReqArticleFailure(String message);

    }

    interface LatestCardCallBack {

        void onReqLatestCardSuccess(ArrayList<CardItemEntity> data);

        void onReqLatestCardFailure(String message);

    }

    interface BusinessArticleListCallBack {
        void onBusinessArticlesSuccess(ArrayList<ArticleItemEntity> data);

        void onReqBusinessArticlesFailure(String message);
    }
}
