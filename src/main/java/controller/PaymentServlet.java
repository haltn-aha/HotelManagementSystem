/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookingDAO;
import dao.PaymentDAO;
import dto.AccountDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Booking;
import model.Payment;

/**
 *
 * @author HP
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

    private final BookingDAO bookingDAO = new BookingDAO();
    private final PaymentDAO paymentDAO = new PaymentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ======================================================
        // Verify login session.
        // ======================================================        
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/auth");
            return;
        }

        AccountDTO loginUser = (AccountDTO) session.getAttribute("LOGIN_USER");

        // Redirect to the login page if no valid session exists.
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/auth");
            return;
        }

        String action = request.getParameter("action");

        // ======================================================
        // Display payment history.
        // ======================================================
        if ("history".equals(action)) {

            // Get the current user's ID from the login session.
            int userId = loginUser.getUserId();

            // Retrieve all payment records of the current user.
            List<Payment> paymentList = paymentDAO.getPaymentHistory(userId);

            request.setAttribute("paymentList", paymentList);

            request.getRequestDispatcher("/WEB-INF/payment/payment-history.jsp").forward(request, response);

            return;
        }

        // ======================================================
        // Display payment invoice.
        // ======================================================
        // Retrieve bookingId from the request.
        String bookingIdRaw = request.getParameter("bookingId");

        // Redirect if bookingId is missing.
        if (bookingIdRaw == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        // Convert bookingId to integer.
        int bookingId = Integer.parseInt(bookingIdRaw);

        // Retrieve the booking information.
        Booking booking = bookingDAO.getBookingById(bookingId);

        // Redirect if the booking does not exist.
        if (booking == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        // Prevent users from accessing another user's booking.
        if (booking.getUserId() != loginUser.getUserId()) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        Payment payment = paymentDAO.getPaymentByBookingId(bookingId);

        request.setAttribute("booking", booking);
        request.setAttribute("payment", payment);

        request.getRequestDispatcher("/WEB-INF/payment/payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ======================================================
        // Verify login session.
        // ======================================================
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/auth");
            return;
        }

        AccountDTO loginUser = (AccountDTO) session.getAttribute("LOGIN_USER");

        // Redirect to the login page if no valid session exists.
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/auth");
            return;
        }

        String action = request.getParameter("action");

        // ======================================================
        // Process payment.
        // ======================================================
        if ("create".equals(action)) {

            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            String paymentMethod = request.getParameter("paymentMethod");

            // Retrieve the booking information.
            Booking booking = bookingDAO.getBookingById(bookingId);

            // Redirect if the booking does not exist.
            if (booking == null) {
                response.sendRedirect(request.getContextPath());
                return;
            }

            // Prevent users from paying for another user's booking.
            if (booking.getUserId() != loginUser.getUserId()) {
                response.sendRedirect(request.getContextPath());
                return;
            }

            // A booking can only be paid once.
            Payment payment = paymentDAO.getPaymentByBookingId(bookingId);

            if (payment == null) {

                // Create a new payment record.
                Payment newPayment = new Payment();

                newPayment.setBookingId(bookingId);
                newPayment.setPaymentMethod(paymentMethod);
                newPayment.setAmount(booking.getTotalPrice());

                paymentDAO.createPayment(newPayment);
            }

            // Redirect to display the invoice after payment.
            response.sendRedirect(request.getContextPath() + "/payment?bookingId=" + bookingId + "&success=true");
        }
    }

}
