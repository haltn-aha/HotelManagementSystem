/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookingDAO;
import dto.AccountDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Booking;

/**
 *
 * @author HP
 */
@WebServlet(name = "CheckInOutServlet", urlPatterns = {"/admin/checkinout"})
public class CheckInOutServlet extends HttpServlet {

    private final BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Redirect to the booking management page.
        // Check-in and Check-out are processed via POST requests only.
        response.sendRedirect(request.getContextPath() + "/admin/booking");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // ======================================================
        // Verify login session and administrator permission.
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
        
        // Only administrators are allowed to perform check-in/check-out.
        if (!"ADMIN".equals(loginUser.getRole())) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        // ======================================================
        // Validate request parameters.
        // ======================================================
        String action = request.getParameter("action");
        String bookingIdRaw = request.getParameter("bookingId");

        // Redirect if the request is invalid.
        if (bookingIdRaw == null
                || (!"checkin".equals(action) && !"checkout".equals(action))) {

            response.sendRedirect(request.getContextPath() + "/admin/booking");
            return;
        }
        
        int bookingId = Integer.parseInt(bookingIdRaw);

        // ======================================================
        // Retrieve booking information.
        // ======================================================
        Booking booking = bookingDAO.getBookingById(bookingId);

        // Redirect if the booking does not exist.
        if (booking == null) {
            response.sendRedirect(request.getContextPath() + "/admin/booking");
            return;
        }

        // ======================================================
        // Process check-in or check-out.
        // ======================================================
        if ("checkin".equals(action)) {
            
            // Only confirmed bookings can be checked in.
            if ("CONFIRMED".equals(booking.getStatus())) {

                bookingDAO.updateBookingStatus(bookingId, "CHECKED_IN");
                bookingDAO.updateRoomStatus(booking.getRoomId(), "OCCUPIED");
            }
        } else {
            
            // Only checked-in bookings can be checked out.
            if ("CHECKED_IN".equals(booking.getStatus())) {

                bookingDAO.updateBookingStatus(bookingId, "CHECKED_OUT");
                bookingDAO.updateRoomStatus(booking.getRoomId(), "AVAILABLE");
            }
        }

        // Return to the booking management page after processing.
        response.sendRedirect(request.getContextPath() + "/admin/booking");
    }

}
