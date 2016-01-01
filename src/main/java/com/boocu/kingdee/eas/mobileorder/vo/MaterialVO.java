package com.boocu.kingdee.eas.mobileorder.vo;

import java.math.BigDecimal;

/**
 * Created by jordan on 2015/12/3.
 */
public class MaterialVO extends BaseVO {

    /**
     * 规则型号
     */
    private String model;

    /**
     * 物料价格
     */
    private BigDecimal price;

    /**
     * 基本计量单位
     */
    private String unitId;

    /**
     * 物料组
     */
    private MaterialGroupVO materialGroupVO;

    public MaterialVO() {
        super();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public MaterialGroupVO getMaterialGroupVO() {
        return materialGroupVO;
    }

    public void setMaterialGroupVO(MaterialGroupVO materialGroupVO) {
        this.materialGroupVO = materialGroupVO;
    }
}
