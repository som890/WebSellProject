<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bài viết</title>
</head>
<body>
	<%@include file="../../common/admin/menuadmin.jsp"%>
	<div class="main-component">
		<div class="col-sm-12 header-sp">
            <p class="loctheongay">Lọc theo ngày</p>
            <label>Ngày bắt đầu</label>
            <input type="date">
            <label>Ngày kết thúc</label>
            <input type="date">
            <select>
                <option>---loại trạng thái---</option>
            </select>
            <button class="btn-filter btn-primary"><i class="fa fa-filter"></i> Lọc</button>
        </div>
        <div class="col-sm-12">
          <table id="example" class="table table-striped">
                <thead>
                    <tr>
                        <th>id đơn hàng</th>
                        <th>ngày tạo</th>
                        <th>trạng thái đơn hàng</th>
                        <th>tổng tiền</th>
                        <th>loại thanh toán</th>
                        <th>ghi chú đơn hàng</th>
                        <th>người đặt</th>
                        <th>địa chỉ nhận</th>
                        <th>cập nhật trạng thái</th>
                        <th>xem chỉ tiết đơn hàng</th>
                    </tr>
                </thead>
                <tbody id="list">
                <c:forEach items="${invoices}" var="list">
                    <tr>
                        <td>#${list.id}</td>
                        <td>${list.createdDate}</td>
                        <td>${list.statusInvoice.name}</td>
                        <td><fmt:formatNumber value="${list.totalAmount}" maxFractionDigits="3"/></td>
                        <td>thanh toán online</td>
                        <td>${list.note}</td>
                        <td>${list.account.username}</td>
                        <td>${list.addressAccount.address}</td>
                        <td><button onclick="setvalue(${list.id})" data-bs-toggle="modal" data-bs-target="#capnhatdonhang" class="btn btn-primary"><i class="fa fa-edit"></i> Cập nhật</button></td>
                        <td><button data-bs-toggle="modal" data-bs-target="#chitietdonhang" class="btn btn-success"><i class="fa fa-list"></i> chi tiết</button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
	</div>
  <div class="modal" id="chitietdonhang" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="false">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header"><h5 class="modal-title" id="exampleModalLabel">Chi tiết đơn hàng</h5> <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
            <div class="modal-body">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ảnh sản phẩm</th>
                            <th>tên sản phẩm</th>
                            <th>số lượng</th>
                            <th>giá</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><img src="image/detail.jpeg" class="img-invoice-detail"></td>
                            <td>quần ống rộng</td>
                            <td>3</td>
                            <td>4,500,000đ</td>
                        </tr>
                    </tbody>
                </table>
            </div>
          </div>
        </div>
    </div>

      <div class="modal" id="capnhatdonhang" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="false">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <div class="modal-header"><h5 class="modal-title" id="exampleModalLabel">Chi tiết đơn hàng</h5> <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
              <div class="modal-body">
                <form action="updateinvoice" method="post">
                 <input id="iddonhang" name="iddh" class="form-control" type="hidden">
                  <select class="form-control" name="status">
                   <c:forEach items="${status}" var="list">
                      <option value="${list.id}">${list.name}</option>
                  </c:forEach>
                  </select>
                  <button class="btn btn-primary form-control action-btn">Cập nhật</button>
                 </form>
              </div>
            </div>
          </div>
      </div>
      <script type="text/javascript">
      	function setvalue(id) {
      		document.getElementById("iddonhang").value = id
		}
      </script>
</body>
</html>