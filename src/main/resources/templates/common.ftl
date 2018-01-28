<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ozc Journal｜首页</title>
    <!--Materialize CSS-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>

<!--导航条-->
<nav class="#ffffff white" role="navigation">
    <div class="nav-wrapper container">
        <img class="brand-logo" src="${request.contextPath}/imgs/logo-small.png"/>
        <ul id="nav-mobile" class="right hide-on-med-and-down">

            <li id="musicLiLeft"><a href="javascript:;" id="btn-pre"><i
                    class="material-icons black-text">chevron_left</i></a>
                &nbsp; &nbsp; &nbsp; &nbsp;
            </li>
            <li id="musicLiPause"><a href="javascript:;" id="btn-play"><i
                    class="material-icons black-text">pause</i></a>
                &nbsp; &nbsp; &nbsp; &nbsp;
            </li>
            <li id="musicLiRight"><a href="javascript:;" id="btn-next"><i class="material-icons black-text">chevron_right</i></a>
                &nbsp; &nbsp; &nbsp; &nbsp;
            </li>
            <li id="registerLi"><a class="#000000 black-text" href="${request.contextPath}/register.html">注册</a></li>
            <li id="loginLi"><a class="#000000 black-text" href="${request.contextPath}/login.html">登陆</a></li>
            <li id="journalLi"><a class="#000000 black-text" href="${request.contextPath}/journals">日志</a></li>
            <li id="personalLi"><a class="#000000 black-text" id="button-collapse" data-activates="slide-out"
                                   href="#">个人中心</a>
            </li>
        </ul>
    </div>
</nav>

<replace id="main">

</replace>



<!--音乐播放-->
<audio id="audio" src="${request.contextPath}/1.MP3">
</audio>


<!--Materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>

<!--公用页面和本地JS-->
<script src="${request.contextPath}/script/commonScript.js"></script>
<script src="${request.contextPath}/script/index.js"></script>


<!--涉及到的相关页面的JS-->
<script src="${request.contextPath}/script/personalInfo.js"></script>

<!--jquery AJAX提交表单js -->
<script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.min.js"></script>

<!--弹出框-->
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet">


<!--pjax-->
<script src="https://cdn.bootcss.com/jquery.pjax/2.0.1/jquery.pjax.min.js"></script>
<script>
    $(function () {
        common.init();
    });
    //$.pjax({ url: path +"/testIndex.html", container: '#main' });
    $(document).pjax("#personalInfo", '#main',{fragment:"#main"});
</script>

