/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;

/**
 *
 * @author HP
 */
public class BookingDAO extends DBContext {

    // =========================================================================
    // Retrieve booking information by booking ID.
    // =========================================================================
    public Booking getBookingById(int bookingId) {
        try {
            String sql = "SELECT\n"
                    + "    b.*,\n"
                    + "    u.full_name,\n"
                    + "    r.room_number,\n"
                    + "    rt.type_name,\n"
                    + "    rt.price\n"
                    + "FROM Booking b\n"
                    + "JOIN Users u\n"
                    + "    ON b.user_id = u.user_id\n"
                    + "JOIN Room r\n"
                    + "    ON b.room_id = r.room_id\n"
                    + "JOIN RoomType rt\n"
                    + "    ON r.type_id = rt.type_id\n"
                    + "WHERE b.booking_id = ?";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, bookingId);

            ResultSet rs = statement.executeQuery();

            // Return the booking if it exists.
            if (rs.next()) {
                Booking booking = new Booking();

                booking.setBookingId(rs.getInt("booking_id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setRoomId(rs.getInt("room_id"));
                booking.setCheckIn(rs.getDate("check_in"));
                booking.setCheckOut(rs.getDate("check_out"));
                booking.setBookingDate(rs.getTimestamp("booking_date"));
                booking.setTotalPrice(rs.getDouble("total_price"));
                booking.setStatus(rs.getString("status"));

                booking.setFullName(rs.getString("full_name"));

                booking.setRoomNumber(rs.getString("room_number"));
                booking.setRoomType(rs.getString("type_name"));
                booking.setPricePerNight(rs.getDouble("price"));
                return booking;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // =========================================================================
    // Update booking status.
    // =========================================================================
    public int updateBookingStatus(int bookingId, String status) {

        try {
            String sql = "UPDATE Booking "
                    + "SET status = ? "
                    + "WHERE booking_id = ?";

            PreparedStatement statement = this.getConnection().prepareStatement(sql);

            statement.setString(1, status);
            statement.setInt(2, bookingId);

            return statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // =========================================================================
    // Update room status.
    // =========================================================================
    public int updateRoomStatus(int roomId, String status) {
        try {
            String sql = "UPDATE Room "
                    + "SET status = ? "
                    + "WHERE room_id = ?";

            PreparedStatement statement = this.getConnection().prepareStatement(sql);

            statement.setString(1, status);
            statement.setInt(2, roomId);

            return statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
