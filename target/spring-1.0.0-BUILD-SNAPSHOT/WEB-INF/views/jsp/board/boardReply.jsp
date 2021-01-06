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
			<form name="form" id="form" method="post" action="${pageContext.request.contextPath}/board/boardReply.do">
				<div class="mb-3">
					<label for="title">제목 </label>
	  	 			<input type="text" class="form-control" id="title" name="title" value="${boardVO.title}">
				</div>

				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" id="content" name="content" placeholder="내용을 입력해 주세요" ></textarea>
					<input type="hidden" id="idx" name="idx" value="${boardVO.idx}"/>
					<input type="hidden" id="curPage" name="curPage"  value="${curPage}"/>
				</div>
			</form>
			<div >
				<button type="button" class="btn btn-sm btn-success" id="board_reply"  onclick="boardReply();">저장</button>
				<button type="button" class="btn btn-sm btn-danger" id="board_list" onclick="boardList();">취소</button>
			</div>
		</div>
	</article>
   
   <script type="text/javascript">
	   $(document).ready(function(){
	

	   });

	   	 function boardReply(){

	   		 $.ajax({
	    		 url:"/myapp/board/boardReply.do",
	    		 type:"POST",
	    		 data:{
	    			 title : $("#title").val(),
	    			 content : $("#content").val(),
	    			 idx : $("#idx").val(),
	    			 curPage : $("#curPage").val()
	    		 },
	    		 success : function(data){
	    			 alert("답글 작성 완료");
	    			 if(data.code==0){
						 window.location.href = "/myapp/board/boardList.do";
					 }else{
						//location.reload();
				 	}
	    		 }, 
	    		 error : function(request, status, error){
	    			 alert("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:" + error);
	    		 }
			 });
	   	 }
         function boardList(){
          	location.href ="${pageContext.request.contextPath}/board/boardList.do/";
         }
   </script>
</body>
</html>