<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container">
    <div class="modal" id="cartModal">
        <div class="modal-dialog" id="modalDialog">
            <div class="modal-content" id="modalBody">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h1 style="margin-top: 5px; margin-bottom: 5px" class="center">Shopping cart</h1>
                </div>
                <div class="modal-body">
                    <c:choose>
                        <c:when test="${fn:length(ordersPlaced) == 0}">
                            <h3>No items in cart...</h3>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-striped">
                                <col width='50%'>
                                <col width='20%'>
                                <col width='30%'>
                                <thead class="thead">
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                </tr>
                                </thead>
                                <c:set var="totalPrice" value="0"/>
                                <c:forEach var="order" items="${ordersPlaced}">
                                    <c:set var="totalPrice"
                                           value="${totalPrice + order.product.price * order.quantity}"/>
                                    <tr>
                                        <td>${order.product.name}</td>
                                        <td>${order.quantity}</td>
                                        <td style="color: red">${order.product.price} Lei
                                            <div style="float: right">
                                                <form action="${pageContext.request.contextPath}/order" method="post">
                                                    <button name="removeProduct" value="${order.product.name}"
                                                            type="submit"
                                                            class="btn btn-danger"
                                                            style="margin-right: 5px; float: right">
                                                        <img src="resources/images/removeFromCart.png"
                                                             title="Remove from cart" width="20px"
                                                             height="20px">
                                                    </button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="modal-footer">
                    <c:if test="${fn:length(ordersPlaced) != 0}">
                        <h4 id="totalPrice">Total price: ${totalPrice} Lei</h4>
                        <c:choose>
                            <c:when test="${currentSessionUser != null}">
                                <button class="btn btn-success" id="send">
                                    <img src="resources/images/send.png" title="Send" width="20px" height="20px">
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button onclick="function openLoginModal() {
                                            $('#cartModal').modal('hide');
                                            $('#loginModal').modal('show');
                                        }
                                        openLoginModal()" type="button" class="btn btn-link">
                                    To send an order you must log in!
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>


