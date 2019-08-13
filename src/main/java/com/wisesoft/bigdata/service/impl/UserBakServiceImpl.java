package com.wisesoft.bigdata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisesoft.annotation.DataSource;
import com.wisesoft.bigdata.dao.UserBakDao;
import com.wisesoft.bigdata.entity.UserBakEntity;
import com.wisesoft.bigdata.service.UserBakService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service("userBakService")
@DataSource(name="bigdata")
public class UserBakServiceImpl extends ServiceImpl<UserBakDao, UserBakEntity> implements UserBakService {
	
	@Override
	public UserBakEntity login(String loginName) {
		UserBakEntity i = baseMapper.selectOne(new QueryWrapper<UserBakEntity>().eq("login_name", loginName));
		return i;
	}

	@Override
	public boolean updatePassWord(String userId, String newPassWord) {
		UserBakEntity entity = new UserBakEntity();
		entity.setId(userId);
		entity.setPasswd(newPassWord);
		return super.updateById(entity);
	}

	@Override
	@Transactional
	public boolean save(UserBakEntity entity) {
//		int i = 1/0;
		return super.save(entity);
	}
	
	@Override
	public boolean modifyUserInfo(UserBakEntity entity) {
		if(entity == null) {
			return false;
		}
		return this.updateById(entity);
	}

	@Override
	public UserBakEntity selectById(Serializable id) {
		return baseMapper.selectById(id);
	}
	
}
