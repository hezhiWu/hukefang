package com.yunwei.easydear.function.mainFuncations.businessFunction;

import java.io.Serializable;

/**
 * Created by LJH on 2017/1/21.
 */

public class CardItemEntity implements Serializable{

    private String CardNo;
    private String CardName;
    private String AssociateName;
    private String CardPrice;
    private String CardOldPrice;
    private String CardStartTime;
    private String CardEndTime;
    private String BusinessNO;
    private String Logo;
    private String BusinessName;

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public String getAssociateName() {
        return AssociateName;
    }

    public void setAssociateName(String associateName) {
        AssociateName = associateName;
    }

    public String getCardPrice() {
        return CardPrice;
    }

    public void setCardPrice(String cardPrice) {
        CardPrice = cardPrice;
    }

    public String getCardOldPrice() {
        return CardOldPrice;
    }

    public void setCardOldPrice(String cardOldPrice) {
        CardOldPrice = cardOldPrice;
    }

    public String getCardStartTime() {
        return CardStartTime;
    }

    public void setCardStartTime(String cardStartTime) {
        CardStartTime = cardStartTime;
    }

    public String getCardEndTime() {
        return CardEndTime;
    }

    public void setCardEndTime(String cardEndTime) {
        CardEndTime = cardEndTime;
    }

    public String getBusinessNO() {
        return BusinessNO;
    }

    public void setBusinessNO(String businessNO) {
        BusinessNO = businessNO;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }
}
