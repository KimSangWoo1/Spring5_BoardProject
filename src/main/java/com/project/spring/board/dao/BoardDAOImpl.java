package com.project.spring.board.dao;

import java.util.List;
import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.spring.board.util.Pagination;
import com.project.spring.board.vo.BoardVO;


@Repository()
public class BoardDAOImpl implements BoardDAO {


	//@Resource(name = "sqlSession")
	@Autowired
	private SqlSessionTemplate sqlSession;

	//1. 게시글 리스트
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
		// sql   namespace : board , id: selectList
		return sqlSession.selectList("board.selectBoardList", map);
	}
	
	//2. 게시글 작성
	public void boardInsert(BoardVO boardVO) {
		
		sqlSession.insert("board.boardInsert", boardVO);
		//작성한 게시글 번호 가져오기
		int idx = selectLastBoardNo();
		boardVO.setIdx(idx);
		boardVO.setGroup_no(idx);
		//group_no 값 넣어주기
		sqlSession.update("board.boardUpdateGroupNO",boardVO);
	}

	//3. 게시글 상세 내용
	public BoardVO boardDetailView(int idx) {

		return sqlSession.selectOne("board.boardDetailView", idx);
	}
	
	//4. 게시글 조회수 올리기
	@Override
	public void boardAddHitCount(BoardVO boardVO) {
		sqlSession.selectOne("board.boardAddHitCount", boardVO);
	}

	//5. 게시글 총 개시글 수 (뷰페이징)
	@Override
	public int boardAllListCnt() {	
		return sqlSession.selectOne("board.boardListSize");
	}
	//6. 게시글 뷰 페이징 리스트
	@Override
	public List<BoardVO> boardPaging(Pagination pagination) {
		System.out.println("페이징 시작 index :"+pagination.getStartIndex());
		System.out.println("페이징 사이즈 :"+pagination.getPageSize());
		System.out.println("페이징 현재 페이지 :"+pagination.getCurPage());
		return sqlSession.selectList("board.boardPagingList",pagination);
	}
	//7. 게시글 삭제
	@Override
	public void boardDelete(int idx) {
		sqlSession.delete("board.boardDelete",idx);
	}
	
	//8. 게시글 수정
	@Override
	public void boardUpdate(BoardVO boardVO) {
		sqlSession.update("board.boardUpdate", boardVO);
	}
	//9. 마지막 게시글 idx 가져오기 
	@Override
	public int selectLastBoardNo() {
		return sqlSession.selectOne("board.lastBoardNO");
	}
	//10. 게시글 답글 준비 ( group_order , depth 값 져오기)
	@Override
	public BoardVO boardReplyReady(int idx) {
		return sqlSession.selectOne("board.boardReplyReady",idx);
	}
	//11.게시글 답글 달기 전  group_order의  마지막 값 조회 
	@Override
	public int boardLastGroupOrder(int idx) {
		return sqlSession.selectOne("board.boardLastGroupOrder",idx);
	}
	//12. 게시글 답글 달기
	@Override
	public void boardReply(BoardVO boardVO) {
		sqlSession.insert("board.boardReply",boardVO);
	}
	//13. 게시글 답 답글 달기전 다음 답글에 order 값을 찾는다.
	@Override
	public int boardNextGroupOrder(BoardVO boardVO) {
		return sqlSession.selectOne("board.boardNextGroupOrder", boardVO);
	}
	//14. 게시글 답 답글 작성시 order값 변동 될 것들 수정하기
	@Override
	public void boardAddGroupOrder(BoardVO boardVO) {
		sqlSession.update("board.boardAddGroupOrder",boardVO);
	}

	//15. 게시글 삭제 전 준비
	@Override
	public BoardVO boardDeleteReady(int idx) {
		return sqlSession.selectOne("board.boardDeleteReady",idx);
	}
	//16. 게시글 원글이 아닐 경우 order 값 수정
	@Override
	public void boardSubGroupOrder(BoardVO boardVO) {
		sqlSession.update("board.boardSubGroupOrder", boardVO);
	}

}
