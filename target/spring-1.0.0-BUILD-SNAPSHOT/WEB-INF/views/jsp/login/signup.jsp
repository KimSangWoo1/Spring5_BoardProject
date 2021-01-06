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
<meta charset="EUC-KR">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<title>회원가입</title>
</head>
<body>

<div class="container p-3 bg-primary  text-white ">
      <h1 class="text-center">회원가입 페이지</h1>
   </div>

	<div class="container">
	  <form id="form" name="signupform" action="${pageContext.request.contextPath}/signUpChk.do/" method="post">
	    <div class="form-group">
	      <label for="text">아이디</label>
	      <input type="text" class="form-control" id="id" name="id" placeholder="New id">
	    </div>
	    <div class="form-group">
	      <label for="pw">비밀번호</label>
	      <input type="password" class="form-control" id="pw"  name="pw" placeholder="New password">
	    </div>	
	  </form>
	  <input type="button" class="btn btn-success" id="signup_submit" value="회원가입" form="form"/>
	  <input type="button" class="btn btn-danger" id="go_login" value="취소"/>
	</div>

   <script type="text/javascript">
	   $(document).ready(function(){
		   $("#signup_submit").on('click', function() {
			   DuplicatedUserCheck();
			})
			   $("#go_login").on('click', function() {
				   goLogin();
			})
	   });
	     function signup_click(){
	    	 var signupform = document.signupform;
	    	 var userid = signupform.id.value;
	    	 var userpw = signupform.pw.value;
	    	 if(!userid || !userpw){
	    		 alert("아이디와 비밀번호를 입력하세요");
	    	 }else{
	    		 //loginForm.submit();
	    		 DuplicatedUserCheck();
	    	 }    
         }
         function goLogin(){
          	location.href ="${pageContext.request.contextPath}/";
         }
         
         function DuplicatedUserCheck(){
          	 $.ajax({
          		 url:"/myapp/signUpChk.do",
          		 type:"POST",
          		 data:{
          			 id:$("#id").val(),
          			 pw:$("#pw").val()       			 
          		},
          		success: function(data){
          			alert(data.Msg);
          			if(data.Code==0){
          				goLogin();	
          			}else{
          				//location.reload();
          			}       			
          		},
          		error: function(request,status,error){
          		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

          		}        		 
          	 });
           } 
   </script>
</body>
</html>
