package com.boocu.kingdee.eas.mobileorder.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jordan on 15/12/6.
 */
public class NetOrderVO extends CoreVO {

    /** 订单编号 **/
    private String number;

    /** 业务日期 **/
    private Date bizDate;

    /** 销售组织  **/
    private SaleOrgUnitVO saleOrgUnitVO;

    /** 经销商 **/
    private CustomerVO customerVO;

    /** 渠道 **/
    private ChannelVO channelVO;

    /** 总数量 **/
    private BigDecimal qty;

    /** 总金额 **/
    private BigDecimal amount;

    /** 要求到货日期 **/
    private Date askArriveDate;

    /** 单据状态 */
    private String billStatusDesc;

    /** 单据状态 **/
    private int billStatus;

    /** 是否打回 **/
    private boolean isFightBack;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public ChannelVO getChannelVO() {
        return channelVO;
    }

    public void setChannelVO(ChannelVO channelVO) {
        this.channelVO = channelVO;
    }

    public SaleOrgUnitVO getSaleOrgUnitVO() {
        return saleOrgUnitVO;
    }

    public void setSaleOrgUnitVO(SaleOrgUnitVO saleOrgUnitVO) {
        this.saleOrgUnitVO = saleOrgUnitVO;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getAskArriveDate() {
        return askArriveDate;
    }

    public void setAskArriveDate(Date askArriveDate) {
        this.askArriveDate = askArriveDate;
    }

    public String getBillStatusDesc() {
        return billStatusDesc;
    }

    public void setBillStatusDesc(String billStatusDesc) {
        this.billStatusDesc = billStatusDesc;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }

    public boolean isFightBack() {
        return isFightBack;
    }

    public void setIsFightBack(boolean isFightBack) {
        this.isFightBack = isFightBack;
    }

    public CustomerVO getCustomerVO() {
        return customerVO;
    }

    public void setCustomerVO(CustomerVO customerVO) {
        this.customerVO = customerVO;
    }
}
