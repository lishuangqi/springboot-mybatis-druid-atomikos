package com.wisesoft.plat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisesoft.annotation.DataSource;
import com.wisesoft.plat.dao.TSysUserDao;
import com.wisesoft.plat.entity.TSysUserEntity;
import com.wisesoft.plat.service.TSysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service("tSysUserService")
@DataSource(name="plat")
public class TSysUserServiceImpl extends ServiceImpl<TSysUserDao, TSysUserEntity> implements TSysUserService {
	
	@Override
	public TSysUserEntity login(String loginName) {
		TSysUserEntity i = baseMapper.selectOne(new QueryWrapper<TSysUserEntity>().eq("login_name", loginName));
		return i;
	}

	@Override
	public boolean updatePassWord(String userId, String newPassWord) {
		TSysUserEntity entity = new TSysUserEntity();
		entity.setId(userId);
		entity.setPasswd(newPassWord);
		return super.updateById(entity);
	}

	@Override
	@Transactional
	public boolean save(TSysUserEntity entity) {
		return super.save(entity);
	}
	
	@Override
	public boolean modifyUserInfo(TSysUserEntity entity) {
		if(entity == null) {
			return false;
		}
		return this.updateById(entity);
	}

	@Override
	public TSysUserEntity selectById(Serializable id) {
		return baseMapper.selectById(id);
	}
	
}
