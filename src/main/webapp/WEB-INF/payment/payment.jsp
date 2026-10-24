<%--
    Document   : payment
    Created on : Jul 3, 2026
    Author     : HP
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>

<div class="container mb-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <h2 class="mb-2 fw-bold">
                Payment Invoice
            </h2>
            <p class="text-muted">
                View booking and payment details.
            </p>
            <hr class="mt-0 mb-4">

            <c:if test="${param.success == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong>Payment Successful!</strong>
                    Your payment has been recorded successfully.
                </div>
            </c:if>

            <c:choose>
                <c:when test="${booking != null}">

                    <div class="card shadow-sm">
                        <div class="card-header bg-primary text-white">
                            Invoice Information
                        </div>

                        <div class="card-body">
                            <table class="table table-bordered mb-0">
                                <tr>
                                    <th style="width:35%">Booking ID</th>
                                    <td>${booking.bookingId}</td>
                                </tr>

                                <c:if test="${payment != null}">
                                    <tr>
                                        <th>Payment ID</th>
                                        <td>${payment.paymentId}</td>
                                    </tr>
                                </c:if>

                                <tr>
                                    <th>Guest Name</th>
                                    <td>${booking.fullName}</td>
                                </tr>

                                <tr>
                                    <th>Status</th>
                                    <td>
                                        <c:choose>

                                            <c:when test="${booking.status == 'PENDING'}">
                                                <span class="badge bg-warning text-dark">
                                                    Pending
                                                </span>
                                            </c:when>

                                            <c:when test="${booking.status == 'CONFIRMED'}">
                                                <span class="badge bg-primary">
                                                    Confirmed
                                                </span>
                                            </c:when>

                                            <c:when test="${booking.status == 'CHECKED_IN'}">
                                                <span class="badge bg-info">
                                                    Checked In
                                                </span>
                                            </c:when>

                                            <c:when test="${booking.status == 'CHECKED_OUT'}">
                                                <span class="badge bg-success">
                                                    Checked Out
                                                </span>
                                            </c:when>

                                            <c:when test="${booking.status == 'CANCELLED'}">
                                                <span class="badge bg-danger">
                                                    Cancelled
                                                </span>
                                            </c:when>

                                            <c:otherwise>
                                                <span class="badge bg-secondary">
                                                    ${booking.status}
                                                </span>
                                            </c:otherwise>

                                        </c:choose>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div class="card shadow-sm mt-4">
                        <div class="card-header bg-secondary text-white">
                            Room Information
                        </div>

                        <div class="card-body">
                            <table class="table table-bordered mb-0">
                                <tr>
                                    <th style="width:35%">Room Number</th>
                                    <td>${booking.roomNumber}</td>
                                </tr>
                                <tr>
                                    <th>Room Type</th>
                                    <td>${booking.roomType}</td>
                                </tr>
                                <tr>
                                    <th>Price / Night</th>
                                    <td>
                                        $
                                        <fmt:formatNumber value="${booking.pricePerNight}"
                                                          pattern="#,##0.00"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Check-in Date</th>
                                    <td>
                                        <fmt:formatDate value="${booking.checkIn}" pattern="dd/MM/yyyy"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Check-out Date</th>
                                    <td>
                                        <fmt:formatDate value="${booking.checkOut}" pattern="dd/MM/yyyy"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Total Amount</th>
                                    <td class="fw-bold text-danger">
                                        $
                                        <fmt:formatNumber value="${booking.totalPrice}"
                                                          pattern="#,##0.00"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${payment == null}">

                            <div class="card shadow-sm mt-4">
                                <div class="card-header bg-warning text-dark">
                                    Payment
                                </div>

                                <div class="card-body">
                                    <form method="post" action="${pageContext.request.contextPath}/payment?action=create">

                                        <input type="hidden" name="bookingId" value="${booking.bookingId}">

                                        <h6 class="text-center fw-semibold mb-3">
                                            Select Payment Method
                                        </h6>

                                        <div class="row justify-content-center g-3 mb-4">

                                            <div class="col-md-3">
                                                <input type="radio"
                                                       class="btn-check"
                                                       name="paymentMethod"
                                                       id="bank"
                                                       value="BANK_TRANSFER"
                                                       checked>

                                                <label class="btn btn-outline-secondary w-100 py-4"
                                                       for="bank">

                                                    <div class="fw-semibold">
                                                        Bank Transfer
                                                    </div>

                                                </label>
                                            </div>

                                            <div class="col-md-3">
                                                <input type="radio"
                                                       class="btn-check"
                                                       name="paymentMethod"
                                                       id="cash"
                                                       value="CASH">

                                                <label class="btn btn-outline-secondary w-100 py-4"
                                                       for="cash">

                                                    <div class="fw-semibold">
                                                        Cash
                                                    </div>

                                                </label>
                                            </div>

                                        </div>

                                        <div class="text-center">
                                            <button type="submit" class="btn btn-dark px-3 py-2">
                                                Confirm Payment
                                            </button>
                                        </div>

                                    </form>
                                </div>

                            </div>

                        </c:when>

                        <c:otherwise>

                            <div class="card shadow-sm mt-4">
                                <div class="card-header bg-success text-white">
                                    Payment Information
                                </div>

                                <div class="card-body">
                                    <table class="table table-bordered mb-0">
                                        <tr>
                                            <th style="width:35%">Payment Method</th>
                                            <td>
                                                <c:choose>

                                                    <c:when test="${payment.paymentMethod == 'BANK_TRANSFER'}">
                                                        Bank Transfer
                                                    </c:when>

                                                    <c:otherwise>
                                                        Cash
                                                    </c:otherwise>

                                                </c:choose>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Payment Date</th>
                                            <td>${payment.paymentDate}</td>
                                        </tr>
                                        <tr>
                                            <th>Amount Paid</th>
                                            <td class="fw-bold text-success">
                                                $
                                                <fmt:formatNumber value="${payment.amount}"
                                                                  pattern="#,##0.00"/>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>

                        </c:otherwise>
                    </c:choose>

                </c:when>

                <c:otherwise>

                    <div class="alert alert-danger">
                        Booking not found.
                    </div>

                </c:otherwise>
            </c:choose>

        </div>
    </div>

</div>

<%@include file="/WEB-INF/include/footer.jsp"%>