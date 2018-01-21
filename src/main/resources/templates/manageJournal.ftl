<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <!--Materialize -->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
    <!--Let browser know website is optimized for mobile-->
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
            <li id="journalLi"><a class="#000000 black-text" href="${request.contextPath}/blogs">日志</a></li>
            <li id="personalLi"><a class="#000000 black-text" id="button-collapse" data-activates="slide-out"
                                   href="#">个人中心</a>
            </li>
        </ul>
    </div>
</nav>


<!-- Page Layout here -->
<div class="container">
    <div class="row section">


        <!--左侧管理导航-->
        <div class="col s3">
            <ul class="collection">
                <li class="collection-item avatar ">
                    <i class="material-icons circle  pink accent-1">folder</i>
                    <a href=""><span class="title">文章管理</span></a>
                </li>

                <li class="collection-item avatar">
                    <i class="material-icons circle pink accent-1">grade</i>
                    <a href=""><span class="title">标签管理</span></a>
                </li>
            </ul>
        </div>

        <!--右侧数据-->
        <div class="col s9">
             <#list blogsContent as blog >
                 <ul class="collection with-header">
                     <li class="collection-header"><h5><a target="_blank"
                                                          href="${request.contextPath}/blogs/:${blog.blogId}"
                                                          class="pink-text text-accent-1">${blog.title}</a></h5>
                     </li>
                     <li class="collection-item">
                         <div class="truncate">
                             <span class="#bdbdbd grey-text lighten-1">发表时间${blog.createTime}</span>

                             <a
                                     target="_blank" href="${request.contextPath}/blogs/:${blog.blogId}"
                             class="secondary-content pink-text text-accent-1">删除&nbsp;&nbsp;&nbsp;&nbsp;</a>

                             <a
                                     target="_blank" href="${request.contextPath}/blogs/:${blog.blogId}"
                                     class="secondary-content pink-text text-accent-1">编辑&nbsp;&nbsp;&nbsp;&nbsp;</a>

                         <a
                         target="_blank" href="${request.contextPath}/blogs/:${blog.blogId}"
                         class="secondary-content pink-text text-accent-1">查看全文&nbsp;&nbsp;&nbsp;&nbsp;</a>
                         </div>
                     </li>
                 </ul>
             </#list>

            <!--分页-->
            <ul class="pagination">

                <!--在当前页数大于1，才显示上一页按钮-->
                <#if (returnCurrentPage>1) >
                    <li><a href="${request.contextPath}/postlist/${returnCurrentPage-1}"><i class="material-icons">chevron_left</i></a></li>
                <#else>
                </#if>


                <!--循环遍历总页数，如果页面处在当前页上，那么突出显示-->
                <#list 1.. totalPages as page >
                    <li <#if page == returnCurrentPage> class="active"<#else> class=""</#if>><a href="${request.contextPath}/postlist/${page}">${page}</a>
                    </li>
                </#list>


                <!--在当前页数小于总页数，才显示下一页按钮-->
                <#if (returnCurrentPage < totalPages) >
                     <li><a href="${request.contextPath}/postlist/${returnCurrentPage+1}"><i class="material-icons">chevron_right</i></a></li>
                <#else>
                </#if>


                <li><i class="material-icons pink-text text-accent-1 ">grade</i>总共${totalElements}条记录</li>


            </ul>



        </div>

    </div>
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>

<!--jqueryCookie -->
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="${request.contextPath}/script/commonScript.js"></script>


</body>
</html>