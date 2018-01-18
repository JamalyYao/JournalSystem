<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ozc Journal｜日志</title>

    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <!--Materialize -->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
    <!--Let browser know website is optimized for mobile-->
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
            <li id="journalLi"><a class="#000000 black-text" href="${request.contextPath}/blogs">日志</a></li>
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

    <li><a class="waves-effect" href="${request.contextPath}/blogs"><i
            class="material-icons pink-text text-accent-1">search</i>查看日志</a></li>
    <li id="logout"><a class="waves-effect"><i class="material-icons pink-text text-accent-1">settings_power</i>退出登陆</a>
    </li>
</ul>


<div class="container">

    <!--个人信息-->
    <div class="row section">
        <h3 id="userNickName-Head">XXX</h3>
        <span id="userEmail-Head">未填邮箱地址</span>
    </div>
    <div class="divider"></div>

    <!--日志操作导航栏-->
    <div class="row section">
        <div class="col s12">
            <ul class="tabs .tabs-fixed-width">
                <li class="tab col s3"><a class="active" target="_self" href="${request.contextPath}/blogs"><i
                        class="material-icons">search</i>查看日志</a></li>
                <li class="tab col s3"><a target="_self" href="${request.contextPath}/editJournal.html"><i
                        class="material-icons">mode_edit</i>编写新日志</a></li>
                <li class="tab col s3 "><a href="#test3"><i class="material-icons ">settings</i>管理日志</a></li>
                <li class="tab col s3"><a target="_self" href="${request.contextPath}/index.html"><i
                        class="material-icons ">label</i>返回首页</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="row">

        <!--左侧显示模块-->
        <div class="col s3">
            <div class="card  pink accent-1">
                <div class="card-content white-text">
                    <span class="card-title"><i class="material-icons">view_list</i>文章存档 </span>
                       <#list archiveRecords as record>
                            <p><a href="/blogs/${record.year}/${record.month}"
                                  class="white-text">${record.year}年${record.month}月</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(${record.num}
                                )</p>
                       </#list>
                </div>
            </div>

            <div class="card pink accent-1 ">
            <div class="card-content white-text  ">
                <span class="card-title "><i class="material-icons">contacts</i>文章标签 </span><br>
                     <#list tags as tag >

                            <div class="chip pink lighten-2 ">
                                <a href="/blogs/${tag}" class="white-text ">${tag}</a>
                            </div>
                     </#list>
            </div>
        </div>
    </div>

    <!--右侧文章内容-->
    <div class="col s9">

           <#if blogs?? && (blogs?size > 0)>

                <#list blogs as blog>

                <ul class="collection with-header">
                    <li class="collection-header"><h5><a href="" class="pink-text text-accent-1">${blog.title}</a></h5>
                    </li>
                    <li class="collection-item">
                        <div class="truncate">
                            <p class="truncate">${blog.contentNoHTML}</p>
                            <span class="#bdbdbd grey-text lighten-1">发表时间：${blog.createTime}</span><a
                                href="#!" class="secondary-content pink-text text-accent-1"><i class="material-icons ">search</i>查看全文</a>
                        </div>
                    </li>

                </ul>
                </#list>
           <#else>
               您还没有编写过任何的文章。
           </#if>

    </div>

</div>
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>

<script src="${request.contextPath}/script/commonScript.js"></script>

<script src="${request.contextPath}/script/journal.js"></script>


<script>
    $(function () {
        //侧边导航条
        $("#button-collapse").sideNav();


        journal.init();
    });
</script>


</body>
</html>