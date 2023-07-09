<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BÀI VIẾT</title>
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
		<div class="col-sm-12 header-sp">
            <a href="addblog" class="btn btn-success"><i class="fa fa-plus"></i> Thêm bài viết</a>
        </div>
        <div class="col-sm-12">
           <table id="example" class="table table-striped">
                <thead>
                    <tr>
                        <th>Id bài viết</th>
                        <th>Ảnh bìa</th>
                        <th>Ngày tạo</th>
                        <th>Tiêu đề bài viết</th>
                        <th>Mô tả</th>
                        <th>Xóa</th>
                        <th>Sửa</th>
                    </tr>
                </thead>
                <tbody id="listblog">
                  <c:forEach items="${blogs}" var="list">
                    <tr>
                        <td>#${list.id}</td>
                        <td><img src="${list.imageBanner}" style="width: 120px"></td>
                        <td>${list.createdDate}</td>
                        <td>${list.title}</td>
                        <td>${list.description}</td>
                        <td><a href="deleteblog?id=${list.id}" class="btn btn-danger"><i class="fa fa-trash"></i> Xóa</a></td>
                        <td><a href="addblog?id=${list.id}" class="btn btn-success"><i class="fa fa-trash"></i> sửa</a></td>
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
		            text: "không thể xóa bài viết này", 
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
		            text: "Đã xóa bài viết thành công", 
		            type: "success"
		          },
		        function(){ 
		        });
		   </script>
	</c:if>
</body>
</html>