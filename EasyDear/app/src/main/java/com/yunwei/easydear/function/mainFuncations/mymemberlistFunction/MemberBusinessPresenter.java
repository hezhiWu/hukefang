package com.yunwei.easydear.function.mainFuncations.mymemberlistFunction;

import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.source.BusinessDataSource;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.source.MemberBusinessRemoteRepo;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction
 * @Description:
 * @date 2017/1/21 14:53
 */

public class MemberBusinessPresenter implements BusinessContract.Present,BusinessDataSource.BusinessCallBack{

    private MemberBusinessRemoteRepo remoteRepo;
    private BusinessContract.BusinessView businessView;

    public MemberBusinessPresenter(BusinessContract.BusinessView businessView){
        this.remoteRepo= MemberBusinessRemoteRepo.newInstance();
        this.businessView=businessView;
    }
    @Override
    public void reqBusinessListAction() {
        businessView.onBusinessStart();
        remoteRepo.reqBusinessList(this);
    }

    @Override
    public void getBusinessSuccess(List<BusinessEntity> list) {
        businessView.onBusinessSuccess(list);
        businessView.onBusinessEnd();
    }

    @Override
    public void getBusinessFailure(String error) {
        businessView.onBusinessFaliure(error);
        businessView.onBusinessEnd();
    }

    @Override
    public String getUserNo() {
        return businessView.getUserNo();
    }

    @Override
    public int getPageSize() {
        return businessView.getPageSize();
    }

    @Override
    public int getPageCount() {
        return businessView.getPageCount();
    }
}
