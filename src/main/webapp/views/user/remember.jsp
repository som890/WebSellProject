<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUÊN MẬT KHẨU</title>
</head>
<body>
<%@include file="../../common/user/menuuser.jsp"%>
<div class="col-md-12">
     <img src="" class="logo-web-login">
 </div>
<form class="login-form" method="get" action="rememberpost">
     <div class="row">
         <div class="col-lg-6">
             <img src="template/user/image/logologin1.png" class="img-login">
         </div>
         <div class="col-lg-6 content-login">
             <h4>Quên Mật Khẩu</h4>
             <label>Email</label>
             <input name="email" type="email" placeholder="email@gmail.com" class="form-control">
             <button class="btn-login">Xác thực</button>
             <p class="not-member"><a href="">Đăng nhập</a> </p>
         </div>
     </div>
</form>
<%@include file="../../common/user/footer.jsp"%>
<c:if test="${not empty mailnull}">
	   <script type="text/javascript">
		   swal({
	            title: "Thông báo", 
	            text: "email không tồn tại trong hệ thống", 
	            type: "error"
	          },
	        function(){ 
	        });
	   </script>
</c:if>
<c:if test="${not empty khoa}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Tài khoản đã bị khóa", 
            type: "error"
          },
        function(){ 
        });
   </script>
</c:if>
</body>

</html>