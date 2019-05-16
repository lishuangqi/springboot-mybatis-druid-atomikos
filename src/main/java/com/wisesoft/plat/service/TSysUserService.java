package com.wisesoft.plat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wisesoft.plat.entity.TSysUserEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户表
 *
 * @author flyCloud
 * @email 
 * @date 2018-08-31 15:01:21
 */
public interface TSysUserService extends IService<TSysUserEntity> {

    /**
     * @Description 说明：
     * @param loginName 登录账号
     * @return
     */
    TSysUserEntity login(String loginName);
    
    /**
     * 更新密码
     * @param userId
     * @param newPassWord
     */
    boolean updatePassWord(String userId, String newPassWord);
    

	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	boolean modifyUserInfo(TSysUserEntity entity);
	

    TSysUserEntity selectById(Serializable id);
}

