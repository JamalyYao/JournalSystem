//存储标签最终的值
var tags = new Array();

var editJournal = {
    URL: {
        getUserURL: function () {
            return path + "/user";
        },
        submitFormURL: function () {
            return path + "/blog";
        }
    },
    init: function () {

        //获取用户
        editJournal.getUser();

        //侧边导航条
        $("#button-collapse").sideNav();

        //标签的相关操作(增加、删除、获取值)
        editJournal.tagOpera();

        //文本编辑器
        var E = window.wangEditor
        var editor = new E('#editor')
        editor.customConfig.uploadImgServer = path + '/upload';  // 上传图片到服务器接口
        editor.customConfig.uploadFileName = 'imgsFile'; // 参数名称
        //...文件上传如果用到其他参数再去看文档。http://www.wangeditor.com/index.html
        editor.create();


        //提交表单
        $("#submitButton").click(function () {

            //文章标题，内容，标签
            var title = $("#title").val();
            editJournal.submitForm(title, editor.txt.html(), tags.toString())
        });


    },
    tagOpera: function () {

        //添加标签
        $('.chips-placeholder').material_chip({
            placeholder: 'Enter a tag',
            secondaryPlaceholder: '按回车分隔'
        });


        $('.chips').on('chip.add', function (e, chip) {
            tags.push(chip.tag);
        });
        $('.chips').on('chip.delete', function (e, chip) {

            for (var index  in tags) {

                if (tags[index] == chip.tag) {
                    tags.splice(index, 1);
                }
            }
        });

    },

    //得到用户
    getUser: function () {
        $.ajax({
            url: editJournal.URL.getUserURL(),
            type: "get",
            success: function (result) {

                if (result && result['code'] == 0) {

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

                    //侧边导航条的头像
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


    //提交表单
    submitForm: function (title, content, tags) {
        $.ajax({
            url: editJournal.URL.submitFormURL(),
            type: "post",
            data: {
                "title": title,
                "content": content,
                "tags": tags
            },
            success: function (result) {
                if (result && result['code'] == 0) {
                    //5秒后跳转到登陆页面
                    console.log(result);

                } else {
                    console.log(result);
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


    }


};