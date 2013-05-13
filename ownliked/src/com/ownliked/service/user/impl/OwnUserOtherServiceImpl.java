package com.ownliked.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ownliked.dao.user.OwnUserOtherDao;
import com.ownliked.pojo.OwnUserOther;
import com.ownliked.service.user.OwnUserOtherService;

@Transactional(readOnly=true)
@Service("ownUserOtherService")
public class OwnUserOtherServiceImpl implements OwnUserOtherService {

	@Resource(name="ownUserOtherDao")
	private OwnUserOtherDao ownUserOtherDao;
	
	public OwnUserOtherDao getOwnUserOtherDao() {
		return ownUserOtherDao;
	}

	public void setOwnUserDao(OwnUserOtherDao ownUserOtherDao) {
		this.ownUserOtherDao = ownUserOtherDao;
	}

	@Override
	public int countOwnUserOther(OwnUserOther ownUserOther) {
		return this.getOwnUserOtherDao().countOwnUserOther(ownUserOther);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int deleteOwnUserOther(OwnUserOther ownUserOther) {
		return this.getOwnUserOtherDao().deleteOwnUserOther(ownUserOther);
	}

	@Override
	public OwnUserOther findOwnUserOtherAndOwnUser(OwnUserOther ownUserOther) {
		return this.getOwnUserOtherDao().findOwnUserOtherAndOwnUser(ownUserOther);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int insertOwnUserOther(OwnUserOther ownUserOther) {
		return this.getOwnUserOtherDao().insertOwnUserOther(ownUserOther);
	}

	@Override
	public List<OwnUserOther> queryOwnUserOther(OwnUserOther ownUserOther) {
		return this.getOwnUserOtherDao().queryOwnUserOther(ownUserOther);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int updateOwnUserOther(OwnUserOther ownUserOther) {
		return this.getOwnUserOtherDao().updateOwnUserOther(ownUserOther);
	}

}
