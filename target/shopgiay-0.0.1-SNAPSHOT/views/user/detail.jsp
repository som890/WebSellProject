<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>
</head>
<body>
<%@include file="../../common/user/menuuser.jsp"%>
<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <img id="img-detail" src="${detail.banner}" class="img-detail">
            </div>
            <div class="col-md-6">
                <p id="name-detail" class="name-detail">${detail.name}</p>
                <p id="price-detail" class="price-detail"><fmt:formatNumber value="${detail.price}" maxFractionDigits="3"/>₫</p>
                <p class="mausac-detail">Hình ảnh sản phẩm:</p>
                <div class="list-image-detail" id="list-image-detail">
                 	<div class="singel-img-detail">
                        <img onmouseover="changeImageDetail(this)" class="list-img-detail" src="${detail.banner}">
                    </div>
                    <c:forEach items="${detail.imageWatch}" var="img">
                    <div class="singel-img-detail">
                        <img onmouseover="changeImageDetail(this)" class="list-img-detail" src="${img.linkImage}">
                    </div>
                    </c:forEach>
                </div><br class="clearBoth" />
                
                <form method="post" action="addcart" id="formcart">
	                <div class="row" style="margin-top: 20px">
		                <div class="col-md-3">
		                	<label>nhập số lượng</label>
		                    <input id="slnhap" name="quantity" class="form-control" type="number" min="1" placeholder="số lượng">
		                </div>
		                <div class="col-md-3">
		                	<label>chọn Size</label>
		                	<select class="form-control" name="mausac">
		                	<c:forEach items="${detail.colorWatchs}" var="mau">
		                		<option value="${mau.id}">${mau.colorname}</option>
		                	</c:forEach>
		                	</select>
		                	 </select>
                    
		                </div>
	                </div>
	            </form>
                <button onclick="addcart()" class="btn-add-gio-hang">THÊM GIỎ HÀNG</button>
                <button class="btn-mua-ngay">Mua ngay</button>
                <p class="ghi-chu-detail">» BẢO HÀNH SẢN PHẨM TRONG 90 NGÀY</p>
                <p class="ghi-chu-detail">» ĐỔI HÀNG TRONG VÒNG 7 NGÀY KỂ TỪ KHI NHẬN HÀNG</p>
                <p class="ghi-chu-detail">» HOTLINE: 0966645612</p>
            </div>

            <div class="col-md-3">
                <div class="col-md-12">
                    <h5>Sản phẩm cùng danh mục</h5>
                </div>
                <div class="row" id="spcungdanhmuc">
                <c:forEach items="${listlienquan}" var="list">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" >
                        <a href="detail?id=${list.id}"><img class="img-item" src="${list.banner}"></a>
                        <div class="content-item-signle">
                            <a href=""><p class="name-signle-item">${list.name}</p>
                            <p class="price-item"><fmt:formatNumber value="${list.price}" maxFractionDigits="3"/>đ</p></a>
                        </div>
                    </div>
                 </c:forEach>  
                </div>
            </div>

            <div class="col-md-12">
                <ul id="myTab" role="tablist" class="nav nav-tabs">
                    <li class="nav-item" role="presentation">
                      <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Mô tả sản phẩm</button>
                    </li>
                    <li class="nav-item" role="presentation">
                      <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Bình luận</button>
                    </li>
                </ul>
                <div class="tab-content accordion" id="myTabContent">
                    <div class="tab-pane fade show active accordion-item" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
                        <div class="container" id="motasp">
                            <hr>
                            <h2>Mô tả sản phẩm</h2>
                            ${detail.description}
                        </div>
                    </div>
                    <div class="tab-pane fade accordion-item" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
                        <div class="container">
                            <hr>
                            <h4>Phản hồi về sản phẩm</h4>
                            
                            <div id="listcomment">
                            <c:forEach items="${listComment}" var="list">
                                <div class="col-md-4">
                                    <div class="comment-user">
                                        <img src="${list.account.avatar}">
                                        <div class="content-comment">
                                            <div class="top-comment-user">
                                                <p class="user-comment">${list.account.username}</p>
                                                <p class="time-comment"><i class="fa fa-clock"></i>${list.createdDate}</p>
                                                <c:choose>
												    <c:when test="${pageContext.request.userPrincipal.name==list.account.username}">
					                                  	<a href="deletecomment?id=${list.id}"><i class="fa fa-trash xoabl"></i></a>
												    </c:when>    
												</c:choose>
                                            </div>
                                            <p class="contentcmt">${list.content}</p>
                                        </div>
                                    </div>
                                </div>
                                  	
                            </c:forEach>
                            </div>
						<c:choose>
						    <c:when test="${pageContext.request.userPrincipal.name==null}">
						        <br> <h5>Hãy đăng nhập để bình luận sản phẩm</h5>
						    </c:when>    
						    <c:otherwise>
						    	<div class="col-md-4" id="mycomment">
	                               	<form action="addcomment" method="post">
	                               	 	<label>Bình luận của bạn</label>
	                               	 	<input name="idpro" type="text" style="display: none;" value="${detail.id}">
		                                <textarea name="content" id="noidungbl" class="form-control" required="required"></textarea>
		                                <button onclick="saveComment()" class="btn btn-primary form-control">Bình luận</button>
	                               	</form>
                            	</div>
						    </c:otherwise>
						</c:choose>
                            
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../../common/user/footer.jsp"%>
<script type="text/javascript">
	function addcart() {
		if(document.getElementById("slnhap").value == ""){
			swal({
	            title: "Thông báo", 
	            text: "Hãy nhập số lượng sản phẩm", 
	            type: "warning"
	          },
	        function(){ 
	        	  return;
	        });
		}
		else if(Number(document.getElementById("slnhap").value) < Number(1)){
			swal({
	            title: "Thông báo", 
	            text: "Số lượng sản phẩm không được nhỏ hơn 1", 
	            type: "warning"
	          },
	        function(){ 
	        	  return;
	        });
		}
		else{
			document.getElementById("formcart").submit();
		}
	}
</script>
<c:if test="${not empty successaddcomment}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Đã đăng bình luận của bạn", 
            type: "success"
          },
        function(){ 
        });
   </script>
</c:if>
<c:if test="${not empty addcartsuccess}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Đã thêm vào giỏ hàng của bạn", 
            type: "success"
          },
        function(){ 
        });
   </script>
</c:if>
<c:if test="${not empty khongdusoluong}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "số lượng sản phẩm không được vượt quá "+${khongdusoluong}, 
            type: "warning"
          },
        function(){ 
        });
   </script>
</c:if>
<c:if test="${not empty xoacmtsuccess}">
   <script type="text/javascript">
	   swal({
            title: "Thông báo", 
            text: "Đã xóa luận của bạn", 
            type: "success"
          },
        function(){ 
        });
   </script>
</c:if>
<c:if test="${not empty khongduquyenxoa}">
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
<script type="text/javascript">
function changeImageDetail(e){
    document.getElementById("img-detail").src = e.src
}
</script>
</body>
</html>