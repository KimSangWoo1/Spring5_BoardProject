<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>게시글 보기</title>
</head>
<body>
<%@include file="../../include/header.jsp" %> 

<div class="container">	
	<form method="post">
		<div class="card">
			<div class="card-header" ><h3>${boardVO.title}</h3></div>
			<div class="cord-body"  style="height:300px">${boardVO.content}</div>
		</div>	
	</form>
</div>

	  <input type="button" class="btn btn-info" id="go_list" value="목록" />
	  <input type="button" class="btn btn-warning" id="go_reply" value="답글"/>
	  <c:choose>
	  	  <c:when test="${edit}">
		 	 <input type="button" class="btn btn-primary" id="go_update" value="수정"/>
		 	 <input type="button" class="btn btn-danger" id="go_delete" value="삭제"/>
		  </c:when>
		  <c:when test="${!empty fn:trim(search.keyWord)}">
		  	<input type="hidden" id="keyWord" value="${search.keyWord}"/>
		  </c:when>
	  </c:choose>
</body>
<script type="text/javascript">
		$(document).ready(function() {
			$("#go_list").on('click', function() {
				if($("#keyWord").val()){
					location.href = "${pageContext.request.contextPath}/board/boardPagingList.do?curPage=${curPage}&searchType=${search.searchType}&keyWord=${search.keyWord}";
				}else{
					location.href = "${pageContext.request.contextPath}/board/boardPagingList.do?curPage=${curPage}";
				}
			
			})
			$("#go_update").on('click', function() {
				location.href = "${pageContext.request.contextPath}/board/boardUpdateView.do?idx=${boardVO.idx}&curPage=${curPage}";
			})
			$("#go_delete").on('click', function() {
				location.href = "${pageContext.request.contextPath}/board/boardDelete.do?idx=${boardVO.idx}&curPage=${curPage}";
			})
			$("#go_reply").on('click', function() {
				location.href = "${pageContext.request.contextPath}/board/boardReplyView.do?idx=${boardVO.idx}&curPage=${curPage}";
			})
		});
		
</script>
</html>