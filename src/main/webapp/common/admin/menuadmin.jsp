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
    <link rel="stylesheet" type="text/css" href="../template/admin/styleadmin.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.2/js/dataTables.bootstrap5.min.js"></script>
    <link href="https://cdn.datatables.net/1.13.2/css/dataTables.bootstrap5.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet" />
   <script src="https://kit.fontawesome.com/9d1d9a82d2.js" crossorigin="anonymous"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
   <script type="text/javascript"> $('#example').DataTable();</script>
   <script src="../template/admin/script.js"></script>
</head>
<body  id="body-pd">
	<header class="header" id="header">
		<div class="header_toggle">
			<i class='bx bx-menu' id="header-toggle"></i>
		</div>
		<div class="header_img">
			<img id="img-login" src="https://i.imgur.com/hczKIze.jpg" alt="">
		</div>
	</header>
	<div class="l-navbar" id="nav-bar">
		<nav class="nav nav-dark ">
			<div>
				<a href="#" class="nav_logo"> <i
					class="bx bx-layer nav_logo-icon"></i> <span class="nav_logo-name">Quản
						trị</span>
				</a>
				<div class="nav_list">
					<a href="home" class="nav_link"> <i
						class="fa fa-home"></i> <span class="nav_name">Trang chủ</span>
					</a> <a href="user" class="nav_link"> <i
						class="bx bx-user nav_icon"></i> <span class="nav_name">Người
							dùng</span>
					</a> <a href="product" class="nav_link"> <i
						class="fas fa-tshirt"></i> <span class="nav_name">Sản phẩm</span>
					</a> <a href="trademark" class="nav_link"> <i
						class="bx bx-bar-chart-alt-2 nav_icon"></i> <span class="nav_name">Thương hiệu</span>
					</a> <a href="invoice" class="nav_link"> <i
						class="fa fa-shopping-cart"></i> <span class="nav_name">Đơn
							hàng</span>
					</a> <a href="category" class="nav_link"> <i
						class="fa fa-list-alt"></i> <span class="nav_name">Danh mục
							sản phẩm</span>
					</a> <a href="blog" class="nav_link"> <i
						class="fa fa-list-alt"></i> <span class="nav_name">Bài viết</span>
					</a>
				</div>
			</div>
			<a href="../logout" class="nav_link"> <i class="bx bx-log-out nav_icon"></i>
				<span class="nav_name">Sign Out</span>
			</a>
		</nav>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function () {
    $('#example').DataTable();
    
});
</script>
</html>