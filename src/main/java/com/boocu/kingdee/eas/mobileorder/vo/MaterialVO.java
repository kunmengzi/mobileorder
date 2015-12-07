package com.boocu.kingdee.eas.mobileorder.vo;

/**
 * Created by jordan on 2015/12/3.
 */
public class MaterialVO extends BaseVO {

    /**
     * 规则型号
     */
    private String model;

    public MaterialVO() {
    }

    public MaterialVO(String id,String name) {
        this();
        setId(id);
        setName(name);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
