package com.boocu.kingdee.eas.mobileorder.dao;

import com.boocu.kingdee.eas.mobileorder.vo.*;
import com.kingdee.bos.BOSException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jordan on 15/12/6.
 */
public interface IDataQueryDao {

    String MATERIAL_GROUP_QUERY = "select fid,fname_l2,fnumber from t_bd_materialgroup where fgroupstandard = ? and flevel = 1 ";

    // 物料、物料基本价格、客户可销售物料
    String MATERIAL_QUERY = "select m.fid,m.fname_l2,m.fnumber,m.fmodel,mbp.fprice,mbp.funitid "
                            + " from t_bd_material m inner join t_sd_materialbaseprice mbp on m.fid = mbp.fmaterialid "
                            + " inner join T_CHA_CustomersMR mr on mr.fmaterialId = m.fid "
                            + " where mbp.fcontrolunitid = ? and m.fmaterialgroupid = ? and m.fstatus = 1 " // 核准状态
                            + " and mr.fcustomerId = ? and mr.fstatus = 0  ";

    String MEASURE_UNIT_QUERY = " select u.fid as FUNITID,u.fname_l2 as FNAME,u.fnumber as FNUMBER,mmu.fid as FID,mmu.fisbaseunit AS FISBASEUNIT, "
                                + " mmu.fbaseconvsrate as FBASECONVSRATE,mmu.FmaterialId as FMATERIALID,mmu.fqtyprecision as FQTYPRECISION "
                                + " from T_bd_multimeasureUnit mmu inner join t_bd_measure u on mmu.fmeasureunitid = u.fid "
                                + " where mmu.fmaterialId = ? ";

    String MATERIAL_GET = "select m.fid,m.fname_l2,m.fnumber,m.fmodel,mbp.fprice,mbp.funitid "
                          + " from t_bd_material m inner join t_sd_materialbaseprice mbp on m.fid = mbp.fmaterialid "
                          + " where  m.fid = ? and mbp.fcontrolunitid = ? and m.fstatus = 1" // 核准状态
                          + " ";

    String MEASURE_UNIT_GET = " select u.fid as FUNITID,u.fname_l2 as FNAME,u.fnumber as FNUMBER,mmu.fid as FID,mmu.fisbaseunit AS FISBASEUNIT, "
                              + " mmu.fbaseconvsrate as FBASECONVSRATE,mmu.FmaterialId as FMATERIALID,mmu.fqtyprecision as FQTYPRECISION "
                              + " from T_bd_multimeasureUnit mmu inner join t_bd_measure u on mmu.fmeasureunitid = u.fid "
                              + " where mmu.fmaterialId = ? and mmu.fmeasureUnitId = ? ";

    String SHOPPING_CARD_QUERY = "select r.fid as id,r.fqty as qty,r.fprice as price,"
                                 + " r.famount as amount,r.fshopdate as shopdate,"
                                 + " m.fname_l2 as materialName,m.fid as materialId"
                                 + " u.fname_l2 as unitName,u.fid as unitId " + " from t_cha_shoppingcartrecord r "
                                 + " inner join t_bd_material m on r.fmaterialid = m.id "
                                 + " inner join t_bd_measureunit u on r.funitId = u.fid "
                                 + " where r.fcreatorid = ? and r.fsaleorgunitid = ? and r.fchannelid = ?";

    /***
     * 根据分类标准，查询物料分组
     *
     * @param standardId 分类标准ID
     * @return 返回物料分组列表。如果无数据，返回空列表，不会为null.
     */
    List<MaterialGroupVO> queryMaterialGroupByStandard(String standardId) throws Exception;

    /**
     * 根据物料分组和客户id，过滤物料数据
     *
     * @param groupId
     * @param customerId
     * @param cuid
     * @return
     */
    List<MaterialVO> queryMaterialByGroupAndSaleOrgUnit(String groupId, String customerId,
                                                        String cuid) throws Exception;

    /***
     * 查询计量单位
     *
     * @param materialId
     * @return
     * @throws Exception
     */
    List<MeasureUnitVO> queryMeausreUnitByMaterialId(String materialId) throws Exception;

    /**
     * 获取物料
     *
     * @param materialId
     * @param cuId
     * @return
     * @throws Exception
     */
    MaterialVO getMaterialVO(String materialId, String cuId) throws Exception;

    /**
     * 获取计量单位
     *
     * @param materialId
     * @param measureUnitId
     * @return
     * @throws Exception
     */
    MeasureUnitVO getMeasureUnitVO(String materialId, String measureUnitId) throws Exception;

    /**
     * 查询网络订单
     *
     * @return
     */
    List<NetOrderVO> queryNetOrder();

    /**
     * 通过销售组织和渠道ID过滤有效的购物车列表
     *
     * @param userId 用户ID
     * @param saleOrgUnitId 销售组织
     * @param channelId 渠道ID
     * @return
     */
    List<ShoppingCarVO> queryShoppingCarList(String userId, String saleOrgUnitId, String channelId) throws BOSException,
                                                                                                    SQLException;

    /**
     * 通过用户id、销售组织、渠道，查询对应的网上订单
     *
     * @param userId
     * @param saleOrgUnitId
     * @param channelId
     * @return
     */
    List<NetOrderVO> queryNetOrderList(String userId, String saleOrgUnitId, String channelId) throws BOSException,
                                                                                            SQLException;

}
