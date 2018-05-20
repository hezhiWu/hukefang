package com.yunwei.easydear.function.account.data;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account.data
 * @Description:登录实体类
 * @date 2016/11/29 15:01
 */

public class UserInfoEntity {

    private String UserNo;

    private String Mobile;

    private String NickName;

    private String Imagery;

    public void setUserNo(String UserNo){
        this.UserNo = UserNo;
    }
    public String getUserNo(){
        return this.UserNo;
    }
    public void setMobile(String Mobile){
        this.Mobile = Mobile;
    }
    public String getMobile(){
        return this.Mobile;
    }
    public void setNickName(String NickName){
        this.NickName = NickName;
    }
    public String getNickName(){
        return this.NickName;
    }
    public void setImagery(String Imagery){
        this.Imagery = Imagery;
    }
    public String getImagery(){
        return this.Imagery;
    }
}
