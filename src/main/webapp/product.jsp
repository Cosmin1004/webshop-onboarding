<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="WEB-INF/category.tld" prefix="yc" %>

<html>

<head>
    <title>young.culture</title>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="resources/js/yc.js" type="text/javascript"></script>

    <style> <%@include file="/resources/css/product.css"%> </style>
    <style> <%@include file="/resources/css/login.css"%> </style>
    <style> <%@include file="/resources/css/footer.css"%> </style>
    <style> <%@include file="/resources/css/cart.css"%> </style>
    <style> <%@include file="/resources/css/myOrders.css"%> </style>
</head>

<body onload="showErrorDialog(); showCartOrMyOrders();">

<jsp:include page="header.jsp"/>

<div id="wrapper">
    <div class="container" style="padding-bottom: 100px;">
        <div class="success-msg" id="orderSuccess" hidden></div>
        <c:if test="${fn:length(products) == 0}">
            <img class="image" alt="No product found..."
                 src="resources/images/noProducts.png">
        </c:if>
        <c:if test="${fn:length(products) > 0}">
            <div class="table-responsive">
                <table class="table table-hover table-sm table-striped">
                    <c:choose>
                        <c:when test="${rendered == true}">
                            <col width='20%'>
                            <col width='10%'>
                            <col width='55%'>
                            <col width='15%'>
                        </c:when>
                        <c:otherwise>
                            <col width='25%'>
                            <col width='60%'>
                            <col width='15%'>
                        </c:otherwise>
                    </c:choose>
                    <thead class="thead">
                    <tr>
                        <th scope="col">Product</th>
                        <c:if test="${rendered == true}">
                            <th scope="col">Category</th>
                        </c:if>
                        <th scope="col">Description</th>
                        <th scope="col">Price</th>
                    </tr>
                    </thead>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td><b>${product.name}</b></td>
                            <c:if test="${rendered == true}">
                                <td><yc:formatCategory category="${product.category.name}"/></td>
                            </c:if>
                            <td>${product.description}</td>
                            <td style="color: red">${product.price} Lei
                                <div style="float: right">
                                    <form method="post" action="${pageContext.request.contextPath}/order">
                                        <button type="submit" value="${product.name}" name="productName"
                                                class="btn btn-success" id="addProductToCart"
                                                onclick="function afterAddToCart(name) {
                                                        alert('Product ' + name + ' has been added to your cart!');
                                                        }
                                                        afterAddToCart('${product.name}')">
                                            <img src="resources/images/addToCart.png" title="Add to cart" width="25px"
                                                 height="25px">
                                        </button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </div>
    <jsp:include page="/footer.jsp"/>
</div>

</body>

</html>