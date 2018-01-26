<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ozc Journal｜登陆</title>

    <!--Jquery校验-->
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

    <!--Materialize -->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>

    <!--弹出框-->
    <script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
    <link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet">

    <!--页面样式-->
    <link rel="stylesheet" href="css/register&login.css">

    <!--页面和通用的JS-->
    <script src="script/commonScript.js"></script>
    <script src="script/login.js"></script>
    <script>
        $(function () {
            login.init();
        });
    </script>
</head>

<body>
<div id="main"></div>


<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br><br>
        <!--logo图-->
        <div class="center-align"><img src="imgs/logo-big.png" alt="" >
        </div>
        <!--表单-->
        <div class="row ">
            <form class="col s8 offset-s4" id="loginForm" action="/session" method="post">
                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix pink-text text-accent-1">phone</i>
                        <input id="mobileNo" name="mobileNo" type="tel" class="validate" required length="11">
                        <label for="mobileNo" >手机号码</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix pink-text text-accent-1">lock</i>
                        <input id="password" name="password" type="password" class="validate" required length="18">
                        <label for="password" >密码</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s4">
                        <i class="material-icons prefix pink-text text-accent-1 ">mode_edit</i>
                        <input id="inputCaptcha" name="inputCaptcha" type="tel" length="4" minlength="4" maxlength="4" required>
                        <label for="inputCaptcha">验证码</label>
                    </div>

                    <div class="input-field col s2">
                        <img src="/user/gifCode" id="captcha">
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s6">
                        <div class="input-field col 11 right">
                            <a href="" class="pink-text text-accent-1">忘记密码</a>
                        </div>
                        <input type="checkbox" class="filled-in pink accent-1" checked="checked" name="rememberMe" id="filled-in-box" value="rememberMe"  />
                        <label for="filled-in-box">记住我</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s3">
                        <a href="/register.html" class="pink-text text-accent-1">返回注册页面</a>
                    </div>
                    <div class="input-field col 6">
                        <button class="btn waves-effect waves-light pink accent-1" type="submit" id="submitForm" name="action">立即登陆
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <br><br>
    </div>
</div>

</body>
</html>