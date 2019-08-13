package com.wisesoft.bigdata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wisesoft.bigdata.entity.UserBakEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 * @author flyCloud
 * @email 
 * @date 2018-08-31 15:01:21
 */
public interface UserBakDao extends BaseMapper<UserBakEntity> {
	
	/**
	 * 查询出所有用户的信息，用于IM服务器接口提供
	 * @return
	 */
	@Select("SELECT * FROM user ")
	List<Map<String,Object>> findAllUser();

}
