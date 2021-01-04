package com.project.spring.board.dao;

import java.util.List;
import java.util.Map;

import com.project.spring.board.util.Pagination;
import com.project.spring.board.vo.BoardVO;

public interface BoardDAO {

	//1. �Խñ� ����Ʈ ����
	List<Map<String, Object>> selectBoardList(Map<String, Object> map);

	//2. �Խñ� �ۼ�
	void boardInsert(BoardVO boardVO);

	//3. �Խñ� �󼼺���
	BoardVO boardDetailView(int idx);
	
	//4.�Խñ� ��ȸ�� �ø���
	void boardAddHitCount(BoardVO boardVO);
	
	//5. �Խñ�  �� ���� ��������
	int boardAllListCnt();
	
	//6. �Խñ� ����¡���� ��������
	List<BoardVO> boardPaging(Pagination pagination);
	
	//7. �Խñ� ����
	void boardDelete(int idx);
	
	//8. �Խù� ����
	void boardUpdate(BoardVO boardVO);
	
	//9. 마지막 게시글 idx 가져오기
	int selectLastBoardNo();
	
	//10. 게시글 답글 준비 ( group_order , depth 값 져오기)
	BoardVO boardReplyReady(int idx);

	//11. 게시글 답글 달기 전  group_order의  마지막 값 조회 
	int boardLastGroupOrder(int idx); 
	
	//12. 게시글 답글 달기
	void boardReply(BoardVO boardVO);
	
	//13. 게시글 답 답글 달기전 다음 답글에 order 값을 찾는다.
	int boardNextGroupOrder(BoardVO boardVO);
	
	//14. 게시글 답 답글 작성시 order값 변동 될 것들 수정하기
	void boardAddGroupOrder(BoardVO boardVO);
	
	//15. 게시글 삭제 전 준비
	BoardVO boardDeleteReady(int idx);
	
	//16. 게시글 원글이 아닐 경우 order 값 수정
	void boardSubGroupOrder(BoardVO boardVO);
}
