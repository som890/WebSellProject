<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bài viết</title>
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
                            <th>id đơn hàng</th>
                            <th>Tổng tiền hàng</th>
                            <th>Số lượng sản phẩm</th>
                            <th>Ngày đặt</th>
                            <th>Thanh toán</th>
                            <th>Trạng thái đơn hàng</th>
                            <th>Chi tiết đơn hàng</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="list">
                        <tr>
                            <td>#2342</td>
                            <td>4,500,000đ</td>
                            <td>3</td>
                            <td>2023-02-11</td>
                            <td>đã thanh toán qua momo</td>
                            <td><p class="dangcho">đang chờ duyệt</p></td>
                            <td><button class="xemchitiet-dh">Xem chi tiết</button></td>
                            <td><button class="huy-dh">Hủy đơn</button></td>
                        </tr>
                        <tr>
                            <td>#2342</td>
                            <td>4,500,000đ</td>
                            <td>3</td>
                            <td>2023-02-11</td>
                            <td>đã thanh toán qua momo</td>
                            <td><p class="dataodon">đã tạo đơn hàng</p></td>
                            <td><button class="xemchitiet-dh">Xem chi tiết</button></td>
                            <td><button class="huy-dh">Hủy đơn</button></td>
                        </tr>
                        <tr>
                            <td>#2342</td>
                            <td>4,500,000đ</td>
                            <td>3</td>
                            <td>2023-02-11</td>
                            <td>đã thanh toán qua momo</td>
                            <td><p class="dagui">đã gửi</p></td>
                            <td><button class="xemchitiet-dh">Xem chi tiết</button></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>#2342</td>
                            <td>4,500,000đ</td>
                            <td>3</td>
                            <td>2023-02-11</td>
                            <td>đã thanh toán qua momo</td>
                            <td><p class="danhanhang">đã nhận được hàng</p></td>
                            <td><button class="xemchitiet-dh">Xem chi tiết</button></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>#2342</td>
                            <td>4,500,000đ</td>
                            <td>3</td>
                            <td>2023-02-11</td>
                            <td>đã thanh toán qua momo</td>
                            <td><p class="dahuy">đã hủy đơn hàng</p></td>
                            <td><button data-bs-toggle="modal" data-bs-target="#chitietdonhang" class="xemchitiet-dh">Xem chi tiết</button></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<div id="footer"></div>
<div class="modal fade" id="chitietdonhang" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                        <th>size</th>
                        <th>màu sắc</th>
                        <th>giá</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><img src="image/detail.jpeg" class="img-invoice-detail"></td>
                        <td>quần ống rộng</td>
                        <td>3</td>
                        <td>M</td>
                        <td>vàng</td>
                        <td>4,500,000đ</td>
                    </tr>
                </tbody>
            </table>
        </div>
      </div>
    </div>
</div>
<%@include file="../../common/user/footer.jsp"%>
</body>
</html>