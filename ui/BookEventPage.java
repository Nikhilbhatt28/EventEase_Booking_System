package ui;

import dao.BookingDAO;
import model.Booking;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class BookEventPage {

    JFrame frame;

    public BookEventPage(int eventId, int userId) {

        frame = new JFrame("EventEase - Book Event");
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(245, 247, 250));

        // ================= HEADER =================

        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(450, 80));

        JLabel title = new JLabel("🎟 Book Event");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(37, 99, 235));

        header.add(title);

        frame.add(header, BorderLayout.NORTH);

        // ================= CENTER =================

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(new Color(245, 247, 250));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel eventLabel = new JLabel("Event ID");
        eventLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JTextField eventField = new JTextField(String.valueOf(eventId));
        eventField.setEditable(false);
        eventField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel qtyLabel = new JLabel("Quantity");
        qtyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        quantitySpinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton bookButton = new JButton("✔ Confirm Booking");
        styleBlue(bookButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        center.add(eventLabel, gbc);

        gbc.gridy = 1;
        center.add(eventField, gbc);

        gbc.gridy = 2;
        center.add(qtyLabel, gbc);

        gbc.gridy = 3;
        center.add(quantitySpinner, gbc);

        gbc.gridy = 4;
        center.add(bookButton, gbc);

        frame.add(center, BorderLayout.CENTER);
        
        // ================= ENTER KEY SUPPORT =================

        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) quantitySpinner.getEditor();

        editor.getTextField().addActionListener(e -> bookButton.doClick());

        // ================= ACTION =================

        bookButton.addActionListener(e -> {

            Booking booking = new Booking();

            booking.setUserId(userId);
            booking.setEventId(eventId);
            booking.setQuantity((Integer) quantitySpinner.getValue());
            booking.setBookingDate(new Date(System.currentTimeMillis()));

            BookingDAO dao = new BookingDAO();

            if (dao.bookEvent(booking)) {

                JOptionPane.showMessageDialog(
                        frame,
                        "🎉 Booking Successful!");

                frame.dispose();

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Booking Failed!\n\nPossible Reasons:\n\n"
                                + "• Already booked this event\n"
                                + "• Seats not available");

            }

        });

        frame.setVisible(true);
    }

    // ================= BUTTON STYLE =================

    private void styleBlue(JButton button) {

        button.setBackground(new Color(37, 99, 235));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    }

}