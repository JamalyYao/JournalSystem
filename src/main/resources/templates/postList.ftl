
<!--管理文章页面-->
<html>
<head>

    <!--Materialize CSS -->
    <link href="https://cdn.bootcss.com/material-design-icons/3.0.1/iconfont/material-icons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
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


<!-- Page Layout here -->
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
        <div class="col s9">
            <#if blogsContent?? && (blogsContent?size > 0)>
             <#list blogsContent as journal >
                 <ul class="collection with-header">
                     <li class="collection-header"><h5><a target="_blank"
                                                          href="${request.contextPath}/journals/:${journal.journalId}"
                                                          class="pink-text text-accent-1">${journal.title}</a></h5>
                     </li>
                     <li class="collection-item">
                         <div class="truncate">
                             <span class="#bdbdbd grey-text lighten-1"><i class=" material-icons">query_builder</i>${journal.createTime}</span><span class="#bdbdbd grey-text lighten-1">&nbsp;&nbsp;&nbsp;<i class=" material-icons">grade</i><#list journal.tagList as tag>&nbsp;&nbsp;&nbsp;${tag.tagName}</#list></span>

                             <a journalId="${journal.journalId}" href="javascript:;"

                                class="secondary-content pink-text text-accent-1">删除&nbsp;&nbsp;&nbsp;&nbsp;

                             </a>

                             <a
                                     target="_self" href="${request.contextPath}/postEdit/${journal.journalId}"
                                     class="secondary-content pink-text text-accent-1">编辑&nbsp;&nbsp;&nbsp;&nbsp;</a>

                         <a
                         target="_blank" href="${request.contextPath}/journals/:${journal.journalId}"
                         class="secondary-content pink-text text-accent-1">查看全文&nbsp;&nbsp;&nbsp;&nbsp;</a>
                         </div>
                     </li>
                 </ul>
             </#list>
            <#else>
               <h3>您还没有编写过任何的文章。</h3>
            </#if>


            <!--只有大于0时，才显示分页-->
            <#if (totalElements>0) >

            <!--分页-->
            <ul class="pagination">
                <!--在当前页数大于1，才显示上一页按钮-->
                <#if (returnCurrentPage>1) >
                    <li><a href="${request.contextPath}/postList/${returnCurrentPage-1}"><i class="material-icons">chevron_left</i></a></li>
                <#else>
                </#if>

                <!--循环遍历总页数，如果页面处在当前页上，那么突出显示-->
                <#list 1.. totalPages as page >
                    <li <#if page == returnCurrentPage> class="active"<#else> class=""</#if>><a href="${request.contextPath}/postList/${page}">${page}</a>
                    </li>
                </#list>


                <!--在当前页数小于总页数，才显示下一页按钮-->
                <#if (returnCurrentPage < totalPages) >
                     <li><a href="${request.contextPath}/postList/${returnCurrentPage+1}"><i class="material-icons">chevron_right</i></a></li>
                <#else>
                </#if>


                <li class="right"><i class="material-icons pink-text text-accent-1 ">grade</i>&nbsp;总共${totalElements}条记录</li>
            </ul>
            <#else>

            </#if>
        </div>
    </div>
</div>

<!--jQuery.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>


<#--通用和页面JS-->
<script src="${request.contextPath}/script/commonScript.js"></script>
<script src="${request.contextPath}/script/postList.js"></script>

<!--弹出框-->
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet">

<script>

    $(function () {
        manageJournal.init();
    });


</script>


</body>
</html>