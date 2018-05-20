package com.yunwei.easydear.common.retrofit;

/**
 * Created by Administrator on 2018/5/19.
 */

public class ResponseEntity {
    private String Message;
    private String Code;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
}
