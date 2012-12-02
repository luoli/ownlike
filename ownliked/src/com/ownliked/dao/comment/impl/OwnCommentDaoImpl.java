package com.ownliked.dao.comment.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ownliked.dao.comment.OwnCommentDao;
import com.ownliked.dao.support.BaseDaoImpl;
import com.ownliked.pojo.OwnComment;

@Repository("ownCommentDao")
public class OwnCommentDaoImpl extends BaseDaoImpl<OwnComment, Object> implements OwnCommentDao {

	@Override
	public int countOwnComment(OwnComment ownComment) {
		return countEntity("ownCommentSqlMap.countOwnComment", ownComment);
	}

	@Override
	public int deleteOwnComment(OwnComment ownComment) {
		return deleteEntity("ownCommentSqlMap.deleteOwnComment", ownComment);
	}

	@Override
	public OwnComment findOwnComment(OwnComment ownComment) {
		return findEntity("ownCommentSqlMap.findOwnComment", ownComment);
	}

	@Override
	public int insertOwnComment(OwnComment ownComment) {
		return insertEntity("ownCommentSqlMap.insertOwnComment", ownComment);
	}

	@Override
	public List<OwnComment> queryOwnComment(OwnComment ownComment) {
		return queryEntity("ownCommentSqlMap.queryOwnComment", ownComment);
	}

	@Override
	public int updateOwnComment(OwnComment ownComment) {
		return updateEntity("ownCommentSqlMap.updateOwnComment", ownComment);
	}

}
