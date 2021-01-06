package com.project.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.spring.board.service.BoardService;
import com.project.spring.board.util.Pagination;
import com.project.spring.board.vo.BoardVO;


@Controller
public class BoardController {
	Logger log = Logger.getLogger(this.getClass().toGenericString());

	// DB Servcie
	@Autowired
	private BoardService boardService;

	// 1. 게시글 목록
	@RequestMapping("board/boardList.do")
	public ModelAndView boardList(Map<String, Object> map) throws Exception {
		ModelAndView mv = new ModelAndView("/jsp/board/boardList");

		List<Map<String, Object>> list = boardService.selectBoardListService(map);
		mv.addObject("list", list);
		return mv;
	}

	// 2. 게시글 작성 뷰
	@RequestMapping("board/boardWrite.do")
	public ModelAndView BoardWrite() throws Exception {
		ModelAndView mv = new ModelAndView("/jsp/board/boardWrite");
		return mv;
	}

	// 3. 게시글 작성
	@RequestMapping(value = "board/boardInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView boardInsert(HttpServletRequest request) throws Exception {
		log.info("게시글 작성");
		
		HttpSession session = request.getSession();
		String create_id = session.getAttribute("userid").toString();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setCreate_id(create_id);
		boardVO.setTitle(title);
		boardVO.setContent(content);
		
		boardService.boardInsertService(boardVO);
		ModelAndView mv = new ModelAndView("redirect:boardList.do/");	
		return mv;
	}

	// 4. 게시글 상세 뷰
	@RequestMapping(value = "board/boardDetailView.do", method = RequestMethod.GET)
	public ModelAndView boardDetailView(HttpServletRequest request, @RequestParam int idx,
			@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(required = false, defaultValue = "0") int hit_count) {

		// 조회수 올리기
		BoardVO boardVO = boardService.boardDetailViewService(idx);
		if (hit_count != 0) {
			boardService.boardAddHitCountService(boardVO);
		}

		// 게시글 상세 내용 가져오기
		ModelAndView mv = new ModelAndView("/jsp/board/boardDetailView");
		mv.addObject("boardVO", boardVO);
		mv.addObject("curPage", curPage);

		HttpSession session = request.getSession();
		String session_id = (String) session.getAttribute("userid");
		String user_id = boardVO.getCreate_id();

		// 세션 확인
		boolean edit;
		if (session_id.equals(user_id)) {
			edit = true;
		} else {
			edit = false;
		}

		mv.addObject("edit", edit);

		return mv;
	}

	// 5. 뷰 페이징 리스트 뷰
	@RequestMapping(value = "board/boardPagingList.do", method = RequestMethod.GET)
	public ModelAndView boardPagingList(@RequestParam(defaultValue = "1") int curPage) throws Exception {
		ModelAndView mv = new ModelAndView("/jsp/board/boardViewPaging");

		// 1. 게시글  총 갯수 가져오기
		int listCnt = boardService.boardAllListSize();
		// 2. 뷰페이징 범위, 페이지 갯수 설정
		Pagination pagination = new Pagination(listCnt, curPage);
		// 3. 뷰페이징 게시글 리스트 가져오기
		List<BoardVO> boardList = boardService.boardPagingListService(pagination);
		for (BoardVO board : boardList) {
			System.out.println(board.getTitle());
		}
		// 4. 뷰페이징 게시글 리스트 모델 뷰 반환
		mv.addObject("boardList", boardList);
		mv.addObject("pagination", pagination);
		return mv;
	}

	// 6. 게시글 삭제
	@RequestMapping(value = "board/boardDelete.do", method = RequestMethod.GET)
	public ModelAndView boardDelete(@RequestParam int idx, @RequestParam int curPage) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:boardPagingList.do?curpage=" + curPage);
		
