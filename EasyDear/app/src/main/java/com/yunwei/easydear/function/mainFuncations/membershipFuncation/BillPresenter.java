package com.yunwei.easydear.function.mainFuncations.membershipFuncation;

import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.source.BillDataSource;
import com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.source.BillRemoteRepo;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.membershipFuncation
 * @Description:
 * @date 2017/1/21 15:44
 */

public class BillPresenter implements BillContract.Presenter, BillDataSource.CardCallBack, BillDataSource.BusinessCallBack, BillDataSource.BillssCallBack {

    private BillRemoteRepo remoteRepo;
    private BillContract.CardView cardView;
    private BillContract.BusinessView businessView;
    private BillContract.BillssView billssView;

    public BillPresenter(BillContract.CardView cardView) {
        this.remoteRepo = BillRemoteRepo.newInstance();
        this.cardView = cardView;
    }

    public BillPresenter(BillContract.BusinessView businessView) {
        this.remoteRepo = BillRemoteRepo.newInstance();
        this.businessView = businessView;
    }

    public BillPresenter(BillContract.BillssView billssView) {
        this.remoteRepo = BillRemoteRepo.newInstance();
        this.billssView = billssView;
    }

    @Override
    public void reqCardAction() {
        remoteRepo.reqCardSize(this);
    }

    @Override
    public void reqBusinessAction() {
        remoteRepo.reqBunisess(this);
    }

    @Override
    public void reqBillAction() {
        remoteRepo.reqBill(this);
    }

    @Override
    public void onBillSuccess(int size) {
        billssView.onBillSuccess(size);
    }

    @Override
    public void onBillFalire(String error) {
        billssView.onBillFalire(error);
    }

    @Override
    public void onBusinessSuccess(int size) {
        businessView.onBusinessSuccess(size);
    }

    @Override
    public void onBusinessFalire(String error) {
        businessView.onBusinessFalire(error);
    }

    @Override
    public void onCardSuccess(int size) {
        cardView.onCardSuccess(size);
    }

    @Override
    public void onCardFalire(String error) {
        cardView.onCardFalire(error);
    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getUserNo();
    }
}
