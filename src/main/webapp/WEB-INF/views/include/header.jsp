<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Spring_Board</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarsExample">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/myapp/board/boardList.do/">게시글 목록 </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href=/myapp/board/boardPagingList.do/>뷰페이징 목록</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="/myapp/board/boardWrite.do/">게시글 작성</a>
      </li>	 
  	  <li class="nav-item">
        <a class="nav-link" href="/myapp/logout.do">로그아웃</a>
      </li>

    </ul>

	<div class="form-group row justify-content-center">
		<div class="w100" style="padding-right:10px">
			<select class="form-control form-control-sm" name="searchType" id="searchType">
				<option value="title">제목</option>
				<option value="reg_id">작성자</option>
			</select>
		</div>
		<form class="form-inline my-2 my-md-0">
	      <input class="form-control" type="text" placeholder="Search">
	    </form>
	</div>
  </div>
</nav>
</body>
</html>