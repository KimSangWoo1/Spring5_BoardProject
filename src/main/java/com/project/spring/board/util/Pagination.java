package com.project.spring.board.util;

public class Pagination {
	// 페이지 안에 게시글 갯수
    private int pageSize = 10;
	// 페이지 갯수 범위 설정 값
    private int rangeSize = 5;  
    // 현재 페이지
    private int curPage = 1;   
    // 현재 범위(range) 
    private int curRange = 1; 
    // 게시글 총 갯수
    private int listCnt; 
    // 페이지 총 갯수
    private int pageCnt;
    // 범위 총 갯수
    private int rangeCnt; 
    // 첫 페이지
    private int startPage = 1;
    // 마지막 페이지
    private int endPage = 1;
    /// 시작 index 
    private int startIndex = 0;

	/// 전 범위
    private int prevPage;
    // 다음 범위
    private int nextPage;
    
    public Pagination () {}
	public Pagination (int listCnt,  int curPage){
    
    	//현재 페이지 설정
    	this.curPage=curPage;
    	//게시글 총 갯수 설정
    	this.listCnt=listCnt;
    	
    	//1. ��ü ������ ��   ex) ��ü �Խñ� ��/ �������� ������ �Խñ� �� = ��ü ������ ��  
    	this.pageCnt = (int)Math.ceil(listCnt*1.0/pageSize);
    	//2. ��ü �� ��  ex)  ��ü ������ ��/ ������ ������ ����  = ��ü ��� �� 
    	this.rangeCnt  = (int)Math.ceil(pageCnt*1.0/rangeSize);
    //3.��� ���� (range Setting)    		
    	//���� ���  ex)  ((4-1)/5) +1 = 1  
    	this.curRange = (int)((curPage-1)/rangeSize) + 1;
    	//����  ������ = (���� ���-1) * ��� ���� + 1
    	this.startPage = (curRange-1)*rangeSize+1;
    	//�� ������ = ���������� + ��� ���� -1
    	this.endPage = startPage + rangeSize-1;
    	
    	//�� ������ �ȳѵ��� ����
    	if(endPage>pageCnt) {
    		this.endPage =pageCnt;
    	}
    	
    	this.prevPage = curPage -1;
    	this.nextPage = curPage +1;
    	
    	//startIndex����
    	this.startIndex = (curPage-1) * pageSize;

    }
	public void pageInfo(int listCnt,  int curPage) {
		new Pagination(listCnt, curPage);
	}
	
	
	   public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getRangeSize() {
			return rangeSize;
		}

		public void setRangeSize(int rangeSize) {
			this.rangeSize = rangeSize;
		}

		public int getCurPage() {
			return curPage;
		}

		public void setCurPage(int curPage) {
			this.curPage = curPage;
		}

		public int getCurRange() {
			return curRange;
		}

		public void setCurRange(int curRange) {
			this.curRange = curRange;
		}

		public int getListCnt() {
			return listCnt;
		}

		public void setListCnt(int listCnt) {
			this.listCnt = listCnt;
		}

		public int getPageCnt() {
			return pageCnt;
		}

		public void setPageCnt(int pageCnt) {
			this.pageCnt = pageCnt;
		}

		public int getRangeCnt() {
			return rangeCnt;
		}

		public void setRangeCnt(int rangeCnt) {
			this.rangeCnt = rangeCnt;
		}

		public int getStartPage() {
			return startPage;
		}

		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}

		public int getEndPage() {
			return endPage;
		}

		public void setEndPage(int endPage) {
			this.endPage = endPage;
		}

		public int getPrevPage() {
			return prevPage;
		}

		public void setPrevPage(int prevPage) {
			this.prevPage = prevPage;
		}

		public int getNextPage() {
			return nextPage;
		}

		public void setNextPage(int nextPage) {
			this.nextPage = nextPage;
		}
	    public int getStartIndex() {
			return startIndex;
		}
		public void setStartIndex(int startIndex) {
			this.startIndex = startIndex;
		}
}
