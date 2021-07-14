$(function(){
    //個人資料template
    let profile =
            `<h2>個人資料</h2>
            <div class="profile">
                <h3>基本資料</h3>
                <span>會員名稱</span><small>在MarryHappiness 使用的䁥稱</small><input type="button" value="編輯" id="edit" class="edit">
                <div id="name" class="user-name"></div>
                <span>手機號碼</span>
                <div id="phone" class="h50"></div>
                <span>居住縣市</span>
                <div id="city" class="h50"></div>
                <span>居住鄉鎮區</span>
                <div id="cityarea" class="h50"></div>
                <span>居住道路或街名</span>
                <div id="street" class="h50"></div>
            </div>
            <div class="profile-form">
                <form action="" id="profile-form" name="profile-form">
                    <h3>編輯基本資料</h3>
                    <div id="prompt"></div>
                    <label for="name-form">會員名稱</label><small>在MarryHappiness 使用的䁥稱</small><br>
                    <input type="text" name="name-form" id="name-form">
                    <div id="name-prompt"></div>
                    <label for="phone-form">手機號碼</label><br>
                    <input type="text" name="phone-form" id="phone-form" maxlength="10">
                    <div id="phone-prompt"></div>
                    <label for="city">縣市及鄉鎮區</label><br>
                    <div id="twzipcode"></div>
                    <label for="street">居住道路或街名</label><br>
                    <input type="text" name="street-form" id="street-form">
                    <input type="button" value="修改" id="edit-submit" class="edit-submit">
                    <input type="button" value="取消" id="cancel" class="cancel">
                </form>
            </div>
            <div class="headshot-edit">
                <h3>編輯個人頭像</h3>
                <img id="headshot-img" class="imgdata" src="" alt=""> 
                <div class="actions"> 
                    <button class="file-btn" id="file-btn"> 
                    <span>選擇檔案</span> 
                    <input type="file" id="upload" value="選擇圖片檔案" accept="image/*"> 
                    </button>
                    <div class="popup-wrap">
                        <div class="crop"> 
                            <div id="upload-demo"></div> 
                            <button class="upload-result">確定</button> 
                            <button id="crop-cancel">取消</button>
                        </div> 
                    </div>
                </div> 
            </div>`;
    
    //預設載入個人資料頁面
    $('#content').html(profile);

    //跳轉個人資料頁面
    $('#profile').on('click',function(){
        $('#content').html(profile);
        Ajaxprofile();
        Ajaxheadshot();
        //設定croppie初始值
        $uploadCrop = $('#upload-demo').croppie({ 
            viewport: { 
                width: 200, 
                height: 200, 
                type: 'circle',
            }, 
            showZoomer:false,
            boundary: { 
                width: 300,
                height: 300
            }
        }); 
    });


    var resultData;
    //profile跟資料庫請求個人資料
    Ajaxprofile();
    function Ajaxprofile(){
        $.ajax({
            type:"get",
            url:"../../member/buyProfileServlet",
            dataType : 'json',
            success:function (result) {
                if(result == "0"){
                    window.location.href="index.html";
                }else{
                    resultData = result;
                    $('.user-name').html(result.name!=null?result.name:"尚未填寫");
                    $('#phone').html(result.phone!=null?result.phone:"尚未填寫");
                    $('#city').html(result.city!=null?result.city:"尚未填寫");
                    $('#cityarea').html(result.cityarea!=null?result.cityarea:"尚未填寫");
                    $('#street').html(result.street!=null?result.street:"尚未填寫");
                }
            }
        });
    };        

    //跟資料庫請求個人頭像
    Ajaxheadshot();
    function Ajaxheadshot(){
        $.ajax({
            type:"post",
            url:"../../member/headshotBuyServlet",
            data:{"headshot":"headshot"},
            xhrFields: {
                // 將回傳結果以Blob保持原本二進位的格式回傳
                //jquery的dataType無法設定返回格式為blob需要手動修改
                responseType: "blob"
            },
            success:function (result) {
                let img = document.getElementsByClassName('imgdata');
                if(result.size != "0"){
                    let url = URL.createObjectURL(result);
                    for (let i = 0; i < img.length; i++) {
                        img[i].src = url;
                    }
                }else{
                    for (let i = 0; i < img.length; i++) {
                        img[i].src = "images/music_castanet_girl.png";
                    }
                }
                
            }
        });
    }
    
    

    //編輯按鈕事件
    $('#content').on('click','#edit',function(){
        //呼叫縣市及鄉鎮區選單並預設初始值
        $('#twzipcode').twzipcode({
            'zipcodeSel'  : '106', 
            'countySel'   : '臺北市',
            'districtSel' : '中正區'
        });
        $('#prompt').text("");
        $('#phone-form').css('border','1px solid #27da80');
        $('#phone-form').css('box-shadow','');
        $('#phone-prompt').text("");
        $('#name-form').css('border','1px solid #27da80');
        $('#name-form').css('box-shadow','');
        $('#name-prompt').text("");
        $('.profile-form').css('display','block');
        $('.profile').css('display','none');
        $('#name-form').val(resultData.name);
        $('#phone-form').val(resultData.phone);
        $('#street-form').val(resultData.street);
        if(resultData.city!=null){
            $('select[name="city"]').val(resultData.city);
            $('select[name="city"]').change();
        }
        if(resultData.cityarea!=null){
            $('select[name="cityarea"]').val(resultData.cityarea);
        }
        checkFlag();
    });

    

    //取消按鈕事件
    $('#content').on('click','#cancel',function(){
        $('.profile-form').css('display','none');
        $('.profile').css('display','block');
    });


    //設置驗證旗幟
    let name_flag = true;
    let phone_flag = true;

    //確認旗幟均為true按鈕才能按
    function checkFlag(){
        if(name_flag && phone_flag){
            $("#edit-submit").removeAttr("disabled") 
        }else{
            $("#edit-submit").attr("disabled", "disabled") 
        }
    }
    
    //監聽會員名稱格式是否正確
    $('#content').on('input','#name-form',function(){
        $('#name-prompt').text("");
        if($('#name-form').val() != ""){
            $('#name-form').css('border','1px solid #27da80');
            $('#name-form').css('box-shadow','');
            name_flag = true;
        }else{
            $('#name-prompt').text("會員名稱不得為空");
            $('#name-prompt').css('color','red');
            $('#name-prompt').css('font-size','10px');
            $('#name-form').css('border','2px solid red');
            $('#name-form').css('box-shadow','0 0 0 2px #f1a7c0');
            name_flag = false;
        }
        checkFlag();
    });

    //監聽電話格式是否正確
    $('#content').on('input','#phone-form',function(){
        $('#phone-prompt').text("");
        if(validatePhone()){
            $('#phone-form').css('border','1px solid #27da80')
            $('#phone-form').css('box-shadow','');
            phone_flag = true;
        }else{
            $('#phone-prompt').text("請輸入有效的手機號碼");
            $('#phone-prompt').css('color','red');
            $('#phone-prompt').css('font-size','10px');
            $('#phone-form').css('border','2px solid red');
            $('#phone-form').css('box-shadow','0 0 0 2px #f1a7c0');
            phone_flag = false;
        }
        checkFlag();
    });

    //電話正則表達式驗證
    function validatePhone(){
        let phone = $('#phone-form').val();
        const re = /^09[0-9]{8}$/;
        return re.test(phone);
    }

    //送出修改表單
    $('#content').on('click','#edit-submit',function(){
        $.ajax({
        type:"post",
        url:"../../member/updateBuyProfileServlet",
        data:$('#profile-form').serialize(),
        success:function (result) {
            if(result == "1"){
                window.location.reload();
            }else{
                $('#prompt').text("更新失敗，請重新填寫");
                $('#prompt').css('color','red');
                $('#prompt').css('font-size','10px');
            }
        }
        });
    });


    var $uploadCrop; 
    //讀取上傳圖片
    function readFile(input) { 
        if (input.files && input.files[0]) { 
            var reader = new FileReader(); 
            reader.onload = function (e) { 
                $uploadCrop.croppie('bind', { 
                url: e.target.result 
            }); 
            } 
        reader.readAsDataURL(input.files[0]); 
        } 
    } 

    //設定croppie初始值
    $uploadCrop = $('#upload-demo').croppie({ 
        viewport: { 
            width: 200, 
            height: 200, 
            type: 'circle',
        }, 
        showZoomer:false,
        boundary: { 
            width: 300,
            height: 300
        }
    }); 

    //點擊button觸發input[type="file"]
    $('#content').on('click','#file-btn',function(){
        document.getElementById('upload').click();
    });

    //點選選擇檔案後開啟圈選範圍面板
    $('#content').on('change','#upload', function(){
        $(".popup-wrap").fadeIn(250);
        $(".crop").fadeIn(250); 
        readFile(this);    
    });

    //關閉圈選面板
    $('#content').on('click','#crop-cancel',function(){
        $(".crop").fadeOut();
        $(".popup-wrap").fadeOut(250);
    });

    //送出圈選後的頭像至資料庫
    $('#content').on('click','.upload-result',function (){
        $uploadCrop.croppie('result', 'blob').then(function (blob){
            var myForm = new FormData();
            myForm.append("blob",blob);
            $.ajax({
            type:"post",
            enctype : 'multipart/form-data',
            url:"../../member/headshotBuyServlet",
            data:myForm,
            processData: false,
            contentType : false,
            xhrFields: {
                // 將回傳結果以Blob保持原本二進位的格式回傳
                //jquery的dataType無法設定返回格式為blob需要手動修改
                responseType: "blob"
            },
            success:function (result) {
                window.location.reload();
            }
            });
        });
    });
});