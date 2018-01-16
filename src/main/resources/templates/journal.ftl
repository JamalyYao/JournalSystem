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
        background-image: url(../imgs/bg-big.png);
        background-repeat: repeat;
    }
</style>


<body>

<!--导航条-->
<nav class="#ffffff white" role="navigation">
    <div class="nav-wrapper container">
        <img class="brand-logo" src="imgs/logo-small.png"/>
        <ul userId="nav-mobile" class="right hide-on-med-and-down">
            <li userId="registerLi"><a class="#000000 black-text" href="/register.html">注册</a></li>
            <li userId="loginLi"><a class="#000000 black-text" href="/login.html">登陆</a></li>
            <li userId="journalLi"><a class="#000000 black-text" href="/journal.html">日志</a></li>
            <li userId="personalLi"><a class="#000000 black-text" userId="button-collapse" data-activates="slide-out"
                                       href="#">个人中心</a>
            </li>
        </ul>
    </div>
</nav>
<!--左侧导航条-->
<ul userId="slide-out" class="side-nav ">
    <li>
        <div class="userView">
            <div class="background">
                <img src="imgs/bg-small.png">
            </div>
            <img userId="slide-out-headPortrait" class="materialboxed" src="imgs/headPortrait.png"/><br>
            <span class="black-text " userId="userNickName">张三</span><br>
            <span userId="userEmail" class="black-text email">未填邮箱地址</span>
        </div>
    </li>
    <li><a class="waves-effect" href="/personalInfo.html"><i class="material-icons pink-text text-accent-1">perm_identity</i>完善个人信息</a>
    </li>
    <li>
        <div class="divider"></div>
    </li>
    <li><a class="subheader">用户操作</a></li>

    <li><a class="waves-effect" href="/index.html"><i class="material-icons prefix  pink-text text-accent-1">label</i>回到首页</a>
    </li>

    <li><a class="waves-effect" href="/editJournal.html"><i class="material-icons prefix pink-text text-accent-1">mode_edit</i>编写新的日志</a>
    </li>

    <li><a class="waves-effect" href="/journal.html"><i
            class="material-icons pink-text text-accent-1">search</i>查看日志</a></li>
    <li userId="logout"><a class="waves-effect"><i class="material-icons pink-text text-accent-1">settings_power</i>退出登陆</a>
    </li>
</ul>


<div class="container">

    <!--个人信息-->
    <div class="row section">
        <h3>钟福成</h3>
        <span>403686131@qq.com</span>
    </div>
    <div class="divider"></div>

    <!--日志操作导航栏-->
    <div class="row section">
        <div class="col s12">
            <ul class="tabs .tabs-fixed-width">
                <li class="tab col s3"><a class="active" href="#test1"><i class="material-icons">search</i>查看日志</a></li>
                <li class="tab col s3"><a target="_self" href="/editJournal.html"><i
                        class="material-icons">mode_edit</i>编写新日志</a></li>
                <li class="tab col s3 "><a href="#test3"><i class="material-icons ">settings</i>管理日志</a></li>
                <li class="tab col s3"><a target="_self" href="/index.html"><i class="material-icons ">label</i>返回首页</a>
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
                    <p>2017年9月</p>
                    <p>2017年8月</p>
                    <p>2017年7月</p>
                    <p>2017年6月</p>
                    <div class="divider"></div>
                </div>
            </div>

            <div class="card pink accent-1">
                <div class="card-content white-text">
                    <span class="card-title"><i class="material-icons">contacts</i>文章标签 </span>
                    <p>日志</p>
                    <p>喜欢</p>
                    <p>女朋友</p>
                    <p>男朋友</p>
                    <div class="divider"></div>
                </div>
            </div>

            <div class="card pink accent-1">
                <div class="card-content white-text">
                    <span class="card-title"> <i class="material-icons">search</i>搜索日志 </span>
                    <p>日志</p>
                    <p>喜欢</p>
                    <p>女朋友</p>
                    <p>男朋友</p>
                    <div class="divider"></div>
                </div>
            </div>
        </div>

        <!--右侧文章内容-->
        <div class="col s9">
             <#list blogs as blog>

                <ul class="collection with-header">
                    <li class="collection-header"><h4><a href="" class="pink-text text-accent-1">${blog.title}</a></h4></li>
                    <li class="collection-item">
                        <div class="truncate">
                            <p>${blog.content}</p>
                            <span class="#bdbdbd grey-text lighten-1">发表时间：${blog.createTime}</span><a
                                href="#!" class="secondary-content pink-text text-accent-1"><i class="material-icons ">search</i>查看全文</a>
                        </div>
                    </li>

                </ul>
             </#list>


        <#--            <ul class="collection with-header">
                        <li class="collection-header"><h4><a href="" class="pink-text text-accent-1">蔬菜</a></h4></li>
                        <li class="collection-item">
                            <div class="truncate">
                                <p>
                                    阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否</p>
                                <span class="#bdbdbd grey-text lighten-1">发表时间：2018年1月12日14:56:40</span><a
                                    href="#!" class="secondary-content pink-text text-accent-1"><i class="material-icons ">search</i>查看全文</a>
                            </div>
                        </li>
                    </ul>
                    <ul class="collection with-header">
                        <li class="collection-header"><h4><a href="" class="pink-text text-accent-1">蔬菜</a></h4></li>
                        <li class="collection-item">
                            <div class="truncate">
                                <p>
                                    阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否阿斯顿发的双方都双阿斯顿发的爽肤水打发的双方都双方都双方都是否但是方都双方都是否</p>
                                <span class="#bdbdbd grey-text lighten-1">发表时间：2018年1月12日14:56:40</span><a
                                    href="#!" class="secondary-content pink-text text-accent-1"><i class="material-icons ">search</i>查看全文</a>
                            </div>
                        </li>
                    </ul>-->

            <!--分页组件-->
            <ul class="pagination">
                <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
                <li class="active"><a href="#!">1</a></li>
                <li class="waves-effect"><a href="#!">2</a></li>
                <li class="waves-effect"><a href="#!">3</a></li>
                <li class="waves-effect"><a href="#!">4</a></li>
                <li class="waves-effect"><a href="#!">5</a></li>
                <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
            </ul>

        </div>

    </div>
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>

<script src="script/commonScript.js"></script>

<script src="script/journal.js"></script>


<script>
    $(function () {
        //侧边导航条
        $("#button-collapse").sideNav();


        journal.init();
    });
</script>


</body>
</html>