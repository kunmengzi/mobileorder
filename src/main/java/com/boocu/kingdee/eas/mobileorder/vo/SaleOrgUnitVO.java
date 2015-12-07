package com.boocu.kingdee.eas.mobileorder.vo;

/**
 * 销售组织
 *
 * Created by jordan on 15/12/6.
 */
public class SaleOrgUnitVO extends BaseVO{

    public SaleOrgUnitVO() {

    }

    public SaleOrgUnitVO(String id,String name) {
        this();
        setId(id);
        setName(name);
    }
}
