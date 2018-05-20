package com.yunwei.easydear.function.mainFuncations.businessFunction;

import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/2/11.
 */

public interface BusinessContact {

    interface BusinessView {
        String getBusinessNo();

        String getUserNo();

        void setBusinessDetails(BusinessDetailEntity entity);

        void setBusinessCardList(ArrayList<CardItemEntity> cardItems);

        void setBusinessArticles(ArrayList<ArticleItemEntity> articleItems);
    }

    interface Presenter {
        void requestBusinessDetail();

        void requestBusinessArticles();
    }
}
