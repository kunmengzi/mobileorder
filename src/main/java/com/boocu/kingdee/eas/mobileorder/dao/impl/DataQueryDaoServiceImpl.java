package com.boocu.kingdee.eas.mobileorder.dao.impl;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类DataQueryDaoServiceImpl.java的实现描述：#TODO 类实现描述
 *
 * @author jordan 15/12/27 21:36
 */
public class DataQueryDaoServiceImpl implements IDataQueryDao {

    private Context context;

    public DataQueryDaoServiceImpl(Context ctx) {
        super();
        this.context = ctx;
    }

    public List<MaterialGroupVO> queryMaterialGroupByStandard(String standardId) throws Exception {
        IRowSet rs = DbUtil.executeQuery(context, MATERIAL_GROUP_QUERY, new Object[] { standardId });

        // 物料组
        List<MaterialGroupVO> result = new ArrayList<MaterialGroupVO>();
        while (rs.next()) {
            MaterialGroupVO vo = new MaterialGroupVO();
            vo.setId(rs.getString("ID"));
            vo.setName(rs.getString("FNAME_L2"));
            vo.setNumber(rs.getString("FNUMBER"));

            result.add(vo);
        }

        return result;
    }

    public List<MaterialVO> queryMaterialByGroupAndSaleOrgUnit(String groupId, String customerId,
                                                               String cuid) throws Exception {
        List<MaterialVO> result = new ArrayList<MaterialVO>();
        IRowSet rs = DbUtil.executeQuery(context, MATERIAL_QUERY, new Object[] { cuid, groupId, customerId });
        while (rs.next()) {
            MaterialVO vo = new MaterialVO();
            vo.setId(rs.getString("FID"));
            vo.setName(rs.getString("FNAME_L2"));
            vo.setNumber(rs.getString("FNUMBER"));
            vo.setModel(rs.getString("FMODEL"));
            vo.setPrice(rs.getBigDecimal("FPRICE"));
            vo.setUnitId(rs.getString("FUNITID"));

            result.add(vo);
        }

        return result;
    }

    public List<MeasureUnitVO> queryMeausreUnitByMaterialId(String materialId) throws Exception {
        List<MeasureUnitVO> result = new ArrayList<MeasureUnitVO>();
        IRowSet rs = DbUtil.executeQuery(context, MEASURE_UNIT_QUERY, new Object[] { materialId });
        while (rs.next()) {
            MeasureUnitVO vo = new MeasureUnitVO();
            vo.setId(rs.getString("FID"));
            vo.setName(rs.getString("FNAME"));
            vo.setNumber(rs.getString("FNUMBER"));
            vo.setUnitId(rs.getString("FUNITID"));
            vo.setBaseConvsRate(rs.getBigDecimal("FBASECONVSRATE"));
            vo.setBasicUnit(rs.getBoolean("FISBASEUNIT"));
            vo.setMaterialId(rs.getString("FMATERIALID"));
            vo.setQtyPrecision(rs.getInt("FQTYPRECISION"));

            result.add(vo);
        }

        return result;
    }

    public MaterialVO getMaterialVO(String materialId, String cuId) throws Exception {
        IRowSet rs = DbUtil.executeQuery(context, MATERIAL_GET, new Object[] { materialId, cuId });
        if (rs.next()) {
            MaterialVO vo = new MaterialVO();
            vo.setId(rs.getString("FID"));
            vo.setName(rs.getString("FNAME_L2"));
            vo.setNumber(rs.getString("FNUMBER"));
            vo.setUnitId(rs.getString("FUNITID"));
            vo.setModel(rs.getString("FMODEL"));
            vo.setPrice(rs.getBigDecimal("FPRICE"));

            return vo;
        }

        return null;
    }

    public MeasureUnitVO getMeasureUnitVO(String materialId, String measureUnitId) throws Exception {
        List<MeasureUnitVO> result = new ArrayList<MeasureUnitVO>();
        IRowSet rs = DbUtil.executeQuery(context, MEASURE_UNIT_GET, new Object[] { materialId, measureUnitId });
        if (rs.next()) {
            MeasureUnitVO vo = new MeasureUnitVO();
            vo.setId(rs.getString("FID"));
            vo.setName(rs.getString("FNAME"));
            vo.setNumber(rs.getString("FNUMBER"));
            vo.setUnitId(rs.getString("FUNITID"));
            vo.setBaseConvsRate(rs.getBigDecimal("FBASECONVSRATE"));
            vo.setBasicUnit(rs.getBoolean("FISBASEUNIT"));
            vo.setMaterialId(rs.getString("FMATERIALID"));
            vo.setQtyPrecision(rs.getInt("FQTYPRECISION"));

            return vo;
        }

        return null;
    }

    public List<NetOrderVO> queryNetOrder() {
        return null;
    }

    public List<ShoppingCarVO> queryShoppingCarList(String userId, String saleOrgUnitId, String channelId)
            throws BOSException, SQLException {

        IRowSet rs = DbUtil.executeQuery(context,SHOPPING_CARD_QUERY,new Object[]{userId,saleOrgUnitId,channelId});
        List<ShoppingCarVO> result = new ArrayList<ShoppingCarVO>();
        while (rs.next()) {
            ShoppingCarVO vo = new ShoppingCarVO();
            vo.setId(rs.getString("ID"));
            vo.setQty(rs.getBigDecimal("QTY"));
            vo.setAmount(rs.getBigDecimal("AMOUNT"));
            vo.setPrice(rs.getBigDecimal("PRICE"));
            vo.setShopDate(rs.getDate("SHOPDATE"));

            MaterialVO materialVO = new MaterialVO();
            materialVO.setId(rs.getString("MATERIALID"));
            materialVO.setName(rs.getString("MATERIALNAME"));
            vo.setMaterialVO(materialVO);

            MeasureUnitVO unitVO = new MeasureUnitVO();
            unitVO.setId(rs.getString("UNITID"));
            unitVO.setName(rs.getString("UNITNAME"));
            vo.setMeasureUnitVO(unitVO);

            result.add(vo);
        }

        return result;
    }

    public List<NetOrderVO> queryNetOrderList(String userId, String saleOrgUnitId, String channelId)
            throws BOSException, SQLException {
        //todo 查看网上订单需要哪些字段
        return null;
    }
}
