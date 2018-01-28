var index = {

    init: function () {
        //轮播图启动
        $('.slider').slider();

        //侧边导航条
        $("#button-collapse").sideNav('show');

        index.getUser();
        $("#logout").click(function () {
            common.logOutUser();
        });
        //播放音乐
        index.audioPlay();

        //
    },

    //得到用户的信息
    getUser: function () {
        $.ajax({
            url: common.URL.getUserURL(),
            type: "get",
            success: function (result) {

                if (result && result['code'] == 0) {

                    //如果登陆了，那么将注册和登陆按钮隐藏掉
                    $("#registerLi").hide();
                    $("#loginLi").hide();


                    //为id赋值(很多地方可能都会用到)
                    $("#userId").val(result['data'].id);

                    //左侧导航条的用户名
                    $("#userNickName").html(result['data'].userNickName);

                    //如果存在，那么初始化“完善个人信息”表单的数据(左侧导航条)
                    if (result['data'].email != null && result['data'].email != "") {
                        $("#userEmail").html(result['data'].email);
                    }
                    if (result['data'].headPortrait != null && result['data'].headPortrait != "") {
                        $("#slide-out-headPortrait").attr("src", file_path + result['data'].headPortrait);
                    }
                    //播放音乐
                    audio.play();


                } else {
                    //如果没有登陆，将日志和个人中心、音乐模块的按钮隐藏掉
                    $("#journalLi").hide();
                    $("#personalLi").hide();

                    $("#musicLiLeft").hide();
                    $("#musicLiPause").hide();
                    $("#musicLiRight").hide();


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