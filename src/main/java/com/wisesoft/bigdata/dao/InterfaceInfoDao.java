package com.wisesoft.bigdata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wisesoft.bigdata.entity.InterfaceInfoEntity;

/**
 * 
 * 
 * @author flyCloud
 * @email 
 * @date 2018-08-28 15:27:21
 */
public interface InterfaceInfoDao extends BaseMapper<InterfaceInfoEntity> {

//    @Select("select * from interface_info where is_fetch=1 and use_status =1 ")
//    List<InterfaceInfo> listTypeForIsFetch();
//
//    @Select("select * from interface_info ")
//    List<InterfaceInfo> listAll();
}
