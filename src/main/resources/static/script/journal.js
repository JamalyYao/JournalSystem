var journal = {
    init: function () {

        //侧边导航条
        $("#button-collapse").sideNav();

        $("#logout").click(function () {
            common.logOutUser();
        });

        var result = common.getUser();
        //补充表单信息
        if (result && result['code'] == 0) {
            //大字版显示
            $("#userNickName-Head").html(result['data'].userNickName);
            $("#userEmail-Head").html(result['data'].email);
        }
    }


};