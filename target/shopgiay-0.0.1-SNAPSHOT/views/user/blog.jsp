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
            <h4>Bài viết</h4>
        </div>
    </div>
    <div class="container">
        <div class="row blog-list" id="blog-list">
        <c:forEach items="${blogs}" var="list">
            <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 blogs">
                <div class="blog-single">
                    <img src="${list.imageBanner}" style="height: 150px;">
                    <p class="time-blog">${list.createdDate}</p>
                    <p class="ten-blog">${list.title}</p>
                    <p class="mota-blog">
                       ${list.description}!</p>
                    <a class="xemthem-blog" href="blogdetail?id=${list.id}">Xem thêm ></a>
                </div>
            </div>
         </c:forEach>
        </div>
        <nav aria-label="Page navigation example" style="margin-top: 50px">
		  <ul class="pagination">
		    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
		    <c:forEach items="${totalpage}" var="list">
		    <li class="page-item"><a class="page-link" href="blog?page=${list}">${list}</a></li>
		    </c:forEach>
		    <li class="page-item"><a class="page-link" href="#">Next</a></li>
		  </ul>
		</nav>
    </div>
</div>
<%@include file="../../common/user/footer.jsp"%>
</body>
</html>