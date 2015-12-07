package com.boocu.kingdee.eas.mobileorder.dao;

import com.boocu.kingdee.eas.mobileorder.vo.MaterialGroupVO;
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
    List<MaterialGroupVO> queryMaterialGroupByStandard(String standardId);

    /**
     * 查询网络订单
     *
     * @return
     */
    List<NetOrderVO> queryNetOrder();
}
