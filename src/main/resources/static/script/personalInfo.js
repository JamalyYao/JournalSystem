var personalInfo = {


    URL: {
        uploadHeadPortaitURL: function () {

            return path + "/user"


        }
    },
    init: function () {

    },

    uploadHeadPortait: function () {
        var opt = {
            //重新指定form的action的值
            url: personalInfo.URL.uploadHeadPortaitURL(),
            type: "put",
            dateType: "json",
            success: function (result) {


            },
            error: function () {
                Error.displayError(result);
            }
        };
        $("#personalInfo").ajaxSubmit(opt);
    }


};
