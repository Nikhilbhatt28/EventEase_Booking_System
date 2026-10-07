package dao;

import db.DBConnection;
import model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EventDAO {

    // Get All Events
    public ArrayList<Event> getAllEvents() {

        ArrayList<Event> events = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM events";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Event event = new Event();

                event.setId(
                        rs.getInt("id"));

                event.setEventName(
                        rs.getString("event_name"));

                event.setLocation(
                        rs.getString("location"));

                event.setEventDate(
                        rs.getDate("event_date"));

                event.setPrice(
                        rs.getDouble("price"));

                event.setTotalSeats(
                        rs.getInt("total_seats"));

                event.setAvailableSeats(
                        rs.getInt("available_seats"));

                events.add(event);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return events;

    }

    // Add Event
    public boolean addEvent(Event event) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO events(event_name, location, event_date, price, total_seats, available_seats) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(
                    1,
                    event.getEventName());

            ps.setString(
                    2,
                    event.getLocation());

            ps.setDate(
                    3,
                    event.getEventDate());

            ps.setDouble(
                    4,
                    event.getPrice());

            ps.setInt(
                    5,
                    event.getTotalSeats());

            ps.setInt(
                    6,
                    event.getAvailableSeats());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    // Update Event
    public boolean updateEvent(Event event) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "UPDATE events SET event_name=?, location=?, event_date=?, price=?, total_seats=?, available_seats=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(
                    1,
                    event.getEventName());

            ps.setString(
                    2,
                    event.getLocation());

            ps.setDate(
                    3,
                    event.getEventDate());

            ps.setDouble(
                    4,
                    event.getPrice());

            ps.setInt(
                    5,
                    event.getTotalSeats());

            ps.setInt(
                    6,
                    event.getAvailableSeats());

            ps.setInt(
                    7,
                    event.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    // Delete Event
    public boolean deleteEvent(int id) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM events WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(
                    1,
                    id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    // =========================
    // DASHBOARD STATS
    // =========================

    // Total Events Count
    public int getTotalEvents() {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT COUNT(*) FROM events";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                count = rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return count;

    }

    // Total Available Seats
    public int getAvailableSeats() {

        int seats = 0;

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT SUM(available_seats) FROM events";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                seats = rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return seats;

    }

}