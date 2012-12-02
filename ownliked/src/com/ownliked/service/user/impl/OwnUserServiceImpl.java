package com.ownliked.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ownliked.dao.user.OwnUserDao;
import com.ownliked.pojo.OwnUser;
import com.ownliked.service.user.OwnUserService;

@Transactional(readOnly=true)
@Service("ownUserService")
public class OwnUserServiceImpl implements OwnUserService {

	@Resource(name="ownUserDao")
	private OwnUserDao ownUserDao;
	
	public OwnUserDao getOwnUserDao() {
		return ownUserDao;
	}

	public void setOwnUserDao(OwnUserDao ownUserDao) {
		this.ownUserDao = ownUserDao;
	}

	@Override
	public int countOwnUser(OwnUser ownUser) {
		return this.getOwnUserDao().countOwnUser(ownUser);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int deleteOwnUser(OwnUser ownUser) {
		return this.getOwnUserDao().deleteOwnUser(ownUser);
	}

	@Override
	public OwnUser findOwnUser(OwnUser ownUser) {
		return this.getOwnUserDao().findOwnUser(ownUser);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int insertOwnUser(OwnUser ownUser) {
		return this.getOwnUserDao().insertOwnUser(ownUser);
	}

	@Override
	public List<OwnUser> queryOwnUser(OwnUser ownUser) {
		return this.getOwnUserDao().queryOwnUser(ownUser);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int updateOwnUser(OwnUser ownUser) {
		return this.getOwnUserDao().updateOwnUser(ownUser);
	}

}
