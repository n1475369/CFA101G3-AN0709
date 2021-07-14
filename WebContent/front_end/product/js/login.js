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
            url:"loginServlet",
            data:{
                "username":username,
                "password":password
            },
            success:function (result) {
                if(result=="1"){
                    window.location.href="memberBuyProfile.html";
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

});