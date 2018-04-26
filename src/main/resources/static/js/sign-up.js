var temp = document.getElementById("text1");
function test()
          {

             //对电子邮件的验证
                 var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                    if(!myreg.test(temp.value))
                    {
                          alert('请输入有效的E_mail！');
                          //myreg.focus();
                          return false;
                     }
              }
var text2=document.getElementById("user_code");
function tab(){
    if(temp.value!="" && text2.value!=""){
        // console.log("aaa");
        document.getElementsByClassName("serial-1")[0].style.backgroundColor="#9696A3";
        document.getElementsByClassName("spn1")[0].style.color ="#9696A3";
        document.getElementsByClassName("li1")[0].style.borderBottom="2px solid #9696A3";

        document.getElementsByClassName("serial-2")[0].style.backgroundColor="red";
        document.getElementsByClassName("spn2")[0].style.color ="black";
        document.getElementsByClassName("li2")[0].style.borderBottom="2px solid  red";

        document.getElementsByClassName("form-list")[0].style.display="none";
        document.getElementsByClassName("form-list2")[0].style.display="block";


    }
}

//对用户名的验证
var text3=document.getElementById("text3");

function test2() {
     //用户名正则，4到16位（字母，数字，下划线，减号）
    var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
    if(!uPattern.test(text3.value)){
        alert("请输入有效的用户名！");
        return false;
    }
}

//对密码的验证
var text4=document.getElementById("text4");
function test3(){
    ////密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
    var pPattern = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
    if (!pPattern.test(text4.value)){
        alert("你输入的密码太简单，最少六位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
        return false;
    }

}

//点击验证码按钮，获取邮箱号
$(document).ready(function() {
    $("button").click(function () {
        var user_mail = $("#user_mail").val();
        var postData = {
            "user_mail": user_mail
        }
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            url: 'sendEmail',
            dataType: "json",
            data: postData,
            error: function () {
                alert('请求失败 ');
            },
            success: function (data) {
                alert('123');
            }
        });
    })


    function tab2() {
        //重新输入密码是否匹配
        var text5 = document.getElementById("text5");

        if (text4.value != "" && text5.value != "" && text4.value != text5.value) {
            alert("你输入的密码的不正确，请重新输入！");
            return false;
        }

        if (text3.value != "" && text4.value != "" && text5.value != "") {
            // console.log("aaa");
            document.getElementsByClassName("serial-2")[0].style.backgroundColor = "#9696A3";
            document.getElementsByClassName("spn2")[0].style.color = "#9696A3";
            document.getElementsByClassName("li2")[0].style.borderBottom = "2px solid #9696A3";

            document.getElementsByClassName("serial-3")[0].style.backgroundColor = "red";
            document.getElementsByClassName("spn3")[0].style.color = "black";
            document.getElementsByClassName("li3")[0].style.borderBottom = "2px solid  red";

            document.getElementsByClassName("form-list2")[0].style.display = "none";
            document.getElementsByClassName("form-list3")[0].style.display = "block";


        }
    }

    $(document).ready(function () {
        $("#user_code").blur(function () {
            var user_code = $("#user_code").val();
            $.ajax({
                url: "sendEmail",
                data: "user_code=" + user_code,
                type: "POST",
                dataType: "text",
                success: function (data) {
                    data = parseInt(data, 10);
                    if (data == 1) {
                        $("#error").html("<font color='#339933'>√ 验证码正确，请继续</font>");
                    } else if (data == 0) {
                        $("#error").html("<font color='red'>× 验证码有误，请核实后重新填写</font>");
                    }
                }
            });
        });
    });
}