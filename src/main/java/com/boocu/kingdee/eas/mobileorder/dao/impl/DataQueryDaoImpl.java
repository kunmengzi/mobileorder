package com.boocu.kingdee.eas.mobileorder.dao.impl;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.*;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.mobilecommon.dao.CommonDao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jordan on 15/12/6.
 */
public class DataQueryDaoImpl extends CommonDao implements IDataQueryDao{

    public List<MaterialGroupVO> queryMaterialGroupByStandard(String standardId) throws Exception {
        List<MaterialGroupVO> result = new ArrayList<MaterialGroupVO>();
        List<Map<String, Object>> list = super.query(MATERIAL_GROUP_QUERY, new Object[] { standardId });
        if(list!=null && list.size()>0){
            for(Map<String,Object> map:list){
                MaterialGroupVO vo = new MaterialGroupVO();
                vo.setId((String)map.get("ID"));
                vo.setName((String) map.get("FNAME_L2"));
                vo.setNumber((String)map.get("FNUMBER"));

                result.add(vo);
            }
        }
        return result;
    }

    public List<MaterialVO> queryMaterialByGroupAndSaleOrgUnit(String groupId, String customerId, String cuid) throws Exception {
        List<MaterialVO> result = new ArrayList<MaterialVO>();
        List<Map<String, Object>> list = super.query(MATERIAL_QUERY, new Object[]{cuid, groupId,customerId});
        if(list!=null && list.size()>0){
            for(Map<String, Object> map:list){
                MaterialVO vo = new MaterialVO();
                vo.setId((String)map.get("FID"));
                vo.setName((String) map.get("FNAME_L2"));
                vo.setNumber((String) map.get("FNUMBER"));
                vo.setModel((String) map.get("FMODEL"));
                vo.setPrice((BigDecimal)map.get("FPRICE"));
                vo.setUnitId((String) map.get("FUNITID"));

                result.add(vo);
            }
        }

        return result;
    }

    public List<MeasureUnitVO> queryMeausreUnitByMaterialId(String materialId) throws Exception {
        List<MeasureUnitVO> result = new ArrayList<MeasureUnitVO>();
        List<Map<String, Object>> list = super.query(MEASURE_UNIT_QUERY, new Object[]{materialId});
        if(list!=null && list.size()>0){
            for(Map<String, Object> map:list){
                MeasureUnitVO vo = new MeasureUnitVO();
                vo.setId((String)map.get("FID"));
                vo.setName((String) map.get("FNAME"));
                vo.setNumber((String) map.get("FNUMBER"));
                vo.setUnitId((String) map.get("FUNITID"));
                vo.setBaseConvsRate((BigDecimal) map.get("FBASECONVSRATE"));
                vo.setBasicUnit((Boolean) map.get("FISBASEUNIT"));
                vo.setMaterialId((String) map.get("FMATERIALID"));
                vo.setQtyPrecision((Integer)map.get("FQTYPRECISION"));

                result.add(vo);
            }
        }

        return result;
    }

    public MaterialVO getMaterialVO(String materialId, String cuId) throws Exception {
        List<Map<String, Object>> list = super.query(MATERIAL_GET, new Object[]{materialId,cuId});
        if(list!=null && list.size()>0){
            for(Map<String, Object> map:list){
                MaterialVO vo = new MaterialVO();
                vo.setId((String) map.get("FID"));
                vo.setName((String) map.get("FNAME_L2"));
                vo.setNumber((String) map.get("FNUMBER"));
                vo.setUnitId((String) map.get("FUNITID"));
                vo.setModel((String) map.get("FMODEL"));
                vo.setPrice((BigDecimal)map.get("FPRICE"));

                return vo;
            }
        }

        return null;
    }

    public MeasureUnitVO getMeasureUnitVO(String materialId, String measureUnitId) throws Exception {
        List<MeasureUnitVO> result = new ArrayList<MeasureUnitVO>();
        List<Map<String, Object>> list = super.query(MEASURE_UNIT_GET, new Object[]{materialId,measureUnitId});
        if(list!=null && list.size()>0){
            for(Map<String, Object> map:list){
                MeasureUnitVO vo = new MeasureUnitVO();
                vo.setId((String)map.get("FID"));
                vo.setName((String) map.get("FNAME"));
                vo.setNumber((String) map.get("FNUMBER"));
                vo.setUnitId((String) map.get("FUNITID"));
                vo.setBaseConvsRate((BigDecimal) map.get("FBASECONVSRATE"));
                vo.setBasicUnit((Boolean) map.get("FISBASEUNIT"));
                vo.setMaterialId((String) map.get("FMATERIALID"));
                vo.setQtyPrecision((Integer)map.get("FQTYPRECISION"));

                return vo;
            }
        }

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

    public List<ShoppingCarVO> queryShoppingCarList(String userId, String saleOrgUnitId, String channelId) {
        return null;
    }

    public List<NetOrderVO> queryNetOrderList(String userId, String saleOrgUnitId, String channelId)
            throws BOSException, SQLException {
        return null;
    }
}
