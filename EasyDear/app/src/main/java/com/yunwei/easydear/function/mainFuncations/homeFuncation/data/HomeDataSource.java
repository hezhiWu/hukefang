package com.yunwei.easydear.function.mainFuncations.homeFuncation.data;

import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/4.
 */

public interface HomeDataSource {

    interface HomeCallBack {

        void onGetTopScrollArticlesSuccess(ArrayList<ArticleItemEntity> articleItems);

        void onGetTopScrollArticlesSuccess(String message);

        String getProvince();

        String getCity();

        String getArea();
    }

    void requestHomeTopScrollArticles(HomeCallBack callBack);
}
