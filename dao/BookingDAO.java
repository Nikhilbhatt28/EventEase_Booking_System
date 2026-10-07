package dao;

import db.DBConnection;
import model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookingDAO {

    // =========================
    // BOOK EVENT
    // =========================

    public boolean bookEvent(Booking booking) {

        try {

            Connection con = DBConnection.getConnection();

            // Check duplicate booking

            String checkQuery = "SELECT * FROM bookings WHERE user_id=? AND event_id=?";

            PreparedStatement checkPs = con.prepareStatement(checkQuery);

            checkPs.setInt(
                    1,
                    booking.getUserId());

            checkPs.setInt(
                    2,
                    booking.getEventId());

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {

                return false;

            }

            // Check seats

            String seatQuery = "SELECT available_seats FROM events WHERE id=?";

            PreparedStatement seatPs = con.prepareStatement(seatQuery);

            seatPs.setInt(
                    1,
                    booking.getEventId());

            ResultSet seatRs = seatPs.executeQuery();

            if (!seatRs.next()) {

                return false;

            }

            int availableSeats = seatRs.getInt(
                    "available_seats");

            if (availableSeats < booking.getQuantity()) {

                return false;

            }

            // Insert booking

            String insertQuery = "INSERT INTO bookings(user_id,event_id,quantity,booking_date) VALUES(?,?,?,?)";

            PreparedStatement insertPs = con.prepareStatement(insertQuery);

            insertPs.setInt(
                    1,
                    booking.getUserId());

            insertPs.setInt(
                    2,
                    booking.getEventId());

            insertPs.setInt(
                    3,
                    booking.getQuantity());

            insertPs.setDate(
                    4,
                    booking.getBookingDate());

            int rows = insertPs.executeUpdate();

            if (rows > 0) {

                String updateQuery = "UPDATE events SET available_seats = available_seats - ? WHERE id=?";

                PreparedStatement updatePs = con.prepareStatement(updateQuery);

                updatePs.setInt(
                        1,
                        booking.getQuantity());

                updatePs.setInt(
                        2,
                        booking.getEventId());

                updatePs.executeUpdate();

                return true;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // =========================
    // GET USER BOOKINGS
    // =========================

    public ArrayList<Booking> getUserBookings(int userId) {

        ArrayList<Booking> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM bookings WHERE user_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(
                    1,
                    userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking();

                booking.setId(
                        rs.getInt("id"));

                booking.setUserId(
                        rs.getInt("user_id"));

                booking.setEventId(
                        rs.getInt("event_id"));

                booking.setQuantity(
                        rs.getInt("quantity"));

                booking.setBookingDate(
                        rs.getDate("booking_date"));

                list.add(booking);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

    // =========================
    // CANCEL BOOKING
    // =========================

    public boolean cancelBooking(int bookingId) {

        try {

            Connection con = DBConnection.getConnection();

            String selectQuery = "SELECT event_id, quantity FROM bookings WHERE id=?";

            PreparedStatement selectPs = con.prepareStatement(selectQuery);

            selectPs.setInt(
                    1,
                    bookingId);

            ResultSet rs = selectPs.executeQuery();

            if (!rs.next()) {

                return false;

            }

            int eventId = rs.getInt("event_id");

            int quantity = rs.getInt("quantity");

            // Return seats

            String updateQuery = "UPDATE events SET available_seats = available_seats + ? WHERE id=?";

            PreparedStatement updatePs = con.prepareStatement(updateQuery);

            updatePs.setInt(
                    1,
                    quantity);

            updatePs.setInt(
                    2,
                    eventId);

            updatePs.executeUpdate();

            // Delete booking

            String deleteQuery = "DELETE FROM bookings WHERE id=?";

            PreparedStatement deletePs = con.prepareStatement(deleteQuery);

            deletePs.setInt(
                    1,
                    bookingId);

            return deletePs.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    // =========================
    // TOTAL REVENUE
    // =========================

    public double getTotalRevenue() {

        double revenue = 0;

        try {

            Connection con = DBConnection.getConnection();

            String query = """
                    SELECT SUM(events.price * bookings.quantity)
                    FROM bookings
                    JOIN events
                    ON bookings.event_id = events.id
                    """;

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                revenue = rs.getDouble(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return revenue;

    }

}