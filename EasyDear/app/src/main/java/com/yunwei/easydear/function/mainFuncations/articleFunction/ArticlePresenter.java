package com.yunwei.easydear.function.mainFuncations.articleFunction;

import com.yunwei.easydear.function.mainFuncations.businessFunction.CardItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/21.
 */

public class ArticlePresenter implements ArticleContact.Presenter, ArticleDataSource.ArticleDetailCallBack, ArticleDataSource.LatestCardCallBack, ArticleDataSource.BusinessArticleListCallBack {

    private ArticleContact.ArticleView mArticleView;
    private ArticleDataSource mRemoteRepo;

    public ArticlePresenter(ArticleRemoteRepo remoteRepo, ArticleContact.ArticleView articleView) {
        mRemoteRepo = remoteRepo;
        mArticleView = articleView;
    }

    @Override
    public void requestArticleDetail(String articleId) {
        mRemoteRepo.requestArticleDetail(articleId, this);
    }

    @Override
    public void requestLatestCardInfo(String businessNo) {
        mRemoteRepo.requestLatestCardInfo(businessNo, this);
    }

    @Override
    public void requestBusinessArticles(String businessNo) {
        mRemoteRepo.requestBusinessArticles(businessNo, this);
    }

    @Override
    public void onReqArticleSuccess(ArticleItemEntity entity) {
        mArticleView.setArticleDetail(entity);
    }

    @Override
    public void onReqArticleFailure(String message) {

    }

    @Override
    public void onReqLatestCardSuccess(ArrayList<CardItemEntity> entities) {
        mArticleView.setLatestCardInfo(entities);
    }

    @Override
    public void onReqLatestCardFailure(String message) {

    }

    @Override
    public void onBusinessArticlesSuccess(ArrayList<ArticleItemEntity> data) {
        mArticleView.setBusinessArticles(data);
    }

    @Override
    public void onReqBusinessArticlesFailure(String message) {

    }
}
