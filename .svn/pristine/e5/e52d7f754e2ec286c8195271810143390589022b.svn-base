<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>스프링 게시판</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list }" var="row">
						<tr>
							<td><a name="idx"
								href="/spring/board/boardDetailView.do?idx=${row.idx}&hit_count=1">${row.idx}</a></td>
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

	<script type="text/javascript">
		$(document).ready(function() {
			$("#go_boardList").on('click', function() {
				GoBoardList();
			})
			$("#go_boardWrite").on('click', function() {
				GoBoardWrite();
			})
		});

		function GoBoardList() {
			location.reload();
		}

		function GoBoardWrite() {
			window.location.href = "/spring/board/boardWrite.do";
		}
	</script>
</body>
</html>