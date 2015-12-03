package com.boocu.kingdee.eas.mobileorder.vo;

/**
 * Created by jordan on 2015/12/3.
 */
public class MaterialTypeVO extends BaseVO {

    private long id;
    private String name;
    private String code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
