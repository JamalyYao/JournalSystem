<html>
<head>
    <meta charset="UTF-8">
    <title>Ozc Journal｜标签管理</title>
    <!--Materialize CSS-->
    <link href="https://cdn.bootcss.com/material-design-icons/3.0.1/iconfont/material-icons.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/materialize/0.97.8/css/materialize.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <style>
        body {
            background-image: url(${request.contextPath}/imgs/bg-big.png);
            background-repeat: repeat;
        }
    </style>
</head>

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
<ul id="slide-out" class="side-nav">
    <li>
        <div class="userView">
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


<div class="container">
    <div class="row section">
        <!--左侧管理导航-->
        <div class="col s3">
            <ul class="collection">
                <li class="collection-item avatar ">
                    <i class="material-icons circle  pink accent-1">folder</i>
                    <a href="${request.contextPath}/postList/1"><span class="title">文章管理</span></a>
                </li>

                <li class="collection-item avatar">
                    <i class="material-icons circle pink accent-1">grade</i>
                    <a href="${request.contextPath}/tags"><span class="title">标签管理</span></a>
                </li>
            </ul>
        </div>

        <!--右侧数据-->
        <div class="col s9 ">
                 <table class="bordered highlight white section" style="border: 1px solid #ccc; ">
                     <thead>
                     <tr>
                         <th data-field="id">标签名</th>
                         <th data-field="name">文章数</th>
                         <th data-field="price">操作</th>
                     </tr>
                     </thead>
                     <tbody>
                     <#list labelStatistics as label >
                         <tr>
                             <td><input type="text" class="black-text" value="${label.tagName}" style="width: 200px" disabled></td>
                             <td><a class="black-text" href="${request.contextPath}/journals/${label.tagName}">${label.blogCount}</a></td>
                             <td><a href="javascript:;">编辑</a>&nbsp;|&nbsp;<a href="javascript:;" tagName="${label.tagName}">删除</a></td>
                         </tr>
                     </#list>
                     </tbody>
                 </table>

        </div>

    </div>
</div>

<!-- jQuery-->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/materialize/0.97.8/js/materialize.min.js"></script>


<#--通用和页面JS-->
<script src="${request.contextPath}/script/commonScript.js"></script>
<script src="${request.contextPath}/script/category.js"></script>

<!--弹出框-->
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet">

<script>

    $(function () {
        category.init();
    });


</script>


</body>
</html>