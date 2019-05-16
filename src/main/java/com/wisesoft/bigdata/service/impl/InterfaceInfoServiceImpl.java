package com.wisesoft.bigdata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisesoft.annotation.DataSource;
import com.wisesoft.bigdata.dao.InterfaceInfoDao;
import com.wisesoft.bigdata.entity.InterfaceInfoEntity;
import com.wisesoft.bigdata.service.InterfaceInfoService;
import com.wisesoft.utils.PageQuery;
import com.wisesoft.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@DataSource(name = "bigdata")
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoDao, InterfaceInfoEntity> implements InterfaceInfoService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String searchName = (String)params.get("searchName");
        String serverId = (String) params.get("serverId");
        QueryWrapper<InterfaceInfoEntity> entityWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(searchName)) {
            entityWrapper.like("interface_code", searchName);
            entityWrapper.or();
            entityWrapper.like("name", searchName);
        }
        if(StringUtils.isNotBlank(serverId)) {
            entityWrapper.eq("server_id", serverId);
        }
        Page page = new PageQuery<InterfaceInfoEntity>(params).getPage();
        baseMapper.selectPage(
                page,
                entityWrapper
                //TODO
                //.like(StringUtils.isNotBlank(searchKey),"c_name", searchKey)
        );

        return new PageUtils(page);
    }

    @Override
    public List<InterfaceInfoEntity> listTypeForIsFetch() {
        List<InterfaceInfoEntity> list = baseMapper.selectList(
                new QueryWrapper<InterfaceInfoEntity>().eq("is_fetch", 1).eq("use_status", 1));
        return list;
    }

    @Override
    public List<InterfaceInfoEntity> listAll(){
        List<InterfaceInfoEntity> list = baseMapper.selectList(
                new QueryWrapper<InterfaceInfoEntity>());
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String serverId){
        int result = baseMapper.delete(new QueryWrapper<InterfaceInfoEntity>().eq("server_id", serverId));
        return result;
    }


    @Override
    @Transactional
    public boolean save(InterfaceInfoEntity entity) {
        return super.save(entity);
    }

}
