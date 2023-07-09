<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>THÊM SẢN PHẨM</title>
<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
  <script>
tinymce.init({
	selector : 'textarea#editor',
});
</script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
  
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
        <div class="title-add-admin">
           	<p>Thêm/cập nhật sản phẩm</p>
        </div>
        <form:form name = "myForm" class="form-add" action="addproductPost" method="post" enctype="multipart/form-data" 
        modelAttribute="watch" onsubmit = "validate()">
            <div class="row">
                <div class="col-sm-6">
               		<form:input type="hidden" class="form-control" path="id"/>
                    <label>Tên sản phẩm</label>
                    <form:input id="tieude" type="text" class="form-control" path="name" />
                    <form:errors class="error" path="name"></form:errors><br>
                    <label>Giá</label>
                    <form:input id="mota" type="text" class="form-control" path="price" />
                    <form:errors class="error" path="price"></form:errors><br>
                    <label>Số lượng</label>
                    <form:input id="mota" type="text" name="quantity" class="form-control" path="quantity" />
                    <form:errors class="error" path="quantity"></form:errors><br>
                    <label>Size</label>
                    <select class="form-control" multiple="multiple" name="listcolor" required="required">
                    <c:forEach items="${colors}" var="list">
                    	<option ${list.selected} value="${list.tenmau}">${list.tenmau}</option>
                    </c:forEach>
                    </select>
                    <label>Danh mục</label>
                    <select class="form-control" name="danhmuc">
                    <c:forEach items="${categories}" var="list">
                    	<c:choose>
						    <c:when test="${list.id==watchp.category.id}">
						        <option selected="selected" value="${list.id}">${list.name}</option>
						    </c:when>    
						    <c:otherwise>
						    	 <option value="${list.id}">${list.name}</option>
						    </c:otherwise>
						</c:choose>
                    </c:forEach>
                    </select>
                    <label>Thương hiệu</label>
                    <select class="form-control" name="thuonghieu">
                    <c:forEach items="${trademarks}" var="list">
                      	<c:choose>
						    <c:when test="${list.id==watchp.tradeMark.id}">
						        <option selected="selected" value="${list.id}">${list.name}</option>
						    </c:when>    
						    <c:otherwise>
						    	 <option value="${list.id}">${list.name}</option>
						    </c:otherwise>
						</c:choose>
                    </c:forEach>
                    </select>
                    <label>Ảnh</label>
                    <form:input id="imagebanner" type="file" class="form-control" path="anhnen" />
                    <form:errors class="error" path="anhnen" cssStyle="color: red"  /><br>
                   
                    <img class="imgblog" id="imgblog"> 
                    <button onclick="saveProduct()" class="btn btn-primary form-control btn-add">Thêm/ cập nhật sản phẩm</button>
                </div>
                <div class="col-md-6">
                  	<label>Ảnh chi tiết</label>
                    <form:input id="imagebanner" name="filephu" type="file" multiple="multiple" class="form-control" path="anhphu" />
                    <form:errors class="error" path="anhphu"></form:errors><br>
                     <div class="row">
                     <c:forEach items="${watch.imageWatch}" var="list">
                    	<div class="col-md-4">
                    		<img alt="" src="${list.linkImage}" style="width: 100%; height: 150px">
                    		<a href="deleteimg?id=${watchp.id}&idimg=${list.id}" class="btn btn-danger form-control">Xóa</a>
                    	</div>
                   	</c:forEach>
                    </div>
                    <label>Mô tả</label>
                    <form:errors class="error" path="description"></form:errors><br>
                    <form:textarea id="editor"  name="content" path="description"></form:textarea>
                </div>
            </div>
        </form:form>
	</div>
</body>
</html>