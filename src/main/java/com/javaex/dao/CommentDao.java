package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {

	@Autowired
	SqlSession sqlSession;
	
	//댓글 작성
	public void insertSelectKey(CommentVo commentVo) {
		sqlSession.insert("comment.insertSelectKey" , commentVo);
	}
	
	//댓글 리스트
	public List<CommentVo> CommentList(int nanumNo){
		return sqlSession.selectList("comment.commentList",nanumNo);
	}
	
	//최근작성 댓글 조회
	public CommentVo cmtSelectOne(int commentNo){
		return sqlSession.selectOne("comment.commentOne",commentNo);
	}
	
	//댓글 삭제
	public int CmtDel(int commentNo){
		return sqlSession.delete("comment.commentDel",commentNo);
	}
	
	//게시글 삭제 전 댓글 전부 삭제
	public int nanumCmtDel(int nanumNo) {
		return sqlSession.delete("comment.commentDelAll", nanumNo);
	}
}
