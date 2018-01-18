var personalInfo = {

    URL: {
        uploadHeadPortraitURL: function () {
            return path + "/image"
        },
        getUserURL: function () {
            return path + "/user";
        },
        submitFormURL: function () {
            return path + "/user";
        },
        logOutUserURL:function () {

            return path + "/session";

        }
    },
    init: function () {
        //获取用户的数据
        personalInfo.getUser();


        //更换头像
        $("#fileButton").change(function () {
            personalInfo.uploadHeadPortait();
        });

        //提交表单
        $("#submitForm").click(function () {
            personalInfo.submitForm();

        });

        //退出登陆
        $("#logout").click(function () {
            personalInfo.logOutUser();
        });

    },

    //更新头像
    uploadHeadPortait: function () {
        var opt = {
            //重新指定form的action的值
            url: personalInfo.URL.uploadHeadPortraitURL(),
            type: "post",
            dateType: "json",
            success: function (result) {
                if (result && result['code'] == 0) {
                    $("#displayImg").attr("src", result['data'].realPath);
                    $("#headPortrait").val(result['data'].relativePath);

                } else {
                    Error.displayError(result);
                }
            },
            error: function () {
                Error.displayError(result);
            }
        };
        $("#personalInfo").ajaxSubmit(opt);
    },

    //获得用户的信息
    getUser: function () {
        $.ajax({
            url: personalInfo.URL.getUserURL(),
            type: "get",
            success: function (result) {
                console.log(result);
                if (result && result['code'] == 0) {

                    //设置导航栏和表单的初始值
                    $("#userNickName").html(result['data'].userNickName);
                    $("#userId").val(result['data'].id);

                    if (result['data'].headPortrait != null && result['data'].headPortrait != "") {
                        $("#slide-out-headPortrait").attr("src", file_path + result['data'].headPortrait);

                        $("#displayImg").attr("src", file_path + result['data'].headPortrait);
                        $("#headPortrait").attr("src", result['data'].headPortrait);

                    }
                    if (result['data'].email != null && result['data'].email != "") {
                        $("#userEmail").html(result['data'].email);

                        $("#email").focus();

                        $("#email").val(result['data'].email);
                    }

                    if (result['data'].email != null && result['data'].email != "") {
                        $("#userEmail").html(result['data'].email);
                    }
                } else {
                    console.log(result)
                    //Error.displayError(result);
                }
            },
            error: function () {
                Error.displayError(result);
            }
        });
    },

    //提交表单
    submitForm: function () {
        $.ajax({
            url: personalInfo.URL.submitFormURL(),
            type: "put",
            data: $("#personalInfo").serialize(),
            success: function (result) {

                console.log(result);
                if (result && result['code'] == 0) {
                    //5秒后跳转到登陆页面
                    sweetAlert({
                        title: "更新成功！",
                        text: "1秒后跳转到首页！",
                        timer: 1000,
                        type: "success",
                        showConfirmButton: false
                    });
                    setTimeout(function () {
                        window.location.href = '/index.html';
                    }, 1000);

                } else {
                    Error.displayError(result);
                }
            },
            error: function () {
                sweetAlert({
                    title: "系统错误了！",
                    text: "请联系管理员！",
                    type: "error",
                    showConfirmButton: false
                });
            }
        });

    },

    //退出登陆
    logOutUser: function () {
        $.ajax({
            url: personalInfo.URL.logOutUserURL(),
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
