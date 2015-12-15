package com.boocu.kingdee.eas.mobileorder.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jordan on 2015/12/1.
 */
public class ShoppingCarVO extends CoreVO {

    /***
     * 商品编码
     * 商品名称
     * 规格型号
     **/
    private MaterialVO materialVO;

    /***
     * 计量单位
     */
    private MeasureUnitVO measureUnitVO;

     /* 订货数量 */
    private BigDecimal qty;

    /** 单价 **/
    private BigDecimal price;

    /** 金额 **/
    private BigDecimal amount;

    /** 购物日期 **/
    private Date shopDate;

    /** 销售组织 **/
    private String saleOrgUnitId;

    /** 渠道 **/
    private String channel;

    public MaterialVO getMaterialVO() {
        return materialVO;
    }

    public void setMaterialVO(MaterialVO materialVO) {
        this.materialVO = materialVO;
    }

    public MeasureUnitVO getMeasureUnitVO() {
        return measureUnitVO;
    }

    public void setMeasureUnitVO(MeasureUnitVO measureUnitVO) {
        this.measureUnitVO = measureUnitVO;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getShopDate() {
        return shopDate;
    }

    public void setShopDate(Date shopDate) {
        this.shopDate = shopDate;
    }

    public String getSaleOrgUnitId() {
        return saleOrgUnitId;
    }

    public void setSaleOrgUnitId(String saleOrgUnitId) {
        this.saleOrgUnitId = saleOrgUnitId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
