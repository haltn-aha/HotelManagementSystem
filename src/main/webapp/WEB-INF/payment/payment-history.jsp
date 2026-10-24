<%--
    Document   : payment-history
    Created on : Jul 4, 2026
    Author     : HP
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>

<div class="container mb-5">
    <h2 class="mb-4 fw-bold">
        Payment History
    </h2>
    <hr class="mt-0 mb-4">

    <c:choose>
        <c:when test="${not empty paymentList}">

            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    Payment History
                </div>

                <div class="card-body">
                    <table class="table table-bordered table-hover align-middle mb-0">

                        <thead class="table-light">
                            <tr>
                                <th class="text-center">Payment ID</th>
                                <th class="text-center">Booking ID</th>
                                <th class="text-center">Payment Method</th>
                                <th class="text-center">Amount</th>
                                <th class="text-center">Payment Date</th>
                                <th class="text-center">Invoice</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${paymentList}" var="payment">
                                <tr>
                                    <td class="text-center">${payment.paymentId}</td>
                                    <td class="text-center">${payment.bookingId}</td>
                                    <td class="text-center">
                                        <c:choose>

                                            <c:when test="${payment.paymentMethod == 'BANK_TRANSFER'}">
                                                Bank Transfer
                                            </c:when>

                                            <c:otherwise>
                                                Cash
                                            </c:otherwise>

                                        </c:choose>
                                    </td>
                                    <td class="text-center fw-bold text-success">
                                        $
                                        <fmt:formatNumber value="${payment.amount}"
                                                          pattern="#,##0.00"/>
                                    </td>
                                    <td class="text-center">
                                        <fmt:formatDate value="${payment.paymentDate}" pattern="dd/MM/yyyy HH:mm"/>
                                    </td>
                                    <td class="text-center">
                                        <a class="btn btn-sm btn-outline-primary"
                                           href="${pageContext.request.contextPath}/payment?bookingId=${payment.bookingId}">
                                            View Invoice
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>

        </c:when>

        <c:otherwise>

            <div class="alert alert-info">
                No payment history found.
            </div>

        </c:otherwise>
    </c:choose>
</div>

<%@include file="/WEB-INF/include/footer.jsp"%>