<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia">
<link href=https://fonts.googleapis.com/css?family=Quicksand rel=stylesheet>
<link rel="stylesheet" type="text/css" href="template/user/style.css">
</head>
<body>
<div id="menu">
	<div class="menu-top">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
					<p id="title-menu-top">Giảm giá toàn bộ cửa hàng trong 30 ngày</p>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
					<ul class="list-menu-top">
						<li><a href="/logout">Đăng xuất</a></li>
						<li><a href="#">FAQS</a></li>
						<li><a href="cart">Giỏ hàng</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="menu-bottom">
		<div class="container">
			<nav class="navbar navbar-expand-lg">
				  <a href="home"><img class="logo-menu" src="template/user/image/logo.png"></a>
				  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
				  <div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					  <li class="nav-item"><a class="nav-link" href="home">Trang chủ</a></li>
					  <li class="nav-item"><a class="nav-link" href="product">Sản phẩm</a></li>
					  <li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						  Danh mục
						</a>
						<ul id="listcategoryheader" class="dropdown-menu" aria-labelledby="navbarDropdown">
						  <li><a class="dropdown-item" href="#">Action</a></li>
						  <li><a class="dropdown-item" href="#">Another action</a></li>
						  <li><a class="dropdown-item" href="#">Something else here</a></li>
						</ul>
					  </li>
					  <li class="nav-item"><a class="nav-link" href="blog">Bài viết</a></li>
					 
					   <c:choose>
						    <c:when test="${pageContext.request.userPrincipal.name==null}">
						         <li class="nav-item" id="login-menu"><a href="login" class="nav-link authens">Đăng nhập</a></li>
						    </c:when>    
						    <c:otherwise>
						    	<li class="nav-item dropdown">
									<a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
									  Tài khoản
									</a>
									<ul id="listcategoryheader" class="dropdown-menu" aria-labelledby="navbarDropdown">
									  <li><a href="logout" class="dropdown-item authens">Đăng xuát</a></li>
									  <li><a href="history" class="dropdown-item" href="">Lịch sử đặt hàng</a></li>
									</ul>
								 </li>
						    </c:otherwise>
						</c:choose>
					</ul>
					<div class="d-flex">
						<a><i  data-bs-toggle="modal" data-bs-target="#exampleModal" class="fa fa-search"></i></a>
						<a href="cart"><i class="fa fa-shopping-cart"></i></a>
						<a href="account"><i class="fa fa-user"></i></a>
					</div>
				</div>
			</nav>
		</div>
	</div>
</div>

</body>
</html>