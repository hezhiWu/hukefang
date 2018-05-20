package com.yunwei.easydear.function.mainFuncations.businessFunction;

import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/2/11.
 */
public class BusinessPresenter implements BusinessContact.Presenter, BusinessDataSource.BusDetailCallBack {

    private BusinessContact.BusinessView mBusinessView;
    private BusinessRemoteRepo mRemoteRepo;

    public BusinessPresenter(BusinessRemoteRepo remoteRepo, BusinessContact.BusinessView businessView) {
        mBusinessView = businessView;
        mRemoteRepo = remoteRepo;
    }

    @Override
    public void requestBusinessDetail() {
        mRemoteRepo.reqBusDetail(this);
    }

    @Override
    public void requestBusinessArticles() {
        mRemoteRepo.reqBusArticles(this);
    }

    public void requestBusinessCardList() {
        mRemoteRepo.reqBusinessCardList(this);
    }

    @Override
    public String getBusinessNo() {
        return mBusinessView.getBusinessNo();
    }

    @Override
    public String getUserNo() {
        return mBusinessView.getUserNo();
    }

    @Override
    public void onReqBusDetailSuccess(BusinessDetailEntity entity) {
        mBusinessView.setBusinessDetails(entity);
    }

    @Override
    public void onReqBusDetailFailure(String message) {

    }

    @Override
    public void onReqCardListSuccess(ArrayList<CardItemEntity> entityItems) {
        mBusinessView.setBusinessCardList(entityItems);
    }

    @Override
    public void onReqCardListFailure(String message) {

    }

    @Override
    public void onBusinessArticlesSuccess(ArrayList<ArticleItemEntity> articleItems) {
        mBusinessView.setBusinessArticles(articleItems);
    }

    @Override
    public void onReqBusinessArticlesFailure(String message) {

    }

}
