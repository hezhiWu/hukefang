package com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity
 * @Description:
 * @date 2017/1/21 14:41
 */

public class BusinessEntity {

    private String BusinessNo;
    private String Logo;
    private String BusinessName;
    private String VipLevel;
    private int CardSize;
    private int Integral;

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

    public String getVipLevel() {
        return VipLevel;
    }

    public void setVipLevel(String vipLevel) {
        VipLevel = vipLevel;
    }

    public int getCardSize() {
        return CardSize;
    }

    public void setCardSize(int cardSize) {
        CardSize = cardSize;
    }

    public int getIntegral() {
        return Integral;
    }

    public void setIntegral(int integral) {
        Integral = integral;
    }
}
