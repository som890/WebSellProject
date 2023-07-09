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
        <p class="blog-detail-name" id="blog-detail-name">TẶNG BAO LÌ XÌ - VUI MỪNG ĐÓN TẾT</p>
        <p>Đăng bởi <b class="updateby">Admin</b> on <b>
            <time class="updateby" id="updateat"> ${detail.createdDate}</time></b></p>
        <div class="content-blog-detail" id="contentblog">
			${detail.content}
        </div>
    </div>
</div>
<%@include file="../../common/user/footer.jsp"%>
</body>
</html>