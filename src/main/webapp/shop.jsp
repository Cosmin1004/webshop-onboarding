<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
    <title>young.culture</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style> <%@include file="/WEB-INF/css/style.css"%> </style>
</head>

<body>

<img class="center" style="margin-bottom: 5px; margin-top: 5px"
         src="./images/youngCulture.jpg" alt="YoungCulture"/>

<nav class="navbar navbar-inverse">
    <div class="center">
        <div class="btn-group">
            <c:forEach var="category" items="${categories}">
                <button type="submit" class="btn btn-primary">${category}</button>
            </c:forEach>
            <button type="submit" class="btn btn-secondary" style="margin-left: 10px">LOGIN</button>
            <button type="submit" class="btn btn-secondary">CART</button>
        </div>
    </div>
</nav>

<div>

</div>

</body>

</html>