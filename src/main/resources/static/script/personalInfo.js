var personalInfo = {

    URL: {
        uploadHeadPortraitURL: function () {
            return path + "/image"
        },
        updateUserInfoURL: function () {
            return path + "/user";
        }
    },
    init: function () {
        //获取用户的数据
        var result = common.getUser();

        //补充表单信息
        if (result['data'].headPortrait != null && result['data'].headPortrait != "") {
            $("#displayImg").attr("src", file_path + result['data'].headPortrait);
            $("#headPortrait").attr("src", result['data'].headPortrait);
        }
        if (result['data'].email != null && result['data'].email != "") {
            $("#email").focus();
            $("#email").val(result['data'].email);
        }

        //更换头像
        $("#fileButton").change(function () {
            personalInfo.uploadHeadPortait();
        });

        //侧边导航条
        $("#button-collapse").sideNav();

        //提交表单
        $("#submitForm").click(function () {
            personalInfo.updateUserInfo();
        });

        //退出登陆
        $("#logout").click(function () {
            common.logOutUser();
        });

    },

    //异步更新头像
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
        //异步提交表单
        $("#personalInfoForm").ajaxSubmit(opt);
    },


    //提交表单
    updateUserInfo: function () {
        $.ajax({
            url: personalInfo.URL.updateUserInfoURL(),
            type: "put",
            data: $("#personalInfoForm").serialize(),
            success: function (result) {

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
                        window.location.href = common.URL.backIndexURL();
                    }, 1000);

                } else {
                    Error.displayError(result);
                }
            },
            error: function () {
                Error.displayError(result);
            }
        });

    }



};
