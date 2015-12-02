package com.boocu.kingdee.eas.mobileorder.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Created by jordan on 2015/12/1.
 */
public class BaseVO implements Serializable{

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
