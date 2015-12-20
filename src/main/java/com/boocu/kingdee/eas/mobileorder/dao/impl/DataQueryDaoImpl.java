package com.boocu.kingdee.eas.mobileorder.dao.impl;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialGroupVO;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;
import com.boocu.kingdee.eas.mobileorder.vo.MeasureUnitVO;
import com.boocu.kingdee.eas.mobileorder.vo.NetOrderVO;
import com.kingdee.eas.mobilecommon.dao.CommonDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jordan on 15/12/6.
 */
public class DataQueryDaoImpl extends CommonDao implements IDataQueryDao{

    public List<MaterialGroupVO> queryMaterialGroupByStandard(String standardId) throws Exception {
        String sql = "select fid,fname_l2,fnumber from t_bd_materialgroup where fgroupstandard = ? and flevel = 1 and fisleaf = 1";

        List<MaterialGroupVO> result = new ArrayList<MaterialGroupVO>();
        List<Map<String, Object>> list = super.query(sql, new Object[]{standardId});
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

    public List<MaterialVO> queryMaterialByGroupAndSaleOrgUnit(String groupId, String saleOrgUnitId, String cuid) throws Exception {

        //todo 查询物料信息
        String sql = "select m.fid,m.fname_l2,m.fnumber,m.fmodel,mbp.fprice,mbp.funitid " +
                 " from t_bd_material m inner join t_sd_materialbaseprice mbp on m.fid = mbp.fmaterialid "
                +" where mbp.fcontrolunitid = ? and m.fmaterialgroupid = ? and m.fstatus = 1"     //核准状态
                +" ";

        //todo
        List<MaterialVO> result = new ArrayList<MaterialVO>();
        List<Map<String, Object>> list = super.query(sql, new Object[]{cuid, groupId});
        if(list!=null && list.size()>0){
            for(Map<String, Object> map:list){
                MaterialVO vo = new MaterialVO();
                vo.setId((String)map.get("FID"));
                vo.setName((String) map.get("FNAME_L2"));
                vo.setNumber((String) map.get("FNUMBER"));
                vo.setUnitId((String) map.get("FUNITID"));
                vo.setModel((String) map.get("FMODEL"));
                vo.setPrice((BigDecimal)map.get("FPRICE"));

                result.add(vo);
            }
        }

        return result;
    }

    public List<MeasureUnitVO> queryMeausreUnitByMaterialId(String materialId) throws Exception {
        String sql = " select u.fid as FUNITID,u.fname_l2 as FNAME,u.fnumber as FNUMBER,mmu.fid as FID,mmu.fisbaseunit AS FISBASEUNIT, "
                + " mmu.fbaseconvsrate as FBASECONVSRATE,mmu.FmaterialId as FMATERIALID,mmu.fqtyprecision as FQTYPRECISION "
                + " from T_bd_multimeasureUnit mmu inner join t_bd_measure u on mmu.fmeasureunitid = u.fid " +
                  " where mmu.fmaterialId = ? ";

        //todo 过滤字段
        List<MeasureUnitVO> result = new ArrayList<MeasureUnitVO>();
        List<Map<String, Object>> list = super.query(sql, new Object[]{materialId});
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
        //todo 查询物料信息
        String sql = "select m.fid,m.fname_l2,m.fnumber,m.fmodel,mbp.fprice,mbp.funitid " +
                " from t_bd_material m inner join t_sd_materialbaseprice mbp on m.fid = mbp.fmaterialid "
                +" where mbp.fcontrolunitid = ? and m.fid = ? and m.fstatus = 1"     //核准状态
                +" ";

        //todo
        List<Map<String, Object>> list = super.query(sql, new Object[]{materialId,cuId});
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
        //todo 查询计量单位
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
