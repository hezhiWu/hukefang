package com.yunwei.easydear.function.mainFuncations.mycardlistFunction.data.source;

import com.yunwei.easydear.function.mainFuncations.mycardlistFunction.data.CardEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mycardlistFunction.data.source
 * @Description:
 * @date 2017/1/21 16:54
 */

public interface CardDataSource {

    interface CardCallBack {
        void getCardSuccess(List<CardEntity> list);

        void getCardFaiulre(String error);

        String getUserNo();
    }

    void reqCardList(CardCallBack callBack);
}
