package com.ownliked.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ownliked.pojo.OwnUser;
import com.ownliked.service.user.OwnUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application*.xml")
public class OwnUserServiceTest {

	@Resource
	private OwnUserService ownUserService;

	public OwnUserService getOwnUserService() {
		return ownUserService;
	}

	public void setOwnUserService(OwnUserService ownUserService) {
		this.ownUserService = ownUserService;
	}
	
	@Test
	public void test_countOwnUser(){
		OwnUser ownUser = new OwnUser();
		int result = ownUserService.countOwnUser(ownUser);
		System.out.println("result : " + result);
	}
	
	@Test
	public void test_insertOwnUser(){
		OwnUser ownUser = new OwnUser();
		ownUser.setFirstName("田");
		ownUser.setLastName("七");
		ownUser.setEmail("324234@wer.com");
		ownUser.setPassword("222222");
		int result = this.ownUserService.insertOwnUser(ownUser);
		System.out.println("result : " + result);
	}
	
	@Test
	public void udpateOwnUser(){
		OwnUser ownUser = new OwnUser();
		ownUser.setId(1);
		OwnUser ow = this.ownUserService.findOwnUser(ownUser);
		System.out.println(ow.getFirstName());
		ow.setFirstName("li");
		this.ownUserService.updateOwnUser(ow);
	}
	
	@Test
	public void test_deleteOwnUser(){
		OwnUser ownUser = new OwnUser();
		ownUser.setId(1);
		int result = this.ownUserService.deleteOwnUser(ownUser);
		System.out.println("result : " + result);
	}
	
	@Test
	public void test_queryOwnUser(){
		OwnUser ownUser = new OwnUser();
		List<OwnUser> ownUserList = this.ownUserService.queryOwnUser(ownUser);
		for(OwnUser ou : ownUserList){
			System.out.println(ou.getFirstName());
		}
	}
	
}
