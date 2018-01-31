var index = {

    init: function () {
        //轮播图启动
        $('.slider').slider();

        //侧边导航条
        $("#button-collapse").sideNav();


        //获取用户的信息(通用)
        common.getUser();


        $("#logout").click(function () {
            common.logOutUser();
        });
    }


};