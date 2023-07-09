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
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-md-3 col-lg-3">
                <form class="filter">
                    <label class="lb-chon-danhmuc">Chọn khoảng giá
                        <button onclick="searchFull(0)" class="btn-apdung">Áp dụng</button>
                    </label>
                    <label class="radio-custom">Mọi khoảng giá
                        <input value="0-1000000000" type="radio" name="khoangia" checked="checked">
                        <span class="checkmark"></span>
                    </label>
                    <label class="radio-custom">DƯỚI 500.000đ
                        <input value="0-499000" type="radio" name="khoangia">
                        <span class="checkmark"></span>
                    </label>
                    <label class="radio-custom">500,000₫ - 1000,000₫
                        <input value="500000-1000000" type="radio" name="khoangia">
                        <span class="checkmark"></span>
                    </label>
                    <label class="radio-custom">1000,000₫ - 1500,000₫
                        <input value="1000000-1500000" type="radio" name="khoangia">
                        <span class="checkmark"></span>
                    </label>
                    <label class="radio-custom">1500,000₫ - 2000,000₫
                        <input value="1500000-2000000" type="radio" name="khoangia">
                        <span class="checkmark"></span>
                    </label>
                    <label class="radio-custom">TRÊN 2000,000₫
                        <input value="2000000-1000000000" type="radio" name="khoangia" >
                        <span class="checkmark"></span>
                    </label>
                    <hr>
                    <label class="lb-chon-danhmuc">Chọn danh mục</label>
                    <div id="listsearchCategory">
                        <label class="radio-custom">Tất cả danh mục
	                        <input value="-1" type="radio" name="danhmuc" checked="checked">
	                        <span class="checkmark"></span>
                    	</label>
	                 </div>
                    <c:forEach items="${listcategory}" var="list">
	                    <div id="listsearchCategory">
	                        <label class="radio-custom">${list.name}
		                        <input value="${list.id}" type="radio" name="danhmuc">
		                        <span class="checkmark"></span>
	                    	</label>
	                    </div>
                    </c:forEach>
                    <hr>
                    <label class="lb-chon-danhmuc">Chọn thương hiệu</label>
                     <div id="listsearchCategory">
                        <label class="radio-custom">Tất cả thương hiệu
	                        <input value="-1" type="radio" name="thuonghieu" checked="checked">
	                        <span class="checkmark"></span>
                    	</label>
	                 </div>
                    <c:forEach items="${listtrademark}" var="list">
                    <div id="listsearchCategory">
                        <label class="radio-custom">${list.name}
	                        <input value="${list.id}" type="radio" name="thuonghieu">
	                        <span class="checkmark"></span>
                    	</label>
                    </div>
                    </c:forEach>
                </form>
            </div>
            <div class="col-sm-12 col-md-9 col-lg-9">
                <div class="row">
                    <div class="col-md-10 list-item row" id="listNewProduct">
                        <p class="hienthisoluong">Hiển thị 1-12 của 46 kết quả</p>
                        <c:forEach items="${listproduct}" var="list">
                            <div class="col-xs-6 col-sm-6 col-md-3 col-lg-3 signle-item" >
                                <a href="detail?id=${list.id}"><img class="img-item" src="${list.banner}"></a>
                                <div class="small-image">
                                <c:forEach items="${list.imageWatch}" var="img">
                                    <img src="${img.linkImage}">
                                </c:forEach>
                                </div>
                                <div class="content-item-signle">
                                    <a href="detail?id=${list.id}"><p class="name-signle-item">${list.name}</p></a>
                                    <p style="text-align: center;" class="price-item"><fmt:formatNumber value="${list.price}" maxFractionDigits="3"/>đ</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="paginationss">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination" id="listpage">
                            <c:forEach items="${totalpage}" var="pages">
                              <li onclick="appendurl(${pages})" class="page-item"><a class="page-link">${pages}</a></li>
                            </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function appendurl(page) {
	var url =  window.location.href;
	if(url.split('khoangia').length > 1){
		url = url.split('&page')[0]
		url = url+'&page='+page
		window.location.replace(url)
	}
	else{
		 window.location.replace('product?page='+page)
	}
}
</script>
<script type="text/javascript">
var url_string = window.location.href; 
var url = new URL(url_string);
var khoangia = url.searchParams.get("khoangia");
if(khoangia != null){
	
}
</script>
<%@include file="../../common/user/footer.jsp"%>
</body>
</html>