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