<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>THƯƠNG HIỆU</title>
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
		<div class="col-sm-12 header-sp">
            <a href="addtrademark" class="btn btn-success"><i class="fa fa-plus"></i> Thêm thương hiệu</a>
        </div>
        <div class="col-sm-12">
            <table id="example" class="table table-striped" style="width:100%; margin-top: 50px;">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Tên thương hiệu</th>
                        <th>Cập nhật</th>
                        <th>Xóa</th>
                    </tr>
                </thead>
                <tbody id="listproduct">
                <c:forEach items="${trademarks}" var="list">
                    <tr>
                        <td>#${list.id}</td>
                        <td>${list.name}</td>
                        <td><a href="addtrademark?id=${list.id}" class="btn btn-primary"><i class="fa fa-edit"></i> Cập nhật</a></td>
                        <td><a href="deletetrademark?id=${list.id}" class="btn btn-danger"><i class="fa fa-trash"></i> Xóa</a></td>
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
		            text: "không thể xóa thương hiệu này, đã có sản phẩm thuộc thương hiệu này", 
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
		            text: "Đã xóa thương hiệu thành công", 
		            type: "success"
		          },
		        function(){ 
		        });
		   </script>
	</c:if>
</body>
</html>