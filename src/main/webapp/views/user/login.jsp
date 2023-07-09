<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>ĐĂNG NHẬP/ĐĂNG KÍ</title>
</head>
<body>
	<%@include file="../../common/user/menuuser.jsp"%>
	<div class="col-md-12" style="margin-top: -30px">
	    <img src="" class="logo-web-login">
	</div>
	<div class="login-form">
	     <div class="row">
	         <div class="col-lg-6">
	             <img src="template/user/image/logologin1.png" class="img-login">
	         </div>
	         <form class="col-lg-6 content-login" action="${pageContext.request.contextPath}/j_spring_security_check"
			method='POST' onsubmit="return signInValidateForm()">
	             <h4>Sign In</h4>
	             <label>Username</label>
	             <input name="username" id="username" type="text" placeholder="username" class="form-control">
	             <label>Password</label>
	             <input name="password" id="password" type="password" placeholder="******" class="form-control">
	             <button class="btn-login">Sign in</button>
	             <input type="checkbox" id="remember"><label class="lb-remember" for="remember"> Remember Me</label>
	             <a href="remember" class="forgot-password">Forgot Password</a><br>
	             <p class="not-member">Not a Member? <a href="regis">Sign Up</a> </p>
	         </form>
	     </div>
	</div>
<c:if test="${not empty remembersuccess}">
	   <script type="text/javascript">
		   swal({
	            title: "Thông báo", 
	            text: "kiểm tra email của bạn để nhận mật khẩu mới", 
	            type: "success"
	          },
	        function(){ 
	        });
	   </script>
</c:if>
</body>
</html>