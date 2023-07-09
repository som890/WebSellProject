<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tegoryCa</title>
</head>
<body>
<%@include file="../../common/user/menuuser.jsp"%>
<div class="content">
    <div class="container-fuild cart-banner">
        <div class="container">
            <h4>Shopping Cart</h4>
        </div>
    </div>
    <div class="container">
       <div class="row table-cart">
            <div class="col-md-8">
                <table class="table">
                    <thead id="title-table-cart">
                        <tr>
                            <th>Ảnh</th>
                            <th>Sản phẩm</th>
                            <th style="width: 25%">Số lượng</th>
                            <th>Đơn giá</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="list-cart">
                     <c:forEach items="${carts}" var="list">
                        <tr>
                            <td><img class="img-cart" src="${list.colorWatch.watch.banner}"></td>
                            <td>${list.colorWatch.watch.name}</td>
                            <td class="range-price">
                            <form action="updatequantity" method="post" style="display: inline;">
                            	<input name="mausac" value="${list.colorWatch.id}" style="display: none">
                            	<input name="quantity" value="-1" style="display: none">
                            	<button>-</button>
                            </form>
                            <input type="number" value="${list.quantity}" style="display: inline;">
                            <form action="updatequantity" method="post" style="display: inline;">
                            	<input name="mausac" value="${list.colorWatch.id}" style="display: none">
                            	<input name="quantity" value="1" style="display: none">
                            	<button>+</button>
                            </form>
                            </td>
                            <td><fmt:formatNumber value="${list.colorWatch.watch.price}" maxFractionDigits="3"/>đ</td>
                            <td><a href="deletecart?id=${list.colorWatch.id}"><i class="fa fa-times"></i></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button class="btn-tieptucmuahang" onclick="location.href=indexUser" type="button"></button>
            </div>
            <div class="col-md-4 chitietdathang">
                <h5>Tổng tiền đơn hàng</h5>
                <table class="table">
                    <tr>
                        <td>Tổng tiền: </td>
                        <td id="tongtien"><fmt:formatNumber value="${tongtien}" maxFractionDigits="3"/>đ</td>
                    </tr>
                    <tr>
                        <td>Phí ship: </td>
                        <td>30.000đ</td>
                    </tr>
                    <tr>
                        <td>Số lượng sản phẩm: </td>
                        <td id="tongsl">${soluongsp} sản phẩm</td>
                    </tr>
                    <tr>
                        <td onclick="location.href='checkout'" colspan="2"><button onclick="location.href=''" type="button" class="btn-thanhtoandonhang">Đặt hàng</button></td>
                    </tr>
                </table>
            </div>
       </div>
    </div>
</div>
<%@include file="../../common/user/footer.jsp"%>
</body>
</html>