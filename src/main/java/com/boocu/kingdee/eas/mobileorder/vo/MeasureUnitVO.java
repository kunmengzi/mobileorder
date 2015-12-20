package com.boocu.kingdee.eas.mobileorder.vo;

import java.math.BigDecimal;

/**
 * Created by jordan on 2015/12/3.
 */
public class MeasureUnitVO extends BaseVO {

    private String materialId;

    private String unitId;

    private boolean basicUnit;

    private int qtyPrecision;

    private BigDecimal baseConvsRate;

    public MeasureUnitVO() {
        super();
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public boolean isBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(boolean basicUnit) {
        this.basicUnit = basicUnit;
    }

    public int getQtyPrecision() {
        return qtyPrecision;
    }

    public void setQtyPrecision(int qtyPrecision) {
        this.qtyPrecision = qtyPrecision;
    }

    public BigDecimal getBaseConvsRate() {
        return baseConvsRate;
    }

    public void setBaseConvsRate(BigDecimal baseConvsRate) {
        this.baseConvsRate = baseConvsRate;
    }
}
