package com.boocu.kingdee.eas.mobileorder.vo;

import java.util.List;

/**
 * Created by jordan on 2015/12/2.
 */
public class JsonListResult<T> extends JsonResult {

    private int size;

    private List<T> data;

    public JsonListResult() {
        setSuccess(true);
    }

    public JsonListResult(List<T> list) {
        setSuccess(true);
        setData(list);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        if(data!=null){
            setSize(data.size());
        }
    }
}
