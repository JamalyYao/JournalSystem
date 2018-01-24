var index = {

    init: function () {
        //轮播图启动
        $('.slider').slider();

        //侧边导航条
        $("#button-collapse").sideNav();

        index.getUser();
        $("#logout").click(function () {
            common.logOutUser();
        });

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
                } else {
                    //如果没有登陆，将日志和个人中心的按钮隐藏掉
                    $("#journalLi").hide();
                    $("#personalLi").hide();

                }
            },
            error: function () {
                Error.displayError(result);
            }
        });
    }


};