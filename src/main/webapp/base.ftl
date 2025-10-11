<html lang="en">

<head>
    <meta charset="UTF-8">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title><@title></@title></title>
</head>

<body>
<div id="header">
    <h3>Header 12345</h3>
    <a href="/logout">Выйти</a>
</div>

<div id="content">
    <div class="content">
        <@content></@content>
    </div>
</div>
<div class="rec_user">
    Рекомендованный друг: ${rec_user!"а неавторизованным нет рекомендованных друзей"} (случайный логин)
</div>
</body>

</html>