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

<title>로그인</title>
</head>

<body>

   <div class="container p-3 bg-dark text-white ">
      <h1 class="text-center">로그인 페이지</h1>
   </div>

	<div class="container">
	  <form id="form" name="loginForm">
	    <div class="form-group">
	      <label for="text">아이디</label>
	      <input type="text" class="form-control" id="id" name="id" placeholder="Enter id">
	    </div>
	    <div class="form-group">
	      <label for="pw">비밀번호</label>
	      <input type="password" class="form-control" id="pw" name="pw" placeholder="Enter password">
	    </div>	
	  </form>
	  <input type="button" class="btn btn-success" id="login_submit" value="로그인" form="form"/>
	  <input type="button" class="btn btn-info" id="go_signup" value="회원가입"/>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#login_submit").on('click', function() {
				login_Click();
			})
			$("#go_signup").on('click', function() {
				goSignUp();
			})
		});

		function login_Click() {
			var loginForm = document.loginForm;
			var userid = loginForm.id.value;
			var userpw = loginForm.pw.value;
			if (!userid || !userpw) {
				alert("아이디와 비밀번호를 입력하세요");
			} else {
				loginCheck();
			}
		}
		function goSignUp() {
			location.href = "${pageContext.request.contextPath}/signup.do/";
		}

		function loginCheck() {
			$.ajax({
				url : "/spring/loginChk.do",
				type : "POST",
				data : {
					id : $("#id").val(),
					pw : $("#pw").val()
				},
				success : function(data) {
					alert(data.Msg);
					if(data.Code==0){
						window.location.href = "/spring/board/boardList.do";
					}else{
						//location.reload();
					}
				
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}
			});
		}
	</script>
</body>
</html>