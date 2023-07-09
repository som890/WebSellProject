<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>THÊM BÀI VIẾT</title>
<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
  <script>
tinymce.init({
	selector : 'textarea#editor',
});
</script>
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
        <div class="title-add-admin">
            <p>Thêm/cập nhật bài viết</p>
        </div>
        <form:form class="form-add" method="post" action="addblogPost" enctype="multipart/form-data" modelAttribute="blog">
            <div class="row">
                <div class="col-sm-6">
                	<form:input type="hidden" class="form-control" path="id"/>
                    <label>Tiêu đề bài viết</label>
                    <form:input id="tieude" type="text" class="form-control" path="title" />
                    <form:errors class="error" path="title"></form:errors><br>
                    <label>Mô tả</label>
                    <form:input id="mota" type="text" class="form-control" path="description" />
                    <form:errors class="error" path="description"></form:errors><br>
                    <label>Ảnh bìa</label>
                    <input id="imagebanner" name="file" type="file" class="form-control">
                    <img class="imgblog" id="imgblog" src="${blog.imageBanner}"> 
                    <button onclick="saveProduct()" class="btn btn-primary form-control btn-add">Thêm/cập nhật bài viết</button>
                </div>
                <div class="col-md-6">
                    <label>Nội dung</label>
                     <form:errors class="error" path="content"></form:errors>
                    <form:textarea id="editor" name="content" path="content"></form:textarea>
                </div>
            </div>
        </form:form >
	</div>
</body>
</html>