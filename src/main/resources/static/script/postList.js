var manageJournal = {


    URL: {
        deleteJournalURL: function (journalId) {
            return path + "/journal/" + journalId;
        }
    },
    init: function () {

        //侧边导航条
        $("#button-collapse").sideNav();


        //获取用户
        manageJournal.getUser();

        //退出用户
        $("#logout").click(function () {
            common.logOutUser();
        });

        //根据内容选择器，选择删除超链接
        $("a:contains('删除')").click(function () {
            var journalId = $(this).attr("journalId");
            //给用户判断是否确定要删除
            sweetAlert({
                    title: "确定删除吗？",
                    text: "你将无法恢复该文章！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定删除！",
                    closeOnConfirm: false
                },
                //如果点击确定，那么就使用ajax去请求删除
                function () {
                    manageJournal.deleteJournal(journalId);
                });

        });
    },

    //得到用户
    getUser: function () {
        $.ajax({
            url: common.URL.getUserURL(),
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
                }
            },
            error: function () {
                Error.displayError(result);
            }
        });
    },

    //删除文章
    deleteJournal: function (journalId) {
        $.ajax({
            url: manageJournal.URL.deleteJournalURL(journalId),
            type: "DELETE",
            success: function (result) {
                if (result && result['code'] == 0) {
                    //成功了，就刷新页面。
                    window.location.reload();
                } else {
                    console.log(result);
                }
            },
            error: function () {
                Error.displayError(result);
            }
        });
    }


};