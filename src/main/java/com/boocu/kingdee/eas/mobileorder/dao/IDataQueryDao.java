package com.boocu.kingdee.eas.mobileorder.dao;

import com.boocu.kingdee.eas.mobileorder.vo.MaterialGroupVO;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;
import com.boocu.kingdee.eas.mobileorder.vo.MeasureUnitVO;
import com.boocu.kingdee.eas.mobileorder.vo.NetOrderVO;

import java.util.List;

/**
 * Created by jordan on 15/12/6.
 */
public interface IDataQueryDao {

    /***
     * 根据分类标准，查询物料分组
     *
     * @param standardId        分类标准ID
     * @return                  返回物料分组列表。如果无数据，返回空列表，不会为null.
     */
    List<MaterialGroupVO> queryMaterialGroupByStandard(String standardId) throws Exception;

    /**
     * 根据物料分组和客户id，过滤物料数据
     *
     * @param groupId
     * @param customerId
     * @param  cuid
     * @return
     */
    List<MaterialVO> queryMaterialByGroupAndSaleOrgUnit(String groupId, String customerId, String cuid) throws Exception;

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
    MaterialVO getMaterialVO(String materialId,String cuId) throws  Exception;

    /**
     * 获取计量单位
     *
     * @param materialId
     * @param measureUnitId
     * @return
     * @throws Exception
     */
    MeasureUnitVO getMeasureUnitVO(String materialId,String measureUnitId) throws Exception;

    /**
     * 查询网络订单
     *
     * @return
     */
    List<NetOrderVO> queryNetOrder();
}
