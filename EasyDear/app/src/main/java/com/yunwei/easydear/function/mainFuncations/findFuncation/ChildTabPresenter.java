package com.yunwei.easydear.function.mainFuncations.findFuncation;


import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easydear.function.mainFuncations.findFuncation.data.source.ChildTabDataSource;
import com.yunwei.easydear.function.mainFuncations.findFuncation.data.source.ChildTabRemoteRepo;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/21.
 */
public class ChildTabPresenter implements ChildTabContact.ChildPresenter, ChildTabDataSource.ChildTabCallBack {

    private ChildTabContact.ChileTabView mChildTabView;
    private ChildTabDataSource mRemoteRepo;

    public ChildTabPresenter(ChildTabRemoteRepo childTabRemoteRepo, ChildTabContact.ChileTabView chileTabView) {
        mRemoteRepo = childTabRemoteRepo;
        mChildTabView = chileTabView;
    }

    @Override
    public void requestRecyclerArticles() {
        mRemoteRepo.requestRecyclerArticles(this);
    }

    @Override
    public void onGetArticleListSuccess(ArrayList<ArticleItemEntity> data) {
        mChildTabView.onArticleListSuccess(data);
        mChildTabView.onArticleListEnd();
    }

    @Override
    public void onGetArticleListFailure(int code, String message) {
        mChildTabView.onArticleListFailure(code,message);
        mChildTabView.onArticleListEnd();
    }

    @Override
    public int getPageSize() {
        return mChildTabView.getPageSize();
    }

    @Override
    public int getPageCount() {
        return mChildTabView.getPageCount();
    }

    @Override
    public String getKey() {
        return mChildTabView.getKey();
    }

    @Override
    public String getType() {
        return mChildTabView.getType();
    }

    @Override
    public String getProvince() {
        return mChildTabView.getProvince();
    }

    @Override
    public String getCity() {
        return mChildTabView.getCity();
    }

    @Override
    public String getArea() {
        return mChildTabView.getArea();
    }
}