		//1. 삭제 전  삭제  값 가져오기
		BoardVO delteboard = boardService.boardDeleteReadyService(idx);
		//2. 원글이 아닐 경우 삭제 게시글에 답글, 답 답글 들 order 값 수정
		if(delteboard.getGroup_order()!=0) {
			boardService.boardSubGroupOrderService(delteboard);
		}
		//원글이 아닐경우 바로 삭제  
		//게시글 삭제
		boardService.boardDeleteService(idx);
		System.out.println("게시글 삭제");
		return mv;
	}

	// 7. 게시글 수정 뷰
	@RequestMapping(value = "board/boardUpdateView.do", method = RequestMethod.GET)
	public ModelAndView boardUpdateView(@RequestParam int idx, @RequestParam int curPage) throws Exception {

		BoardVO boardVO = boardService.boardDetailViewService(idx);

		ModelAndView mv = new ModelAndView("/jsp/board/boardUpdateView");
		mv.addObject("curPage", curPage);
		mv.addObject("boardVO", boardVO);
		return mv;
	}

	// 8. 게시글 수정 완료
	@RequestMapping(value = "board/boardUpdate.do", method = RequestMethod.POST, produces="text/plain; charset=UTF-8")
	@ResponseBody
	public ModelAndView boardUpdate(BoardVO boardVO, Pagination pagination) throws Exception {
		System.out.println("1수정 - > 제목: "+boardVO.getTitle());
		boardService.boardUpdateService(boardVO);

		int idx = boardVO.getIdx();
		int curPage = pagination.getCurPage();
		ModelAndView mv = new ModelAndView("redirect:boardDetailView.do?idx=" + idx + "&curPage=" + curPage);
		return mv;
	}
	// 9. 게시글 답글 뷰
	@RequestMapping(value="board/boardReplyView.do", method = RequestMethod.GET)
	public ModelAndView boardReplyView(@RequestParam int idx, @RequestParam int curPage) throws Exception  {
		
		BoardVO boardVO = boardService.boardDetailViewService(idx);
		ModelAndView mv = new ModelAndView("/jsp/board/boardReply");	
		
		String title = boardVO.getTitle();
		title = "RE : " +title;
		boardVO.setTitle(title);
		boardVO.setIdx(idx);
		mv.addObject(boardVO);
		mv.addObject("curPage",curPage);
		System.out.println("답글 "+boardVO.getTitle());
		return mv;
	}
	//10. 게시글 답글 
	@RequestMapping(value="board/boardReply.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> boardReply(HttpServletRequest request) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		String create_id = session.getAttribute("userid").toString();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int idx = Integer.parseInt(request.getParameter("idx"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setCreate_id(create_id);
		
		//1. 원 글 order, depth 조회 
		BoardVO originBoard = boardService.boardReplyReadyService(idx);
		int originOrder = originBoard.getGroup_order();
		
		int lastGroupOrder;
		//2. 답글 일 경우
		if(originOrder==0) {
			// group_order 기준으로 가장 높은 order 값을 찾는다
			lastGroupOrder = boardService.boardLastGroupOrderService(idx);
			boardVO.setGroup_order(lastGroupOrder+1);
			boardVO.setGroup_no(idx);
			boardVO.setDepth(1);
			boardService.boardReplyService(boardVO);
			System.out.println("답글을  달았습니다.");
		}
		//3. 답 답글일 경우
		else {
			//원래 게시글 속성 가져오기
			int originDepth = originBoard.getDepth();
			int originNo = originBoard.getGroup_no();	
			//
			BoardVO selectBoard = new BoardVO();
			selectBoard.setDepth(originDepth);
			selectBoard.setGroup_no(originNo);
			selectBoard.setGroup_order(originOrder);
			
			System.out.println("깊이 :"+selectBoard.getDepth());
			System.out.println("그룹 번호 :"+selectBoard.getGroup_no());
			System.out.println("그룹 순서 :"+selectBoard.getGroup_order());
			//3-1 같은 depth 기준으로  다음 (가장 낮은) Order값을 찾는다. 
			
			lastGroupOrder = boardService.boardNextGroupOrderService(selectBoard);					
				
			//3-1-1 기존에 있던 다른 답글이 없을 경우
			if(lastGroupOrder==0) {				
				//3-1-2 답 답글들 조회 후 마지막 order 값을 찾아 +1 해줌
				int resultGroupOrder = boardService.boardLastGroupOrderService(originNo);		
				System.out.println("마지막 순서는 :" +resultGroupOrder+"따라서 순서는 :" +(resultGroupOrder+1)+"로 지정됩니다.");
				//3-1-3  답 답글 작성
				boardVO.setDepth(originDepth+1);
				boardVO.setGroup_no(originNo);
				boardVO.setGroup_order(resultGroupOrder+1);
				boardService.boardReplyService(boardVO);
				System.out.println("답 답글을  달았습니다.");
			}
			//3-2-1기존에 있던 답 답글이 있었을 경우.
			else {
				System.out.println("다음 순서는 :" +lastGroupOrder+"따라서 순서 조정 후 순서는 :" +(lastGroupOrder)+"로 지정됩니다.");
				//3-2-2  기존에 있던 답글들 order 값 +1 시키기
				BoardVO updateBoard = new BoardVO();
				updateBoard.setGroup_order(lastGroupOrder);
				updateBoard.setGroup_no(originNo);
				boardService.boardAddGroupOrderService(updateBoard);
				System.out.println("답글들을 먼저 수정 하였습니다.");
				//3-2-3 답 답글 작성
				boardVO.setDepth(originDepth+1);
				boardVO.setGroup_no(originNo);
				boardVO.setGroup_order(lastGroupOrder);
				boardService.boardReplyService(boardVO);
				System.out.println("답 답글을  달았습니다.");
			}			
		}
		 	
		int code = 0;
		String msg ="성공";
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
}
