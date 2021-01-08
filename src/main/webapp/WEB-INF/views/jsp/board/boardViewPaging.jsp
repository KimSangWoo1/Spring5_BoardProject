<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>게시글 페이징</title>
</head>
<body>
<%@include file="../../include/header.jsp" %> 
<div class="container">
	<table class="table table-striped table-hover" align="center">
		<colgroup>
			<col width="10%" />
			<col width="*" />
			<col width="15%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">NO</th>
				<th scope="col">제목</th>
				<th scope="col">조회수</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(boardList) > 0}">
					<c:forEach items="${boardList}" var="row">
						<tr>
							<td><a href="/spring/board/boardDetailView.do?idx=${row.idx}&curPage=${search.curPage}&hit_count=1">${row.idx}</a></td>
							<td>${row.title }</td>
							<td>${row.hit_count }</td>
							<td>${row.create_id }</td>
							<td>${row.create_time }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>

<!-- search{s} -->
	<div  class="box-footer">
		<ul class="search">
			<c:if test="${search.curRange ne 1}">
				<li class="page-item">
					<a class="page-link" href="${path}/spring/board/boardPagingList.do?curPage=${search.startPage-1}">Previous</a>
				</li>
			</c:if>	
			<c:forEach begin="${search.startPage}" end="${search.endPage}" var="curPage">
				<li class="page-item <c:out value="${search.curPage == curPage ? 'active' : ''}"/> ">
					<a class="page-link" href="${path}/spring/board/boardPagingList.do?curPage=${curPage}"> ${curPage} </a>
				</li>
			</c:forEach>
			<c:if test="${search.curRange lt search.rangeCnt}">
				<li class="page-item">
					<a class="page-link" href="${path}/spring/board/boardPagingList.do?curPage=${search.endPage+1}" >Next</a>		
				</li>
			</c:if>
		</ul>
	</div>
		<!--
			 == is JSTL eq 
			!=  is JSTL ne
			<   is JSTL lt
			>   is JSTL gt
			<= is JSTL le
			>= is JSTL ge
		-->
</body>
</html>