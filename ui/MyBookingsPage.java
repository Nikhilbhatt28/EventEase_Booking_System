package ui;

import dao.BookingDAO;
import model.Booking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class MyBookingsPage {

    JFrame frame;
    JTable table;
    DefaultTableModel model;

    public MyBookingsPage(int userId) {

        frame = new JFrame("EventEase - My Bookings");
        frame.setSize(850, 550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(245, 247, 250));

        // ================= HEADER =================

        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(850, 80));
        header.setBackground(Color.WHITE);
        header.setLayout(new BorderLayout());

        JLabel title = new JLabel("📖 My Bookings");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(37, 99, 235));
        title.setBorder(BorderFactory.createEmptyBorder(18, 25, 0, 0));

        header.add(title, BorderLayout.WEST);

        frame.add(header, BorderLayout.NORTH);

        // ================= TABLE =================

        model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        model.addColumn("Booking ID");
        model.addColumn("Event ID");
        model.addColumn("Quantity");
        model.addColumn("Booking Date");

        table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(new Color(220, 220, 220));

        JTableHeader tableHeader = table.getTableHeader();

        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setBackground(new Color(37, 99, 235));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(100, 38));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        frame.add(scrollPane, BorderLayout.CENTER);

        // ================= ENTER KEY SUPPORT =================

        table.getInputMap(JComponent.WHEN_FOCUSED).put(
                KeyStroke.getKeyStroke("ENTER"),
                "cancelBooking");

        table.getActionMap().put("cancelBooking", new AbstractAction() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                int row = table.getSelectedRow();

                if (row == -1)
                    return;

                int bookingId = (Integer) model.getValueAt(row, 0);

                BookingDAO dao = new BookingDAO();

                if (dao.cancelBooking(bookingId)) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Booking Cancelled Successfully!");

                    loadBookings(userId);

                    if (table.getRowCount() > 0) {

                        table.setRowSelectionInterval(0, 0);
                        table.requestFocusInWindow();

                    }

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Cancellation Failed!");

                }

            }

        });

        // ================= BUTTON PANEL =================

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 20, 20));

        buttonPanel.setBackground(new Color(245, 247, 250));

        JButton refreshButton = new JButton("🔄 Refresh");
        JButton cancelButton = new JButton("❌ Cancel Booking");
        JButton closeButton = new JButton("🚪 Close");

        styleBlue(refreshButton);
        styleRed(cancelButton);
        styleGray(closeButton);

        buttonPanel.add(refreshButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(closeButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        loadBookings(userId);

        // First booking auto select

        if (table.getRowCount() > 0) {

            table.setRowSelectionInterval(0, 0);
            table.requestFocusInWindow();

        }

        refreshButton.addActionListener(e -> {

            loadBookings(userId);

            if (table.getRowCount() > 0) {

                table.setRowSelectionInterval(0, 0);
                table.requestFocusInWindow();

            }

        });

        cancelButton.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please select a booking first.");

                return;

            }

            int choice = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to cancel this booking?",
                    "Confirm Cancellation",
                    JOptionPane.YES_NO_OPTION);

            if (choice != JOptionPane.YES_OPTION)
                return;

            int bookingId = (Integer) model.getValueAt(row, 0);

            BookingDAO dao = new BookingDAO();

            if (dao.cancelBooking(bookingId)) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Booking Cancelled Successfully!");

                loadBookings(userId);

                if (table.getRowCount() > 0) {

                    table.setRowSelectionInterval(0, 0);
                    table.requestFocusInWindow();

                }

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Cancellation Failed!");

            }

        });

        closeButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);

    }

    // ================= LOAD BOOKINGS =================

    private void loadBookings(int userId) {

        model.setRowCount(0);

        BookingDAO dao = new BookingDAO();

        ArrayList<Booking> list = dao.getUserBookings(userId);

        for (Booking booking : list) {

            model.addRow(new Object[] {

                    booking.getId(),
                    booking.getEventId(),
                    booking.getQuantity(),
                    booking.getBookingDate()

            });

        }

    }
    // ================= BUTTON STYLES =================

    private void styleBlue(JButton button) {

        button.setBackground(new Color(37, 99, 235));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    }

    private void styleRed(JButton button) {

        button.setBackground(new Color(239, 68, 68));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    }

    private void styleGray(JButton button) {

        button.setBackground(new Color(107, 114, 128));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    }

}