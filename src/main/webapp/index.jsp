<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Welcome to Servlet Home Task</title>
</head>
<h1>To start please Log in or Signup</h1>
<body>
<%--@elvariable id="name" type=""--%>
<c:choose>
    <c:when test="${empty name}">
        <button type="submit" class="btn btn-success" onclick="location.href='/login'">Login</button>
        <button type="submit" class="btn btn-success" onclick="location.href='/signup'">Signup</button>
    </c:when>
    <c:otherwise>
        <button type="submit" class="btn btn-success" onclick="location.href='/logout'">Logout
        </button>
    </c:otherwise>
</c:choose>
</body>
</html>

