<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
<%@include file="../../include/header.jsp" %> 
<div class="container-fluid pt-3">
	<div class="form-group">
		<form id="form" action="boardUpdate.do" method="post">
			<div class= "row">
				<div class="col-md-6">
					<label for="title">제목</label>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<input type="text" class="form-control form-control-lg" name="title" value="${boardVO.title}">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label for="content">내용</label>		
				</div>
			</div>	
			<div class="row">
				<div class="col-md-6">
					<textarea class="form-control" rows="5" name="content" >${boardVO.content}</textarea>
					<input type="hidden" name="idx" value="${boardVO.idx}"/>
				</div>	
			</div>
		</form> 	
		 <input type="submit" class="btn btn-primary" id="do_update" value="수정" form="form"/>
	 	 <input type="button" class="btn btn-danger" id="go_list" value="취소"/>
	 	 <input type="hidden" name="curPage" value="${curPage}"/>
	</div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$("#go_list").on('click', function() {
		location.href = "${pageContext.request.contextPath}/board/boardPagingList.do?curPage=${curPage}";
	})

	$("#do_update").on('click', function() {
		$("#form").submit();
	})
});
</script>
</html>
