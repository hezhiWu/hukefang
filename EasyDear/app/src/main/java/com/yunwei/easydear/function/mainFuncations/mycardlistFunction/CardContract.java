package com.yunwei.easydear.function.mainFuncations.mycardlistFunction;

import com.yunwei.easydear.function.mainFuncations.mycardlistFunction.data.CardEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mycardlistFunction
 * @Description:
 * @date 2017/1/21 16:55
 */

public interface CardContract {

    interface CardView {
        void onCardStart();

        void onCardEnd();

        void onCardSuccess(List<CardEntity> list);

        void onCardFailure(String error);

        String getUserNo();

        int getPageSize();

        int getPageCount();
    }

    interface Presenter {
        void reqCardListAction();
    }
}
