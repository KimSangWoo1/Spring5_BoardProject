<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>Insert title here</title>
</head>
<body>
<%@include file="../include/header.jsp" %>
	<section class="content container-fluid">
		<h3>${exception.getMessage()}</h3>
		<ul>
			<c:forEach items ="${exception.getStackTrace()}" var="stack">
				<li>${stack.toString() }</li>
			</c:forEach>
		</ul>
	</section>
</body>
</html>