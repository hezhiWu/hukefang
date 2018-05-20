package com.yunwei.easydear.function.mainFuncations.mycardlistFunction;

import com.yunwei.easydear.function.mainFuncations.mycardlistFunction.data.CardEntity;
import com.yunwei.easydear.function.mainFuncations.mycardlistFunction.data.source.CardDataSource;
import com.yunwei.easydear.function.mainFuncations.mycardlistFunction.data.source.CardRemoteRepo;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mycardlistFunction
 * @Description:
 * @date 2017/1/21 16:55
 */

public class CardPresenter implements CardContract.Presenter, CardDataSource.CardCallBack {

    private CardRemoteRepo remoteRepo;
    private CardContract.CardView cardView;

    public CardPresenter(CardContract.CardView cardView) {
        this.remoteRepo = CardRemoteRepo.newInstance();
        this.cardView = cardView;
    }

    @Override
    public void reqCardListAction() {
        cardView.onCardEnd();
        remoteRepo.reqCardList(this);
    }

    @Override
    public String getUserNo() {
        return cardView.getUserNo();
    }

    @Override
    public void getCardSuccess(List<CardEntity> list) {
        cardView.onCardSuccess(list);
        cardView.onCardEnd();
    }

    @Override
    public void getCardFaiulre(String error) {
        cardView.onCardFailure(error);
        cardView.onCardEnd();
    }
}
