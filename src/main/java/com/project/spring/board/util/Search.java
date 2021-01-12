package com.project.spring.board.util;

public class Search extends Pagination {
	
	private String searchType;
	private String keyWord;
	
	public Search(String searchType, String keyWord) {
		this.searchType= searchType;
		this.keyWord = keyWord;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
