//获取项目的路径
var path = "";
$(function () {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    path = prePath;
});

//图片服务器路径
var file_path = "http://localhost:8888";

var common = {

    init : function () {

        //播放音乐
        audio.play();


        //音乐操作
        common.audioPlay();

    },


    URL: {
        backIndexURL:function () {
            return path + "/index.html";
        },
        getUserURL: function () {
            return path + "/session";
        },
        logOutUserURL:function () {
            return path + "/session";
        }
    },

    //退出用户
    logOutUser: function () {
        $.ajax({
            url: common.URL.logOutUserURL(),
            type: "delete",
            success: function (result) {
                if (result && result['code'] == 0) {
                    //退出成功返回首页
                    window.location.href = common.URL.backIndexURL();
                } else {
                    console.log(result)
                }
            },
            error: function () {
                Error.displayError(result);
            }
        });
    },
    //播放音乐相关操作
    audioPlay :function () {

        var btn1 = document.getElementById("btn-play");
        btn1.onclick = function () {
            if (audio.paused) {
                audio.play();
            } else {
                audio.pause();
            }
        };
        var music = new Array();
        music = ["1", "2", "3"];//歌单
        var num = 0;
        var name = document.getElementById("name");


        <!--上一首-->
        var btn3 = document.getElementById("btn-pre");
        btn3.onclick = function () {
            num = (num + 2) % 3;
            audio.src = "music/" + music[num] + ".mp3";
            name.innerHTML = music[num];
            audio.play();
        };
        <!--下一首-->
        var btn4 = document.getElementById("btn-next");
        btn4.onclick = function () {
            num = (num + 1) % 3;
            audio.src = "music/" + music[num] + ".mp3";
            name.innerHTML = music[num];
            audio.play();
        }
        audio.addEventListener('ended', function () {
            btn4.onclick();
        }, false);
    }

};

// 显示或者记录错误
var Error = {

    //显示错误
    displayError: function (result) {
        sweetAlert(Error.sweetAlertInfo(result.msg));
    },

    //配置错误的信息
    sweetAlertInfo: function (info) {

        //sweertAlertObj默认的配置
        var sweertAlertObj = {
            title: "操作失败",
            type: "error",
            showConfirmButton: true
        };

        //灵活的配置
        sweertAlertObj.text = info;
        return sweertAlertObj;
    }

};

var Cookie = {
    //发送验证码时添加cookie
    addCookie: function (name, value, expiresHours) {

        var cookieString = name + "=" + escape(value);
        //判断是否设置过期时间,0代表关闭浏览器时失效
        if (expiresHours > 0) {
            var date = new Date();
            date.setTime(date.getTime() + expiresHours * 1000);
            cookieString = cookieString + ";expires=" + date.toUTCString();
        }
        document.cookie = cookieString;

    },
    //修改cookie的值
    editCookie: function (name, value, expiresHours) {
        var cookieString = name + "=" + escape(value);
        if (expiresHours > 0) {
            var date = new Date();
            date.setTime(date.getTime() + expiresHours * 1000); //单位是毫秒
            cookieString = cookieString + ";expires=" + date.toGMTString();
        }
        document.cookie = cookieString;
    },
    //根据名字获取cookie的值
    getCookieValue: function (name) {
        var strCookie = document.cookie;
        var arrCookie = strCookie.split("; ");
        for (var i = 0; i < arrCookie.length; i++) {
            var arr = arrCookie[i].split("=");
            if (arr[0] == name) {
                return unescape(arr[1]);
                break;
            }
        }
    }
}
