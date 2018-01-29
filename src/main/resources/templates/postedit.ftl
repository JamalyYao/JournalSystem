<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ozc Journal｜编辑日志</title>

    <!--Materialize CSS-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


</head>
<style>
    body {
        background-image: url(${request.contextPath}/imgs/bg-big.png);
        background-repeat: repeat;
    }
</style>


<body>

<!--导航条-->
<nav class="#ffffff white" role="navigation">
    <div class="nav-wrapper container">
        <img class="brand-logo" src="${request.contextPath}/imgs/logo-small.png"/>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li id="registerLi"><a class="#000000 black-text" href="${request.contextPath}/register.html">注册</a></li>
            <li id="loginLi"><a class="#000000 black-text" href="${request.contextPath}/login.html">登陆</a></li>
            <li id="journalLi"><a class="#000000 black-text" href="${request.contextPath}/journals">日志</a></li>
            <li id="personalLi"><a class="#000000 black-text" id="button-collapse" data-activates="slide-out"
                                   href="#">个人中心</a>
            </li>
        </ul>
    </div>
</nav>

<!--左侧导航条-->
<ul id="slide-out" class="side-nav ">
    <li>
        <div class="userView">
            <div class="background">
                <img src="${request.contextPath}/imgs/bg-small.png">
            </div>
            <img id="slide-out-headPortrait" class="materialboxed"
                 src="${request.contextPath}/imgs/headPortrait.png"/><br>
            <span class="black-text " id="userNickName">张三</span><br>
            <span id="userEmail" class="black-text email">未填邮箱地址</span>
        </div>
    </li>
    <li><a class="waves-effect" href="${request.contextPath}/personalInfo.html"><i
            class="material-icons pink-text text-accent-1">perm_identity</i>完善个人信息</a>
    </li>

    <li><a class="waves-effect" href="${request.contextPath}/music.html"><i
            class="material-icons pink-text text-accent-1">insert_chart</i>设置个人音乐</a>
    </li>
    <li>
        <div class="divider"></div>
    </li>
    <li><a class="subheader">用户操作</a></li>

    <li><a class="waves-effect" href="${request.contextPath}/index.html"><i
            class="material-icons prefix  pink-text text-accent-1">label</i>回到首页</a>
    </li>

    <li><a class="waves-effect" href="${request.contextPath}/editJournal.html"><i
            class="material-icons prefix pink-text text-accent-1">mode_edit</i>编写新的日志</a>
    </li>
    <li><a class="waves-effect" href="${request.contextPath}/journals"><i
            class="material-icons pink-text text-accent-1">search</i>查看日志</a></li>
    <li id="logout"><a class="waves-effect"><i class="material-icons pink-text text-accent-1">settings_power</i>退出登陆</a>
    </li>
</ul>

<!--表单内容-->
<div class="container #ffffff white   ">
    <form id="journalContentForm">
        <!--标题-->
        <div class="input-field  #eeeeee grey lighten-3  ">
            <i class="material-icons prefix pink-text text-accent-1">mode_edit</i>
            <input id="title" name="title" type="text" value="${journal.title}"/>
            <label for="title">请输入文章标题</label>
        </div>

        <!--编辑器-->
        <div id="editor" class="section">
        ${journal.content}
        </div>


        <input type="hidden" id="journalId" value="${journal.journalId}">

        <!--标签-->
        <div class="#eeeeee grey lighten-3 section ">
            <i class="material-icons prefix pink-text text-accent-1">mode_edit</i>文章标签：
            <#assign name=""/>
            <div class="chips chips-placeholder">
                <#list journal.tagList as tag>
                     <#assign name= name+"${tag.tagName},"/>
                </#list>
            </div>
        </div>
        <input type="hidden" name="tags" value="${name}">
        <div class="row section ">
            <div class="col s6">
                <button id="submitButton" class="btn waves-effect waves-light pink accent-1 " type="button">
                    确认编辑
                    <i class="material-icons right">send</i>
                </button>

            </div>

            <div class="col s6">
                <button class="btn waves-effect waves-light pink accent-1" type="button" id="backJournal">返回
                    <i class="material-icons right">label_outline</i>
                </button>
            </div>

        </div>
    </form>

</div>

<!-- jQuery js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>


<#--通用和页面JS-->
<script src="${request.contextPath}/script/commonScript.js"></script>
<script src="${request.contextPath}/script/editJournal.js"></script>


<!--富文本编辑器-->
<script src="https://cdn.bootcss.com/wangEditor/10.0.13/wangEditor.min.js"></script>

<script>
    $(function () {
        //初始化该页面的JS
        editJournal.init("${name}");
    });

</script>

</body>
</html>