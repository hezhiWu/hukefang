package com.yunwei.easydear.function.mainFuncations.mineFuncation;

import com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.CardEntity;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.data.MineDataSource;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.data.MineRemoteRepo;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment.MineContact;

/**
 * Created by LJH on 2017/2/12.
 */
public class MinePresenter implements MineContact.Presenter, MineDataSource.MineCallBack {

    private MineContact.MineView mMineView;
    private MineRemoteRepo mRemoteRepo;

    public MinePresenter(MineContact.MineView mineView) {
        mMineView = mineView;
        mRemoteRepo = MineRemoteRepo.getInstance();
    }

    @Override
    public void requestMyCardAmount() {
        mRemoteRepo.reqMyCardAmount(this);
    }

    @Override
    public void requestMyBusinessAmount() {
        mRemoteRepo.reqMyBusinessAmount(this);
    }

    @Override
    public void getCardCountSuccess(CardEntity cardEntity) {
        mMineView.setCardAmount(cardEntity);
    }

    @Override
    public void getCardCountFailure(String message) {

    }

    @Override
    public void getBusinessCountSuccess(com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.BusinessEntity businessEntity) {
        mMineView.setBusinessAmount(businessEntity);
    }

    @Override
    public void getBusinessCountFailure(String message) {

    }

    @Override
    public String getUserNo() {
        return mMineView.getUserNo();
    }

    @Override
    public int getPageSize() {
        return mMineView.getPageSize();
    }

    @Override
    public int getPageCount() {
        return mMineView.getPageCount();
    }
}
