<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bài viết</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
<%@include file="../../common/user/menuuser.jsp"%>
 <div class="content infor-taikhoan">
    <div class="container">
       <div class="row">
            <div class="col-md-12">
                <h4 class="title-taikhoan">Thông Tin Tài Khoản</h4>
            </div>
            <div class="col-md-6">
                <div class="container chitietthongtintk">
                    <div class="profile-detail">
                        <p>Chi tiết thông tin</p>
                    </div>
                    <form:form method="post" action="updateinfor" enctype="multipart/form-data" modelAttribute="account" class="content-profile">
                       <div class="box">
                        <img src="${accountp.avatar}" id="imgpre" style="width: 200px;height: 200px;border-radius: 50%">
                        <button type="button" id="OpenImgUpload" class="upload-anh"><i class="fa fa-upload"></i> Upload ảnh</button>
                        <form:input type="file" id="imgupload" style="display: none;" path="file"/> 
                       </div>
                        <br>
                        <form:input class="form-control" path="id" style="display: none;"/>
                        <label>Họ tên</label>
                        <form:input id="fullname" class="form-control" path="fullname" />
                        <form:errors class="error" path="fullname"></form:errors><br>
                        <label>Số điện thoại</label>
                        <form:input id="phone" class="form-control" path="phone" />
                        <form:errors class="error" path="phone"></form:errors><br>
                        <label>Email</label>
                        <form:input id="email" class="form-control" path="email" />
                        <form:errors class="error" path="email"></form:errors><br>
                        <label>Địa chỉ nhận hàng</label>
                        <form:input id="email" class="form-control" path="address" />
                        <form:errors class="error" path="address"></form:errors><br>
                        <button class="luuthaydoi">Lưu thay đổi</button>
                    </form:form>
                </div>
            </div>
            <div class="col-md-6">
                <div class="container chitietthongtintk">
                    <div class="profile-detail">
                        <p>Cập nhật mật khẩu</p>
                    </div>
                    <div class="content-profile">
                        <form:form method="post" action="updatepass" modelAttribute="password">
	                        <label>Mật khẩu cũ</label>
	                        <form:input class="form-control" type="password" placeholder="*****" path="pass" />
	                        <form:errors class="error" path="pass"></form:errors><br>
	                        <label>Mật khẩu mới</label>
	                        <form:input id="newpass" class="form-control" type="password" placeholder="*****" path="newpass" />
	                        <form:errors class="error" path="newpass"></form:errors><br>
	                        <label>Nhập lại mật khẩu mới</label>
	                        <form:input id="renew" class="form-control" type="password" placeholder="*****" path="renewpass" />
	                        <form:errors class="error" path="renewpass"></form:errors><br>
	                        <button class="luuthaydoi">Cập nhật mật khẩu</button>
                        </form:form>
                    </div>
                </div>
            </div>
       </div>
    </div>
</div>
<%@include file="../../common/user/footer.jsp"%>
<script type="text/javascript">


imgupload.onchange = evt => {
  const [file] = imgupload.files
  if (file) {
	  imgpre.src = URL.createObjectURL(file)
  }
}
$('#OpenImgUpload').click(function(){ $('#imgupload').trigger('click'); });
</script>

<c:if test="${not empty inforthanhcong}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Cập nhật thông tin thành công", 
            type: "success"
          },
        function(){ 
        });
   </script>
</c:if>
<c:if test="${not empty doimktc}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Thay đổi mật khẩu thành công", 
            type: "success"
          },
        function(){ 
        });
   </script>
</c:if>
<c:if test="${not empty doimkthatbai}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Mật khẩu cũ không chính xác", 
            type: "error"
          },
        function(){ 
        });
   </script>
</c:if>
</body>
</html>