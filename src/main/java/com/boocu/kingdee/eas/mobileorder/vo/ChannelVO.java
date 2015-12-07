package com.boocu.kingdee.eas.mobileorder.vo;

/**
 * 经销商
 *
 * Created by jordan on 15/12/6.
 */
public class ChannelVO extends BaseVO {

    public ChannelVO() {
    }

    public ChannelVO(String id,String name) {
        this();
        setId(id);
        setName(name);
    }
}
