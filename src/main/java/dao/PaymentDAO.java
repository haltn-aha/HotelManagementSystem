/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Payment;

/**
 *
 * @author HP
 */
public class PaymentDAO extends DBContext {

    // =========================================================================
    // Retrieve payment information by booking ID.
    // =========================================================================
    public Payment getPaymentByBookingId(int bookingId) {
        try {
            String sql = "SELECT\n"
                    + "    p.payment_id,\n"
                    + "    p.booking_id,\n"
                    + "    p.payment_method,\n"
                    + "    p.amount,\n"
                    + "    p.payment_date\n"
                    + "\n"
                    + "FROM Payment p\n"
                    + "WHERE p.booking_id = ?";

            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, bookingId);

            ResultSet rs = statement.executeQuery();

            // Return the payment if it exists.
            if (rs.next()) {
                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setBookingId(rs.getInt("booking_id"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentDate(rs.getTimestamp("payment_date"));

                return payment;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // =========================================================================
    // Create a new payment record.
    // =========================================================================
    public int createPayment(Payment payment) {

        try {
            String sql = "INSERT INTO Payment (booking_id, payment_method, amount) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement statement = this.getConnection().prepareStatement(sql);

            statement.setInt(1, payment.getBookingId());
            statement.setString(2, payment.getPaymentMethod());
            statement.setDouble(3, payment.getAmount());

            return statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // =========================================================================
    // Retrieve payment history of a specific user.
    // =========================================================================
    public List<Payment> getPaymentHistory(int userId) {

        List<Payment> paymentList = new ArrayList<>();

        try {
            String sql = "SELECT\n"
                    + "    p.payment_id,\n"
                    + "    p.booking_id,\n"
                    + "    p.payment_method,\n"
                    + "    p.amount,\n"
                    + "    p.payment_date\n"
                    + "\n"
                    + "FROM Payment p\n"
                    + "\n"
                    + "JOIN Booking b\n"
                    + "ON p.booking_id = b.booking_id\n"
                    + "\n"
                    + "WHERE b.user_id = ?\n"
                    + "\n"
                    + "ORDER BY p.payment_date DESC";

            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            // Add each payment record to the history list.
            while (rs.next()) {
                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setBookingId(rs.getInt("booking_id"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentDate(rs.getTimestamp("payment_date"));

                paymentList.add(payment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paymentList;
    }

}
