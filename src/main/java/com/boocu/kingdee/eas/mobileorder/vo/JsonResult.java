package com.boocu.kingdee.eas.mobileorder.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by jordan on 2015/12/2.
 */
public class JsonResult extends BaseVO {

    private boolean success;

    private String errorCode;

    private String errorMsg;

    public JsonResult() {
        this( true);
    }

    public JsonResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toJsonString(){
        return JSONObject.toJSONString(this);
    }
}
