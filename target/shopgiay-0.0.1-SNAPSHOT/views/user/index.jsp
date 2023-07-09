<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRANG CHỦ</title>
</head>
<body>
<%@include file="../../common/user/menuuser.jsp"%>
<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img style="height: 700px;" src="https://img.freepik.com/free-vector/modern-black-friday-sale-banner-template-with-3d-background-red-splash_1361-1877.jpg?size=626&ext=jpg&ga=GA1.1.1917480170.1682134952&semt=ais" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img style="height: 700px;" src="https://freehindidesign.com/wp-content/uploads/2022/05/SHOES-SHOP-BANNER-TEMPLATE.webp" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img style="height: 700px;" src="https://img.freepik.com/free-psd/black-friday-super-sale-web-banner-template_106176-1649.jpg?size=626&ext=jpg&ga=GA1.1.1917480170.1682134952&semt=ais" class="d-block w-100" alt="...">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>


    <div class="content">
        <div class="container">
            <ul id="myTab" role="tablist">
                <li class="nav-item tab-li" role="presentation">
                  <button class="active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Sản phẩm trong cửa hàng</button>
                </li>
                <!-- li class="nav-item tab-li" role="presentation">
                  <button class="" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Sản phẩm bán chạy</button>
                </li> -->
            </ul>
            <div class="tab-content accordion" id="myTabContent">
                <div class="tab-pane fade show active accordion-item" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
                    <div class="row">
                        <div class="col-md-10 list-item row" id="listNewProduct">
                        <c:forEach items="${newwatch}" var="list">
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
                     
                        <div class="col-md-12">
                            <div class="paginationss">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination" id="listpage">
                                      <li class="page-item"><a class="page-link" href="home?page=${pre}#myTabContent">Previous</a></li>
                                      <c:forEach items="${totalpage}" var="pages">
                                      <li class="page-item"><a class="page-link" href="home?page=${pages}#myTabContent">${pages}</a></li>
                                      </c:forEach>
                                      <li class="page-item"><a class="page-link" href="home?page=${next}#myTabContent">Next</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
               <%--  <div class="tab-pane fade accordion-item" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
                    <c:forEach items="${spbanchay}" var="list">
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
                </div> --%>
            </div>
            
        </div>
    </div>
<%@include file="../../common/user/footer.jsp"%>
</body>
</html>