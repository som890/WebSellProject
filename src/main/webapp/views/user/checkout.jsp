<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đơn hàng</title>
</head>
<body>
<%@include file="../../common/user/menuuser.jsp"%>
<div class="content">
    <div class="container-fuild cart-banner">
        <div class="container">
            <h4>Checkout</h4>
        </div>
    </div>
    <div class="container">
       <div class="row table-cart">
            <div class="col-md-8">
                <div class="title-checkout">
                    <p><i class="fa fa-info-circle" aria-hidden="true"></i> Thông tin nhận hàng của bạn</p>
                </div>
                <hr>
                <table class="table table-checkout">
                    <tr>
                        <td class="bold-checkout">Họ tên: </td>
                        <td id="hotencheck">${account.fullname}</td>
                    </tr>
                    <tr>
                        <td class="bold-checkout">Số điện thoại: </td>
                        <td id="sdtcheck">${account.phone}</td>
                    </tr>
                    <tr>
                        <td class="bold-checkout">Tổng tiền đơn hàng: </td>
                        <td id="tongtien" class="money-checkout"><fmt:formatNumber value="${tongtien}" maxFractionDigits="3"/>đ</td>
                    </tr>
                    <tr>
                        <td class="bold-checkout">Phí ship: </td>
                        <td class="money-checkout">30.000đ</td>
                    </tr>
                    <tr>
                        <td class="bold-checkout">Số lượng sản phẩm: </td>
                        <td id="tongsl">${tongsl} sản phẩm</td>
                    </tr>
                    <tr>
                        <td onclick="window.location.href='account'" colspan="2"><button class="btn-thaydoithongtin">Thay đổi thông tin người nhận</button></td>
                    </tr>
                </table>
            </div>
            <div class="col-md-4 chitietcheckout">
                <h5 class="title-chitietdathang tongtiendonhang">Ghi chú đơn hàng</h5>
                <input id="ghichutong" onkeyup="ghichu()" type="text" placeholder="ghi chú đơn hàng" class="form-control">
                <h5 class="title-chitietdathang">Địa chỉ nhận hàng</h5>
                <div id="listdcnhanhang">
                    <label class="radio-custom">Địa chỉ: ${account.address}
                        <input type="radio" name="radio" checked="checked">
                        <span class="checkmark"></span>
                    </label>
                </div>
                
               	<form action="payment" method="post">
               		<input id="ghichukhinhan" name="ghichukhinhan" style="display: none;">
               	 	<button class="add-dia-chi">Thanh toán khi nhận hàng</button>
               	</form>
            </div>
       </div>
    </div>
</div>
<%@include file="../../common/user/footer.jsp"%>
<script type="text/javascript">
	function ghichu() {
		document.getElementById("ghichukhinhan").value = document.getElementById("ghichutong").value
		document.getElementById("ghichumomo").value = document.getElementById("ghichutong").value
	}
</script>
<c:if test="${not empty cartromg}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Bạn chưa có sản phẩm nào trong giỏ hàng, không thể thanh toán", 
            type: "warning"
          },
        function(){ 
        	  window.location.replace('cart')
        });
   </script>
</c:if>
</body>
</html>