<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sản phẩm</title>
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
		<div class="col-sm-12 header-sp">
            <a href="addproduct" class="btn btn-success"><i class="fa fa-plus"></i> Thêm sản phẩm</a>
            
        </div>
        <div class="col-sm-12">
            <table id="example" class="table table-striped" style="width:100%; margin-top: 50px;">
                <thead>
                    <tr>
                        <th>id sản phẩm</th>
                        <th>Ảnh bìa</th>
                        <th>Tên sản phẩm</th>
                        <th>Số lượng còn</th>
                        <th>Danh mục</th>
                        <th>Giá</th>
                        <th>Cập nhật</th>
                        <th>Xóa</th>
                    </tr>
                </thead>
                <tbody id="listproduct">
                 <c:forEach items="${listwatch}" var="list">
                    <tr>
                        <td>#${list.id}</td>
                        <td><img src="${list.banner}" class="img-sp-admin"></td>
                        <td>${list.name}</td>
                        <td>${list.quantity}</td>
                        <td>${list.category.name}</td>
                        <td><fmt:formatNumber value="${list.price}" maxFractionDigits="3"/></td>
                        <td><a href="addproduct?id=${list.id}" class="btn btn-primary"><i class="fa fa-edit"></i> Cập nhật</a></td>
                        <td><a href="deleteproduct?id=${list.id}" class="btn btn-danger"><i class="fa fa-trash"></i> Xóa</a></td>
                    </tr>
                 </c:forEach>
                </tbody>
            </table>
        </div>
	</div>
	<c:if test="${not empty error}">
		   <script type="text/javascript">
			   swal({
		            title: "Thông báo", 
		            text: "không thể xóa sản phẩm này", 
		            type: "error"
		          },
		        function(){ 
		        });
		   </script>
		</c:if>
	<c:if test="${not empty success}">
	   <script type="text/javascript">
			   swal({
		            title: "Thông báo", 
		            text: "Đã xóa sản phẩm thành công", 
		            type: "success"
		          },
		        function(){ 
		        });
		   </script>
	</c:if>
</body>
</html>