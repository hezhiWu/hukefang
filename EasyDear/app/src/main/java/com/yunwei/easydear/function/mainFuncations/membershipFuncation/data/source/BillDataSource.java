package com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.source;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.membershipFuncation.data
 * @Description:
 * @date 2017/1/21 15:37
 */

public interface BillDataSource {

    interface CardCallBack {
        void onCardSuccess(int size);

        void onCardFalire(String error);

        String getUserNo();
    }

    interface BusinessCallBack {
        void onBusinessSuccess(int size);

        void onBusinessFalire(String error);

        String getUserNo();
    }

    interface BillssCallBack {
        void onBillSuccess(int size);

        void onBillFalire(String error);

        String getUserNo();
    }

    void reqCardSize(CardCallBack cardCallBack);

    void reqBunisess(BusinessCallBack callBack);

    void reqBill(BillssCallBack callBack);
}
