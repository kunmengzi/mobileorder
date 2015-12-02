package com.boocu.kingdee.eas.mobileorder.vo;

/**
 * Created by jordan on 2015/12/2.
 */
public class JsonObjectResult<T> extends JsonResult {


    private T data;

    public JsonObjectResult() {
       this.setSuccess(true);
    }

    public JsonObjectResult(T data) {
        this.setSuccess(true);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
