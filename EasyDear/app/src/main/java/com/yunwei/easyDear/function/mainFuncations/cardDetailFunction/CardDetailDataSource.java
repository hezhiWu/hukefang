package com.yunwei.easydear.function.mainFuncations.cardDetailFunction;

import com.yunwei.easydear.function.mainFuncations.businessFunction.CardItemEntity;

/**
 * Created by LJH on 2017/1/21.
 */

public interface CardDetailDataSource {

    void requestCardDetail(String cardNo, CardDetailCallBack callBack);

    interface CardDetailCallBack {

        void onReqCardDetailSuccess(CardItemEntity data);

        void onReqCardDetailFailure(String message);
    }
}
