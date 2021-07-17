<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Integer payment = (Integer)request.getAttribute("payment");
	if(payment == null){
		response.sendRedirect("/index.html");
	}
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"
    integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<style>


.loader3 {
        display: none;
        margin: 20px auto;
        font-size: 10px;
        position: relative;
        text-indent: -9999em;
        border-top: 1.1em solid rgba(64, 128, 128, .2);
        border-right: 1.1em solid rgba(64, 128, 128, .2);
        border-bottom: 1.1em solid rgba(64, 128, 128, .2);
        border-left: 1.1em solid #408080;
        -webkit-transform: translateZ(0);
        -ms-transform: translateZ(0);
        transform: translateZ(0);
        -webkit-animation: loader3 1.1s infinite linear;
        animation: loader3 1.1s infinite linear
    }

    .loader3,
    .loader3:after {
        border-radius: 50%;
        width: 10em;
        height: 10em
    }

    @-webkit-keyframes loader3 {
        0% {
            -webkit-transform: rotate(0);
            transform: rotate(0)
        }

        100% {
            -webkit-transform: rotate(360deg);
            transform: rotate(360deg)
        }
    }

    @keyframes loader3 {
        0% {
            -webkit-transform: rotate(0);
            transform: rotate(0)
        }

        100% {
            -webkit-transform: rotate(360deg);
            transform: rotate(360deg)
        }
    }

    body{
        box-sizing: border-box;
    }

    .container{
        width: 380px;
        height: 520px;
        border: 1px solid #000;
        margin: 0 auto;
        margin-top: 20px;
        padding: 30px;
        border-radius: 5px;
    }

    .show{
        width: 340px;
        height: 60px;
        border: 1px solid #000;
        margin: 0 auto;
        border-radius: 5px;
        font-size: 40px;
        line-height: 60px;
        padding-left: 10px;
        padding-right: 10px;
    }

    .btn{
        display: inline-block;
        width: 90px;
        height: 90px;
        border: 1px solid #000;
        margin: 12px;
        background: #f5f1f1;
        border-radius: 5px;
    }

    .btn:hover{
        cursor: pointer;
    }

    .box{
        font-size: 40px;
        text-align: center;
        line-height: 90px;
    }

    h4{
        text-align: center;
        display: none;
    }

    .title{
        width: 440px;
        margin: 0 auto;
    }

    .load{
        position: absolute;
        top:50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
</style>
<body>
    <div class="load">
        <div class="loader3" id="loader"></div>
        <h4>處理中請稍後...</h4>
    </div>
    <div id="wrap">
		<div class="title">(模擬Web-ATM轉帳)<br>
            銀行代碼：822<br>
            銀行名稱：中國信託<br>
            轉入帳號：2885-4001-1352<br>
            付款金額：$${payment}
        </div>        <div class="container">
            <div class="show" id="show"></div>
            <div class="box">
                <div data-number="1" class="btn">1</div>
                <div data-number="2" class="btn">2</div>
                <div data-number="3" class="btn">3</div>
                <div data-number="4" class="btn">4</div>
                <div data-number="5" class="btn">5</div>
                <div data-number="6" class="btn">6</div>
                <div data-number="7" class="btn">7</div>
                <div data-number="8" class="btn">8</div>
                <div data-number="9" class="btn">9</div>
                <div data-number="0" class="btn">0</div>
                <div class="btn" id="clear">清除</div>
                <div class="btn" id="submit">確定</div>
            </div>
        </div>
        <form action="<%=request.getContextPath()%>/shop_order/spoServlet" method="post" id="submit-form">
            <input type="hidden" name="action" value="webATM">
            <input type="hidden" name="pay" id="pay">
			<input type="hidden" name="payment" id="payment">
        </form>
    </div>
    <script>
        $('.btn').on('click',function(e){
            let number = e.target.dataset.number;
            let show = $('#show').html();

            if(show.length <= 10){
                $('#show').append(number);
            }
        });

        $('#clear').on('click',function(){
            $('#show').html("");
        });

        //送出付款表單
        $('#submit').on('click',function(e){
                $('#wrap').hide();
                $('#loader').show();
                $('h4').show();
                let pay = $('#show').html();
                $('#pay').val(pay);
                $('#payment').val(${payment});
                setTimeout(function(){
                    $('#submit-form').submit();
                },2000);
            })
    </script>
</body>
</html>