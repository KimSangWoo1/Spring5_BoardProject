<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<title>게시글 작성</title>
</head>
<body>
<%@include file="../../include/header.jsp" %> 
   <article>
		<div class="container" role="main">
			<h2>게시글 작성 </h2>
			<form name="form" id="form" method="post" action="${pageContext.request.contextPath}/board/boardInsert.do">
				<div class="mb-3">
					<label for="title">제목 </label>
	  	 			<input type="text" class="form-control" name="title" placeholder="제목을 입력해 주세요" value="${boardVO.title}">
				</div>

				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" name="content" placeholder="내용을 입력해 주세요" ></textarea>
					<input type="hidden" name="idx" value="${boardVO.idx}"/>
					<input type="hidden" name="curPage"  value="${curPage}"/>
				</div>
			</form>
			<div >
				<button type="button" class="btn btn-sm btn-success" id="board_write"  onclick="boardInsert();">저장</button>
				<button type="button" class="btn btn-sm btn-danger" id="board_list" onclick="boardList();">취소</button>
			</div>
		</div>
	</article>
   
   <script type="text/javascript">
	   $(document).ready(function(){

	   });
	     function boardInsert(){
	  		var boardForm = document.form;
			var title = boardForm.title.value;
			var content = boardForm.content.value;
			//NPE Check
			if (!title || !content) {
				alert("제목과 내용을 입력하세요");
			} else {
				 $("#form").submit();
			}
         }
         function boardList(){
          	location.href ="${pageContext.request.contextPath}/board/boardList.do/";
         }
   </script>
</body>
</html>