package com.yunwei.easydear.function.mainFuncations.membershipFuncation;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.membershipFuncation
 * @Description:
 * @date 2017/1/21 15:41
 */

public interface BillContract {

    interface CardView {
        void onCardSuccess(int size);

        void onCardFalire(String error);

        String getUserNo();
    }

    interface BusinessView {
        void onBusinessSuccess(int size);

        void onBusinessFalire(String error);

        String getUserNo();
    }

    interface BillssView {
        void onBillSuccess(int size);

        void onBillFalire(String error);

        String getUserNo();
    }

    interface Presenter {
        void reqCardAction();

        void reqBusinessAction();

        void reqBillAction();
    }
}
