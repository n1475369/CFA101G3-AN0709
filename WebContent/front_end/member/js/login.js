$(function(){
    //設置驗證旗幟
    let u_flag = false;
    let p_flag = false;

    //確認旗幟均為true按鈕才能按
    function checkFlag(){
        if(u_flag && p_flag){
            $("#login").removeAttr("disabled") 
        }else{
            $("#login").attr("disabled", "disabled") 
        }
    }

    //監聽帳號格式是否正確
    $('#username').on('input',function(){
        $('#u-prompt').text("");
        if(validateUsername()){
            $('#username').css('border','2px solid #27da80')
            u_flag = true;
        }else{
            $('#u-prompt').text("請輸入有效的電子郵件地址");
            $('#u-prompt').css('color','red');
            $('#u-prompt').css('font-size','10px');
            $('#username').css('border','2px solid red')
            u_flag = false;
        }
        checkFlag();
    });

    //監聽密碼格式是否正確
    $('#password').on('input',function(){
        $('#p-prompt').text("");
        if(validatePassword()){
            $('#password').css('border','2px solid #27da80')
            p_flag = true;
        }else{
            $('#p-prompt').text("密碼長度限制6-20");
            $('#p-prompt').css('color','red');
            $('#p-prompt').css('font-size','10px');
            $('#password').css('border','2px solid red')
            p_flag = false;
        }
        checkFlag();
    });

    //送出登入驗證
    $('#login').on('click',function(){
        let username = $('#username').val();
        let password = $('#password').val();
        $.ajax({
            type:"post",
            url:"../../member/loginServlet",
            data:{
                "username":username,
                "password":password
            },
            success:function (result) {
                if(result=="1"){
                    window.location.href="memberBuyProfile.html";
                    // window.location.href="websocket.html";

                }else{
                    $('#u-prompt').text("帳號或密碼錯誤");
                    $('#u-prompt').css('color','red');
                    $('#u-prompt').css('font-size','10px');
                    $('#username').css('border','2px solid red')
                }
            }
        });
    });


    //帳號正則表達式驗證
    function validateUsername() {
        let username = $('#username').val();
        const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(username);
    }

    //密碼正則表達式驗證
    function validatePassword(){
        let password = $('#password').val();
        const re = /^[0-9A-Za-z]{6,20}$/;
        return re.test(password);
    }


    //忘記密碼-開啟圈選範圍面板
    $('#forget').on('click', function(){
        $(".popup-wrap").fadeIn(250);
        $(".popup").fadeIn(250); 
    });

    //忘記密碼-關閉圈選面板
    $('#popup-cancel').on('click',function(){
        $(".popup").fadeOut();
        $(".popup-wrap").fadeOut(250);
        $('#email').val("");
        $('#prompt').text("");
        $('#email').css('border','1px solid #a1a0a0')
    });

    
    //忘記密碼-監聽email格式是否正確
    $('#email').on('input',checkEmail);
    function checkEmail(){
        $('#prompt').text("");
        if(validateEmail()){
            $('#email').css('border','2px solid #27da80')
            return true;
        }else{
            $('#prompt').text("請輸入有效的電子郵件地址");
            $('#prompt').css('color','red');
            $('#prompt').css('font-size','10px');
            $('#email').css('border','2px solid red')
            return false;
        }
    }

    //忘記密碼-信箱正則表達式驗證
    function validateEmail() {
        let email = $('#email').val();
        const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }


    //忘記密碼-送出表單
    $('#submit-email').on('click',function(){
        if(checkEmail()){
            let email = $('#email').val();
            $.ajax({
                type:"post",
                url:"../../member/forgetPasswordServlet",
                data:{"email":email},
                success:function(){
                    alert("已將密碼成功寄到您的信箱");
                    window.location.reload();
                }
            });
        }
    });


});