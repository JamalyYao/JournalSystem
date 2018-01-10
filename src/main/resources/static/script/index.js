var index = {


    URL: {

        getUserURL: function () {
            return path + "/user";
        }

    },
    init: function () {
        index.getUser();
    },


    //得到用户
    getUser: function () {
        $.ajax({
            url: index.URL.getUserURL(),
            type: "get",
            success: function (result) {
                console.log(result);
                if (result && result['code'] == 0) {
                    $("#registerLi").hide();
                    $("#loginLi").hide();
                    $("#userNickName").html(result['data'].userNickName);

                    if (result['data'].email != null && result['data'].email != "") {
                        $("#userEmail").html(result['data'].email);
                    }

                    if (result['data'].headPortrait != null && result['data'].headPortrait != "") {
                        $("#slide-out-headPortrait").attr("src", file_path + result['data'].headPortrait);
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
    }


};