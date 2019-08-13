package com.wisesoft.bigdata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wisesoft.bigdata.entity.UserBakEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表
 *
 * @author flyCloud
 * @email 
 * @date 2018-08-31 15:01:21
 */
public interface UserBakService extends IService<UserBakEntity> {

    /**
     * @Description 说明：
     * @param loginName 登录账号
     * @return
     */
	UserBakEntity login(String loginName);
    
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
	boolean modifyUserInfo(UserBakEntity entity);


	UserBakEntity selectById(Serializable id);

	List<UserBakEntity> selectByAll();
}

