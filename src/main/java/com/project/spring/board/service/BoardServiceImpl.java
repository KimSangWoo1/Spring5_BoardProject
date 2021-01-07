package com.project.spring.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spring.board.common.Logging;
import com.project.spring.board.mapper.BoardMapper;
import com.project.spring.board.util.Pagination;
import com.project.spring.board.vo.BoardVO;




@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	//1.게시글 리스트
	@Override
	public List<Map<String, Object>> selectBoardListService(Map<String, Object> map) {
		return boardMapper.selectBoardList(map);
	}

	//2. 게시글 작성
	@Override
	public void boardInsertService(BoardVO boardVO) throws Exception {
		 boardMapper.boardInsert(boardVO);
		 
			int idx = boardMapper.selectLastBoardNo();
			boardVO.setIdx(idx);
			boardVO.setGroup_no(idx);
			//group_no 값 넣어주기
			boardMapper.boardUpdateGroupNO(boardVO);
	}

	//3. 게시글 상세 내용
	@Override
	public BoardVO boardDetailViewService(int idx) {
		 return boardMapper.boardDetailView(idx);
	}
	
	//4. 게시글 조회수 증가
	@Override
	public void boardAddHitCountService(BoardVO boardVO) {
	
		//조회수 get
		int hit_count = boardVO.getHit_count();
		//조회수 +1 하기
		boardVO.setHit_count(hit_count+1);
		boardMapper.boardAddHitCount(boardVO);
	}
	
	//5. 게시글 총 갯수 (뷰 페이징)
	@Override
	public int boardAllListSize() {
		return boardMapper.boardAllListSize();
	}

	//6. 게시글 뷰 페이징 리스트
	@Override
	public List<BoardVO> boardPagingListService(Pagination pagination) {

		return boardMapper.boardPagingList(pagination);
	}
	//7. 게시글 삭제
	@Override
	public void boardDeleteService(int idx) {
		boardMapper.boardDelete(idx);
	}	
	//8. 게시글 수정
	@Override
	public void boardUpdateService(BoardVO boardVO) {
		System.out.println("1수정 - > 제목: "+boardVO.getTitle());
		boardMapper.boardUpdate(boardVO);
	}
	//9. 게시글 답글 준비 ( group_order , depth 값 져오기)
	@Override
	public BoardVO boardReplyReadyService(int idx) {
		return boardMapper.boardReplyReady(idx);
	}
	//10. 게시글 답글 달기 전  group_order의  마지막 값 조회 
	@Override
	public int boardLastGroupOrderService(int idx) {
		return boardMapper.boardLastGroupOrder(idx);
	}
	//11. 게시글 답글 달기
	@Override
	public void boardReplyService(BoardVO boardVO) {
		boardMapper.boardReply(boardVO);
	}
	//12. 게시글 답 답글 달기전 다음 답글에 order 값을 찾는다.
	@Override
	public int boardNextGroupOrderService(BoardVO boardVO) {	
		return boardMapper.boardNextGroupOrder(boardVO);
	}
	//13. 게시글 답 답글 작성시 order값 변동 될 것들 수정하기
	@Override
	public void boardAddGroupOrderService(BoardVO boardVO) {
		boardMapper.boardAddGroupOrder(boardVO);
	}
	//14. 게시글 삭제 전 준비
	@Override
	public BoardVO boardDeleteReadyService(int idx) {
		return boardMapper.boardDeleteReady(idx);
	}
	//15. 게시글 원글이 아닐 경우 order 값 수정
	@Override
	public void boardSubGroupOrderService(BoardVO boardVO) {
		boardMapper.boardSubGroupOrder(boardVO);
	}
	
}
