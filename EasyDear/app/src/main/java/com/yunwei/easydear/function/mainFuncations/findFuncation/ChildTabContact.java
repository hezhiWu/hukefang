package com.yunwei.easydear.function.mainFuncations.findFuncation;

import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/21.
 */

public interface ChildTabContact {

    interface ChildPresenter {
        void requestRecyclerArticles();
    }

    interface ChileTabView {
        void onArticleListStart();

        void onArticleListEnd();

        void onArticleListSuccess(ArrayList<ArticleItemEntity> data);

        void onArticleListFailure(int code, String error);

        int getPageSize();

        int getPageCount();

        String getKey();

        String getType();

        String getProvince();

        String getCity();

        String getArea();

    }
}
