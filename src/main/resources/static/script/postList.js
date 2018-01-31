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
        common.getUser();

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