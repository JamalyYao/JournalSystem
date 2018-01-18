var journal = {


    URL: {
        getUserURL: function () {
            return path + "/user";
        },
        logOutUserURL: function () {
            return path + "/session";
        }
    },
    init: function () {
        journal.getUser();

        $("#logout").click(function () {
            journal.logOutUser();
        });

    },

    //得到用户
    getUser: function () {
        $.ajax({
            url: journal.URL.getUserURL(),
            type: "get",
            success: function (result) {

                if (result && result['code'] == 0) {

                    //大字版显示
                    $("#userNickName-Head").html(result['data'].userNickName);
                    $("#userEmail-Head").html(result['data'].email);



                    //如果登陆了，那么将注册和登陆按钮隐藏掉
                    $("#registerLi").hide();
                    $("#loginLi").hide();


                    //为id赋值(很多地方可能都会用到)
                    $("#userId").val(result['data'].id);

                    //如果存在，那么初始化“完善个人信息”表单的数据
                    $("#userNickName").html(result['data'].userNickName);
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

                    console.log(result)
                }
            },
            error: function () {
                Error.displayError(result);
            }
        });
    },
    //退出用户
    logOutUser: function () {
        $.ajax({
            url: journal.URL.logOutUserURL(),
            type: "delete",
            success: function (result) {
                if (result && result['code'] == 0) {

                    //退出成功返回首页
                    window.location.href = '/index.html';


                } else {
                    console.log(result)
                }
            },
            error: function () {
                Error.displayError(result);
            }
        });


    }


};