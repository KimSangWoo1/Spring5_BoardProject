package com.project.spring.board.service;

import java.util.List;
import java.util.Map;

import com.project.spring.board.util.Pagination;
import com.project.spring.board.vo.BoardVO;


public interface BoardService {
	//1. 게시글 리스트
	List<Map<String,Object>> selectBoardListService (Map<String,Object> map);
	//2. 게시글 작성
	void boardInsertService(BoardVO boardVO)throws Exception;
	//3. 게시글 상세 뷰
	BoardVO boardDetailViewService(int idx);
	//4. 게시글 조회수 올리기
	void boardAddHitCountService(BoardVO boardVO);
	//5. 게시글 총 갯수
	int boardListCnt();
	//6. 게시판 뷰페이징 리스트
	List<BoardVO> boardPagingService(Pagination pagination);
	//7. 게시판 삭제
	void boardDeleteService(int idx);
	//8. 게시판 수정
	void boardUpdateService(BoardVO boardVO);
	//9. 게시글 답글 준비 ( group_order , depth 값 져오기)
	BoardVO boardReplyReadyService(int idx);
	//10. 게시글 답글 달기 전  group_order의  마지막 값 조회 
	int boardLastGroupOrderService(int idx);
	//11. 게시글 답글 달기
	void boardReplyService(BoardVO boardVO);
	//12. 게시글 답 답글 달기전 depth 기준으로 group_order 마지막 값 조회
	int boardNextGroupOrderService(BoardVO boardVO);
	//13. 게시글 답 답글 작성시 order값 변동 될 것들 수정하기
	void boardAddGroupOrderService(BoardVO boardVO);
	//14. 게시글 삭제 전 준비
	BoardVO boardDeleteReadyService(int idx);
	//15. 게시글 원글이 아닐 경우 order 값 수정
	void boardSubGroupOrderService(BoardVO boardVO);
}
