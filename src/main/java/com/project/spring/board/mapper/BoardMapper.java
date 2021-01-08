package com.project.spring.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.spring.board.util.Pagination;
import com.project.spring.board.util.Search;
import com.project.spring.board.vo.BoardVO;

@Mapper
public interface BoardMapper {

	//1. 게시글 리스트
	List<Map<String, Object>> selectBoardList(Map<String, Object> map);

	//2. 게시글 작성
	void boardInsert(BoardVO boardVO);
	//2-1 작성한 게시글 group_order 값 설정
	void boardUpdateGroupNO(BoardVO boardVO);
	
	//3. 게시글 상세 내용
	BoardVO boardDetailView(@Param("idx")int idx);
	
	//4. 게시글 조회수 올리기
	void boardAddHitCount(BoardVO boardVO);
	
	//5. 게시글 총 갯수 
	int boardAllListSize(Search search);
	
	//6. 뷰 페이징 게시글 리스트
	List<BoardVO> boardPagingList(Search search);
	
	//7. 게시글 삭제
	void boardDelete(@Param("idx")int idx);
	
	//8. 게시글 수정
	void boardUpdate(BoardVO boardVO);
	
	//9. 마지막 게시글 idx 가져오기
	int selectLastBoardNo();
	
	//10. 게시글 답글 준비 ( group_order , depth 값 져오기)
	BoardVO boardReplyReady(@Param("idx")int idx);

	//11. 게시글 답글 달기 전  group_order의  마지막 값 조회 
	int boardLastGroupOrder(@Param("idx")int idx); 
	
	//12. 게시글 답글 달기
	void boardReply(BoardVO boardVO);
	
	//13. 게시글 답 답글 달기전 다음 답글에 order 값을 찾는다.
	int boardNextGroupOrder(BoardVO boardVO);
	
	//14. 게시글 답 답글 작성시 order값 변동 될 것들 수정하기
	void boardAddGroupOrder(BoardVO boardVO);
	
	//15. 게시글 삭제 전 준비
	BoardVO boardDeleteReady(@Param("idx")int idx);
	
	//16. 게시글 원글이 아닐 경우 order 값 수정
	void boardSubGroupOrder(BoardVO boardVO);
	
	//17. 게시글 검색 & 리스트
	BoardVO boardSearchList(BoardVO boardVO);
	
}
