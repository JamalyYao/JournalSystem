<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ozc Journal｜文章详情页</title>


    <!--Materialize  CSS-->

    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!--样式-->
    <link rel="stylesheet" href="${request.contextPath}/css/register&login.css">
</head>


<body>

<div class="container">
    <div class="divider"></div>
    <h2>${journal.title}</h2>
    <br>
    <div class="divider" style="border: 1px solid #cccccc;"></div>

    <div class="section">
    ${journal.content}
    </div>
</div>


<!--jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>


</body>
</html>