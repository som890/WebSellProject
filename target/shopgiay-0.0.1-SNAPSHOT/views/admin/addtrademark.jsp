<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>THÊM THƯƠNG HIỆU</title>
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
        <div class="title-add-admin">
            <p>Thêm/cập nhật thương hiệu</p>
        </div>
        <form:form class="form-add" method="post" action="addtrademarkPost" modelAttribute="trademark">
            <div class="row">
                <div class="col-sm-6">
                    <label>Id thương hiệu</label>
                    <form:input type="hidden" class="form-control" path="id" />
                    <label>Tên thương hiệu</label>
                    <form:input type="text" class="form-control" path="name" />
                     <form:errors class="error" path="name"></form:errors>
                    <button class="btn btn-primary form-control btn-add">Thêm/cập nhật thương hiệu</button>
                </div>
            </div>
        </form:form >
	</div>
</body>
</html>