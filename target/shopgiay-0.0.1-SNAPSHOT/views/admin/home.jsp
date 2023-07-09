<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRANG CHỦ</title>
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
        <div class="row">
            <div class="col-sm-12 col-xs-12 col-lg-3 col-md-3">
                <div class="content-box-index tongdonhangs-admin">
                    <ul>
                        <li><h4>${totalInvoice}</h4><p>Tổng đơn hàng</p></li>
                        <li><img src="../image/icon-tongdonhang.png"></li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-12 col-xs-12 col-lg-3 col-md-3">
                <div class="content-box-index sanpham-admin">
                    <ul>
                        <li><h4>${totalProduct}</h4><p>Tổng sản phẩm</p></li>
                        <li><img src="../image/icon-product.png"></li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-12 col-xs-12 col-lg-3 col-md-3">
                <div class="content-box-index tonguser-admin">
                    <ul>
                        <li><h4>${totaluser}</h4><p>Tổng nguời dùng</p></li>
                        <li><img src="../image/icon-tonguser.png"></li>
                    </ul>
                </div>
            </div>
        </div>
        <hr>
       <%--  <div class="row">
            <div class="col-sm-12 col-md-12">
                <h5>Người dùng mới</h5>
                <table class="table">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>username</th>
                            <th>họ tên</th>
                            <th>email</th>
                            <th>ngày tạo</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="list">
                        <tr>
                            <td>#${list.id}</td>
                            <td>${list.username}</td>
                            <td>${list.fullname}</td>
                            <td>${list.email}</td>
                            <td>${list.created_date}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div> --%>
        </div>
    </div>
</body>
</html>