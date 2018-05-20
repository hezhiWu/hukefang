package com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.myorderlistFunction.data
 * @Description:
 * @date 2017/1/21 10:53
 */

public class OrderEntity {

    private String BillNo;
    private String BusinessNo;
    private String Logo;
    private String BusinessName;
    private String CardName;
    private String CardStartTime;
    private String CardEndTime;
    private String BuyAmount;
    private String Status;
    private String CardSize;

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public String getBusinessNo() {
        return BusinessNo;
    }

    public void setBusinessNo(String businessNo) {
        BusinessNo = businessNo;
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

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
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

    public String getBuyAmount() {
        return BuyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        BuyAmount = buyAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCardSize() {
        return CardSize;
    }

    public void setCardSize(String cardSize) {
        CardSize = cardSize;
    }
}
