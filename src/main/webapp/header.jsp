<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="WEB-INF/category.tld" prefix="yc" %>


<a href="${pageContext.request.contextPath}"><img class="center" style="margin-bottom: 5px; margin-top: 5px"
                                                  src="resources/images/youngCulture.jpg" alt="YoungCulture"/></a>

<!-- include modals -->
<jsp:include page="login.jsp"/>
<jsp:include page="cart.jsp"/>
<jsp:include page="myOrders.jsp"/>

<nav class="navbar navbar-inverse">
    <div>
        <div class="center" style="margin-top: 10px">
            <form action="${pageContext.request.contextPath}/product" method="get">
                <button type="submit" class="btn btn-default" name="category"
                        value="all">
                    <img src="resources/images/all.png" title="All products (${numberOfProducts})" width="20px" height="20px">
                </button>
                <div class="btn-group" role="group">
                    <c:forEach var="category" items="${categories}">
                            <button type="submit" class="btn btn-default active"
                                    name="category" value="${category}">
                                <yc:formatCategory category="${category}"/>
                            </button>
                    </c:forEach>
                </div>
            </form>
            <form action="${pageContext.request.contextPath}/cart" method="get">
                <button type="submit" class="btn btn-default active"
                        style="margin-right: 5px; float: right">
                    <img src="resources/images/cart.png" title="View the cart" width="25px" height="25px">
                </button>
                <c:if test="${cartRendered == true}">
                    <div hidden id="hiddenCart"></div>
                </c:if>
            </form>
            <c:if test="${currentSessionUser != null}">
                <form action="${pageContext.request.contextPath}/order" method="get">
                    <button type="submit" class="btn btn-default active"
                            style="margin-right: 5px; float: right">
                        <img src="resources/images/myOrders.png" title="View your orders" width="25px" height="25px">
                        <c:if test="${myOrdersRendered == true}">
                            <div hidden id="hiddenMyOrders"></div>
                        </c:if>
                    </button>
                </form>
            </c:if>
        </div>
        <span id="userSpan">
                <c:choose>
                    <c:when test="${currentSessionUser == null}">
                        <button type="button" class="btn btn-default active"
                                href="#" style="margin-bottom: 10px" onclick="openLoginModal()">
                            <img src="resources/images/login.png" title="Login" width="25px" height="25px">
                        </button>
                        <c:if test="${message != null}">
                            <div hidden id="hiddenError"></div>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/user" method="post">
                            <span id="welcomeMessage">Welcome, ${currentSessionUser.firstName}</span>
                            <button type="submit" name="logout" class="btn btn-default active">
                                <img src="resources/images/logout.png" title="Logout" width="25px" height="25px">
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
        </span>
    </div>
</nav>