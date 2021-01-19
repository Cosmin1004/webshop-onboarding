<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container">
    <div class="modal" id="cartModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h1 style="margin-top: 5px; margin-bottom: 5px" class="center">Shopping cart</h1>
                </div>
                <div class="modal-body">
                    <c:choose>
                        <c:when test="${fn:length(orders) == 0}">
                            <h3>No items in cart...</h3>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-striped">
                                <thead class="thead">
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                </tr>
                                </thead>
                                <c:set var="totalPrice" value="0"/>
                                <c:forEach var="order" items="${orders}">
                                    <c:set var="totalPrice" value="${totalPrice + order.product.price}"/>
                                    <tr>
                                        <td>${order.product.name}</td>
                                        <td>${order.quantity}</td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/order" method="post">
                                                <button name="removeProduct" value="${order.product.name}" type="submit"
                                                        class="btn btn-danger" style="margin-right: 5px; float: right">
                                                    <img src="resources/images/removeFromCart.png"title="Remove from cart" width="20px"
                                                         height="20px">
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="modal-footer">
                    <form>
                        <c:if test="${fn:length(orders) != 0}">
                            <h4 id="totalPrice">Total price: ${totalPrice} Lei</h4>
                            <button type="submit" class="btn btn-success">
                                <img src="resources/images/send.png" title="Send" width="20px" height="20px">
                            </button>
                        </c:if>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


