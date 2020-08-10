package com.lishuangqi.db1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lishuangqi.db1.entity.UserEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表
 *
 * @author flyCloud
 * @email 
 * @date 2018-08-31 15:01:21
 */
public interface UserService extends IService<UserEntity> {

    /**
     * @Description 说明：
     * @param loginName 登录账号
     * @return
     */
    UserEntity login(String loginName);
    
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
	boolean modifyUserInfo(UserEntity entity);
	

    UserEntity selectById(Serializable id);

    List<UserEntity> selectByAll();
}

