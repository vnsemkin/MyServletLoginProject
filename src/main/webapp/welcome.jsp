<%--@elvariable id="name" type="text"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Welcome</title>
</head>
<h1>Welcome to system "${name}"</h1>
<body>
<a href="${pageContext.request.contextPath}/index" style="text-decoration: none">
    <button type="button" class="btn btn-success">Home</button>
</a>
<button type="button" class="btn btn-success" onclick="location.href='http://localhost:8080/userslist'">Вывести список
    пользователей
</button>
<button type="button" class="btn btn-success" onclick="location.href='http://localhost:8080/logout'">LogOut</button>
</body>
</html>
