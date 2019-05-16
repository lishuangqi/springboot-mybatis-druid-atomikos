package com.wisesoft.bigdata.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wisesoft.bigdata.entity.InterfaceInfoEntity;
import com.wisesoft.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author flyCloud
 * @email 
 * @date 2018-08-28 15:27:21
 */
public interface InterfaceInfoService extends IService<InterfaceInfoEntity> {

//    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params);

    List<InterfaceInfoEntity> listTypeForIsFetch();

    List<InterfaceInfoEntity> listAll();

    @Transactional(rollbackFor = Exception.class)
    int delete(String serverId);

}

