//驗證是否為登入狀態
let xhr = new XMLHttpRequest();
let MemberName = null;
xhr.open('GET', '../../member/checkServlet', true);
xhr.send(null);
xhr.onreadystatechange = function (e) {
    if (xhr.readyState == 4 && xhr.status == 200) {
        if(xhr.responseText == "0"){
            console.log(xhr.responseText);
        }else{
            MemberName = xhr.responseText
            document.write(MemberName + ",歡迎登入");
        }
    }
};