<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>THÊM DANH MỤC</title>
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
        <div class="title-add-admin">
            <p>Thêm/cập nhật danh mục</p>
        </div>
        <form:form class="form-add" method="post" action="addcategoryPost" modelAttribute="category">
            <div class="col-sm-5">
                <form:input type="hidden" class="form-control" path="id"/>
                <label>Tên danh mục</label>
                <form:input type="text" class="form-control" path="name" />
                <form:errors class="error" path="name"></form:errors>
                <button class="btn btn-success form-control action-btn">Thêm/Cập nhật danh mục</button>
            </div>
        </form:form>
	</div>
</body>
</html>