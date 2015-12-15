package com.boocu.kingdee.eas.mobileorder.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Created by jordan on 2015/12/1.
 */
public class BaseVO extends CoreVO{

    private String name;
    private String number;

    public BaseVO() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
