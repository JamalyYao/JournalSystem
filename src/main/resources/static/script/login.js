var login = {

    //封装注册相关ajax的url
    URL: {
        submitFormURL: function () {
            return path + "/session";
        },
        changeGifCodeURL: function () {
            return path + "/user/gifCode?" + new Date().getTime();
        }

    },

    //验证表单
    validateForm: function () {
        $("#loginForm").validate({
            rules: {
                password: {
                    required: true,
                    checkPwd: true,
                },
                mobileNo: {
                    required: true,
                    checkPhone: true,
                },
                captcha: {
                    required: true,
                }

            },
            messages: {
                password: {
                    required: "*必填！",
                    rangelength: "密码长度为6到18位！！"
                },
                mobileNo: {
                    required: "*必填！",
                    rangelength: "手机号码长度为11位！",
                },
                captcha: {
                    required: "*必填！",
                },
            },
            //显示错误的信息
            errorElement: 'div',
            errorPlacement: function (error, element) {
                var placement = $(element).data('error');
                if (placement) {
                    $(placement).append(error)
                } else {
                    error.insertAfter(element);
                }
            },

            //不通过回调
            invalidHandler: function (form, validator) {
                return false;
            },

            //ajax提交表单
            submitHandler: function () {
                $.ajax({
                    url: login.URL.submitFormURL(),
                    type: "post",
                    data: $("#loginForm").serialize(),
                    success: function (result) {
                        if (result && result['code'] == 0) {
                            //5秒后跳转到登陆页面
                            sweetAlert({
                                title: "登陆成功！",
                                text: "3秒后跳转到首页！",
                                timer: 3000,
                                type: "success",
                                showConfirmButton: false
                            });
                            setTimeout(function () {
                                window.location.href = '/index.html';
                            }, 3000);

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
            onkeyup: false// 是否在敲击键盘时验证

        });

        //自定义正则表达示验证方法
        $.validator.addMethod("checkPwd", function (value, element, params) {
            var checkPwd = /^\w{6,18}$/g;
            return this.optional(element) || (checkPwd.test(value));
        }, "只允许6-18位英文字母、数字或者下画线！");

        $.validator.addMethod("checkPhone", function (value, element, params) {
            var checkPhone = /^1[3,5,7,8]\d{9}$/;
            return this.optional(element) || (checkPhone.test(value));
        }, "手机号码格式必须正确！");

    },

    //点击图片换一张验证码
    changeCode: function () {
        $("#captcha").attr("src", login.URL.changeGifCodeURL());
    },


    //页面初始化
    init: function (params) {
        login.validateForm();


        //点击图片更换验证码
        $("#captcha").click(function () {
            login.changeCode();
        });

    }

};

