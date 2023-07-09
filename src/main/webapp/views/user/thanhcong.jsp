<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt hàng thành công</title>
<style type="text/css">
    body
    {
        background:#f2f2f2;
    }

    .payment
	{
		border:1px solid #f2f2f2;
		height:280px;
        border-radius:20px;
        background:#fff;
	}
   .payment_header
   {
	   background:rgba(255,102,0,1);
	   padding:20px;
       border-radius:20px 20px 0px 0px;
	   
   }
   
   .check
   {
	   margin:0px auto;
	   width:50px;
	   height:50px;
	   border-radius:100%;
	   background:#fff;
	   text-align:center;
   }
   
   .check i
   {
	   vertical-align:middle;
	   line-height:50px;
	   font-size:30px;
   }

    .content 
    {
        text-align:center;
    }

    .content  h1
    {
        font-size:25px;
        padding-top:25px;
    }

    .content a
    {
        width:200px;
        height:35px;
        color:#fff;
        border-radius:30px;
        padding:5px 10px;
        background:rgba(255,102,0,1);
        transition:all ease-in-out 0.3s;
    }

    .content a:hover
    {
        text-decoration:none;
        background:#000;
    }
</style>
</head>
<body>
<%@include file="../../common/user/menuuser.jsp"%>
    <div class="container">
	   <div class="row">
	      <div class="col-md-6 mx-auto mt-5">
	         <div class="payment">
	            <div class="payment_header">
	               <div class="check"><i class="fa fa-check" aria-hidden="true"></i></div>
	            </div>
	            <div class="content">
	               <h1>Đơn hàng đã được tạo thành công !</h1>
	               <p>Cảm ơn bạn đã tin tưởng chúng tôi, đơn hàng của bạn đã được tạo. Bạn sẽ nhận được đơn hàng sau từ 3-7 ngày. </p>
	               <a href="home">Quay lại trang chủ</a>
	            </div>
	            
	         </div>
	      </div>
	   </div>
	</div>
<%@include file="../../common/user/footer.jsp"%>
</body>
</html>