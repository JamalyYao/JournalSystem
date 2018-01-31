var music = {

    URL: {
        uploadMusicURL: function () {
            return path + "/musicFile"
        },
        updateUserInfoURL: function () {
            return path + "/user";
        },
        selectUserMusicURL: function () {
            return path + "/user/music";
        },
        deleteMusicURL: function (musicId) {

            return path + "/music/" + musicId;
        }
    },

    init: function () {
        //获取用户的信息(通用)
        common.getUser();


        music.selectUserMusic();

        //更换头像
        $("#input-file-now").change(function () {
            music.uploadMusic();
        });

        //侧边导航条
        $("#button-collapse").sideNav();

        //提交表单
        $("#submitForm").click(function () {
            music.updateUserInfo();
        });

        //退出登陆
        $("#logout").click(function () {
            common.logOutUser();
        });


        //根据内容选择器，选择删除超链接，live方法在jquery更新到1.9后被on方法替代了。
        $(document).on('click',"a:contains('删除')", function () {
            var musicId = $(this).attr("musicId");
            //给用户判断是否确定要删除
            sweetAlert({
                    title: "确定删除吗？",
                    text: "你将无法恢复该歌曲！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定删除！",
                    closeOnConfirm: false
                },
                //如果点击确定，那么就使用ajax去请求删除
                function () {
                    music.deleteMusic(musicId);
                });
        });

    },

    //删除歌曲
    deleteMusic: function (musicId) {
        $.ajax({
            url: music.URL.deleteMusicURL(musicId),
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

    },


    //获取用户下的所有歌曲
    selectUserMusic: function () {
        $.ajax({
            url: music.URL.selectUserMusicURL(),
            type: "get",
            success: function (result) {

                if (result && result['code'] == 0) {

                    for (var index in result['data']) {

                        $("#musicList").append("<tr><td>" + result['data'][index].musicName + "</td><td><a href='javascript:;' musicId='" + result['data'][index].musicId + "'>删除</a></td></tr>");

                    }
                } else {
                    Error.displayError(result);
                }
            },
            error: function () {
                Error.displayError(result);
            }
        });


    },

    //异步上传MP3
    uploadMusic: function () {
        var opt = {
            //重新指定form的action的值
            url: music.URL.uploadMusicURL(),
            type: "post",
            dateType: "json",
            success: function (result) {
                if (result && result['code'] == 0) {
                    //获取歌名
                    var filename = result['data'].originalFilename;
                    $("#musicList").append("<tr><td>" + filename + "</td><td><a href='#'>删除</a></td></tr>");

                } else {
                    Error.displayError(result);
                }
            },
            error: function () {
                Error.displayError(result);
            }
        };

        //异步提交表单
        $("#musicForm").ajaxSubmit(opt);
    }




};
