package com.yunwei.easydear.function.mainFuncations.homeFuncation;

import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/2.
 */

public interface HomeContract {

    interface HomeView {
        void setTopScrollArticles(ArrayList<ArticleItemEntity> articleItems);

        String getProvince();

        String getCity();

        String getArea();
    }

    interface Presenter {
        void requestTopScrollArticles();
    }
}
