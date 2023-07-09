<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TÀI KHOẢN</title>
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
		<div class="col-sm-12 header-sp">
            
        </div>
        <div class="col-sm-12">
          <table id="example" class="table table-striped" style="width: 100%;">
                <thead>
                    <tr>
                        <th>id tài khoản</th>
                        <th>Ảnh</th>
                        <th>username</th>
                        <th>Họ tên</th>
                        <th>Ngày tạo</th>
                        <th>Khóa</th>
                    </tr>
                </thead>
                <tbody id="listuser">
                 <c:forEach items="${accounts}" var="list">
                    <tr>
                        <td>#${list.id}</td>
                        <td><img src="${list.avatar}" class="img-sp-admin"></td>
                        <td>${list.username}</td>
                        <td>${list.fullname}</td>
                        <td>${list.created_date}</td>
                        <td>
                         <c:choose>
						    <c:when test="${list.enable==1}">
						        <a href="active?id=${list.id}" class="btn btn-success"><i class="fa fa-lock"></i> Khóa</a>
						    </c:when>    
						    <c:otherwise>
						    	 <a href="active?id=${list.id}" class="btn btn-danger"><i class="fa fa-lock"></i> Mở khóa</a>
						    </c:otherwise>
						</c:choose>
                       </td>
                    </tr>
                   </c:forEach>
                </tbody>
            </table>
        </div>
	</div>
	<c:if test="${not empty khoa}">
		   <script type="text/javascript">
			   swal({
		            title: "Thông báo", 
		            text: "Đã khóa tài khoản thành công", 
		            type: "success"
		          },
		        function(){ 
		        });
		   </script>
	</c:if>
	<c:if test="${not empty mokhoa}">
	   <script type="text/javascript">
			   swal({
		            title: "Thông báo", 
		            text: "Đã mở khóa tài khoản thành công", 
		            type: "success"
		          },
		        function(){ 
		        });
		   </script>
	</c:if>
</body>
</html>