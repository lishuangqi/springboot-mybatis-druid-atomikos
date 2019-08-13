package com.wisesoft.plat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisesoft.annotation.DataSource;
import com.wisesoft.plat.dao.UserDao;
import com.wisesoft.plat.entity.UserEntity;
import com.wisesoft.plat.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Service("userService")
@DataSource(name="plat")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
	
	@Override
	public UserEntity login(String loginName) {
		UserEntity i = baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("login_name", loginName));
		return i;
	}

	@Override
	public boolean updatePassWord(String userId, String newPassWord) {
		UserEntity entity = new UserEntity();
		entity.setId(userId);
		entity.setPasswd(newPassWord);
		return super.updateById(entity);
	}

	@Override
	@Transactional
	public boolean save(UserEntity entity) {
//		int i = 1/0;
		return super.save(entity);
	}
	
	@Override
	public boolean modifyUserInfo(UserEntity entity) {
		if(entity == null) {
			return false;
		}
		return this.updateById(entity);
	}

	@Override
	public UserEntity selectById(Serializable id) {
		return baseMapper.selectById(id);
	}

	@Override
	public List<UserEntity> selectByAll(){
		return baseMapper.selectList(null);
	}
}
