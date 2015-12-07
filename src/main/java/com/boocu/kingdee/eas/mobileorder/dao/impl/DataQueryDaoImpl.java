package com.boocu.kingdee.eas.mobileorder.dao.impl;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialGroupVO;
import com.boocu.kingdee.eas.mobileorder.vo.NetOrderVO;

import java.util.List;

/**
 * Created by jordan on 15/12/6.
 */
public class DataQueryDaoImpl implements IDataQueryDao{

    public List<MaterialGroupVO> queryMaterialGroupByStandard(String standardId) {
        String sql = "select fid,fname_l2,fnumber from t_bd_materialgroup where fgroupstandard = ? and flevel = 1 and fisleaf = 1";

        return null;
    }

    public List<NetOrderVO> queryNetOrder() {
        //订购总数量、订购总金额、要求到货日期、，要查询分录
        String sql = "select bill.fid,bill.fnumber,bill.fbizDate,bill.fqty,bill.famount,bill.fAskArriveDate,bill.fbillstatus,bill.fisfightback,"
                + " customer.fid,customer.fname_l2,sale.fid,sale.fname_l2"
                + " from T_CHA_NETORDERBILL bill " +
                 " inner join T_BD_CUSTOMER customer on bill.fcustomerId = customer.fid " +
                 " inner join T_ORG_SALE sale on bill.fsaleorgunitid = sale.fid "
                +" where bill.fCustomerId = ? ";

        return null;
    }
}
