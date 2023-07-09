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
	<div class="regis-form">
	     <div class="row">
	         <div class="col-lg-5">
	             <img src="template/user/image/logo-regis2.jpeg" class="img-regis">
	         </div>
	         <div class="col-lg-7 content-regis">
	         	<form:form method="post" action="regispost" enctype="multipart/form-data" modelAttribute="account">
         		<div class="row">
         		<div class="col-md-6">
	         		<label>Tên đăng nhập</label>
                	<form:input id="username" type="text" placeholder="username" class="form-control" path="username" />
                	<form:errors class="error" path="username"></form:errors><br>
         		</div>
         		<div class="col-md-6">
	         		<label>Họ tên</label>
                	<form:input id="fullname" type="text" placeholder="full name" class="form-control" path="fullname" />
                	<form:errors class="error" path="fullname"></form:errors><br>
         		</div>
	         	</div>
                <label>Email</label>
                <form:input id="email" type="email" placeholder="email" class="form-control" path="email" />
                <form:errors class="error" path="email"></form:errors><br>
                <label>Số điện thoại</label>
                <form:input id="phone" type="text" placeholder="phone" class="form-control" path="phone" />
                <form:errors class="error" path="phone"></form:errors><br>
                <label>Ảnh đại diện</label>
                <form:input name="file" id="file" type="file" class="form-control" path="file" />
                <form:errors class="error" path="file"></form:errors><br>
                <div class="row">
	         		<div class="col-md-6">
		         		<label>Mật khẩu</label>
                		<form:input id="password" type="password" placeholder="******" class="form-control" path="password" />
                		<form:errors class="error" path="password"></form:errors><br>
	         		</div>
	         		<div class="col-md-6">
		         		<label>Nhập lại mật khẩu</label>
                		<input id="repassword" type="password" placeholder="******" class="form-control">
	         		</div>
	         	</div>
                <label>Địa chỉ</label>
                <form:input id="tenduong" type="text" placeholder="địa chỉ" class="form-control" path="address" />
                <form:errors class="error" path="address"></form:errors><br>
                <button class="btn-regis" onclick="regis()">Đăng ký</button> <span>Hoặc</span> <a href="login" class="chuyendn">Đăng nhập</a>
	         	</form:form>
            </div>
	     </div>
	</div>

</body>
</html>