package com.boocu.kingdee.eas.mobileorder.vo;

/**
 * 经销商
 *
 * Created by jordan on 15/12/6.
 */
public class CustomerVO extends BaseVO {

    public CustomerVO() {
    }

    public CustomerVO(String id, String name) {
        this();
        setId(id);
        setName(name);
    }
}
