<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container">
    <div class="modal" id="myOrdersModal">
        <div class="modal-dialog" id="modalDialog">
            <div class="modal-content" id="modalBody">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h1 style="margin-top: 5px; margin-bottom: 5px" class="center">My orders</h1>
                </div>
                <div class="modal-body">
                    <c:choose>
                        <c:when test="${ordersSent.size() == 0}">
                            <h3>No orders sent...</h3>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${ordersSent}" var="ordersSentMap">
                                <h3>Order reference: ${ordersSentMap.key}</h3>
                                <c:set var="orderStatus" value="${ordersSentMap.value[0].status}"/>
                                <table class="table table-striped">
                                    <col width='60%'>
                                    <col width='20%'>
                                    <col width='20%'>
                                    <thead class="thead">
                                    <tr>
                                        <th>Product</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                    </tr>
                                    </thead>
                                    <c:set var="totalPrice" value="0"/>
                                    <c:forEach items="${ordersSentMap.value}" var="ordersSent" >
                                        <c:set var="totalPrice"
                                               value="${totalPrice + ordersSent.product.price * ordersSent.quantity}"/>
                                        <tr>
                                            <td>${ordersSent.product.name}</td>
                                            <td>${ordersSent.quantity}</td>
                                            <td>${ordersSent.product.price}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <h4 id="totalPrice">Total price: ${totalPrice} Lei</h4>
                                <h4 style="float: right" id="status">Status: ${orderStatus}</h4>
                                <br><br><hr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>


