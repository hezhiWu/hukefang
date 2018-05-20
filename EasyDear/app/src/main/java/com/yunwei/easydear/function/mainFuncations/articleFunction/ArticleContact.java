package com.yunwei.easydear.function.mainFuncations.articleFunction;

import com.yunwei.easydear.function.mainFuncations.businessFunction.CardItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/21.
 */

public interface ArticleContact {

    interface ArticleView {
        void setArticleDetail(ArticleItemEntity entity);

        void setLatestCardInfo(ArrayList<CardItemEntity> cardItems);

        void setBusinessArticles(ArrayList<ArticleItemEntity> data);
    }

    interface Presenter {
        void requestArticleDetail(String articleId);

        void requestLatestCardInfo(String businessNo);

        void requestBusinessArticles(String businessNo);
    }
}
