package com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment;

import com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.CardEntity;

/**
 * Created by LJH on 2017/2/12.
 */

public interface MineContact {

    interface MineView {
        String getUserNo();

        int getPageSize();

        int getPageCount();

        void setCardAmount(CardEntity cardEntity);

        void setBusinessAmount(com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.BusinessEntity businessEntity);
    }

    interface Presenter {
        void requestMyCardAmount();

        void requestMyBusinessAmount();
    }
}
