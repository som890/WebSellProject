<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử đặt hàng</title>
</head>
<body>
<%@include file="../../common/user/menuuser.jsp"%>
<div class="content">
    <div class="container-fuild cart-banner">
        <div class="container">
            <h4>Lịch Sử Đặt Hàng</h4>
        </div>
    </div>
    <div class="container">
        <div class="list-invoice">
            <div id="list-invoice-content">
                <table id="example" class="table table-striped" style="width:100%; margin-top: 50px;">
                    <thead>
                        <tr>
                            <th>ID đơn hàng</th>
                            <th>Tổng tiền hàng</th>
                            <th>Ngày đặt</th>
                            <th>Thanh toán</th>
                            <th>Trạng thái đơn hàng</th>
                            <th>Chi tiết đơn hàng</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="list">
                     <c:forEach items="${listinvoice}" var="list">
                        <tr>
                            <td>#${list.id}</td>
                            <td><fmt:formatNumber value="${list.totalAmount}" maxFractionDigits="3"/>đ</td>
                            <td>${list.createdDate}</td>
                            <c:choose>
							    <c:when test="${list.type==1}">
							          <td>đã thanh toán qua momo</td>
							    </c:when>    
							    <c:otherwise>
							    	<td>thanh toán khi nhận hàng</td>
							    </c:otherwise>
							</c:choose>
                           
                            <td><p class="dangcho">${list.statusInvoice.name}</p></td>
                            <td><button data-bs-toggle="modal" data-bs-target="#chitietdonhang${list.id}" class="xemchitiet-dh">Xem chi tiết</button></td>
                            <c:choose>
							    <c:when test="${list.type==0}">
							        <td><a href="deleteinvoice?id=${list.id}" class="btn btn-danger">Hủy đơn</a></td>
							    </c:when>    
							    <c:otherwise>
							    	<td></td>
							    </c:otherwise>
							</c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<div id="footer"></div>
<c:forEach items="${listinvoice}" var="list">
 <div class="modal fade" id="chitietdonhang${list.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header"><h5 class="modal-title" id="exampleModalLabel">Chi tiết đơn hàng</h5> <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
        <div class="modal-body">
            <table class="table">
                <thead>
                    <tr>
                        <th>Ảnh sản phẩm</th>
                        <th>Tên sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Màu sắc</th>
                        <th>Giá</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${list.detailInvoices}" var="de">
                    <tr>
                        <td><img src="${de.colorWatch.watch.banner}" class="img-invoice-detail"></td>
                        <td>${de.colorWatch.watch.name}</td>
                        <td>${de.quantity}</td>
                        <td>${de.colorWatch.colorname}</td>
                        <td><fmt:formatNumber value="${de.colorWatch.watch.price}" maxFractionDigits="3"/>đ</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
      </div>
    </div>
</div>
</c:forEach>
<%@include file="../../common/user/footer.jsp"%>

<c:if test="${not empty khongduquyen}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Bạn không đủ quyền", 
            type: "error"
          },
        function(){ 
        });
   </script>
</c:if>
<c:if test="${not empty donhangkhongthehuy}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Đơn hàng không thể hủy", 
            type: "warning"
          },
        function(){ 
        });
   </script>
</c:if>
<c:if test="${not empty dathanhtoanmomo}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Đơn hàng đã thanh toán qua momo, không thể hủy", 
            type: "warning"
          },
        function(){ 
        });
   </script>
</c:if>

<c:if test="${not empty huythanhcong}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Đơn hàng đã được hủy", 
            type: "success"
          },
        function(){ 
        });
   </script>
</c:if>
</body>
</html>