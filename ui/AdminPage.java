package ui;

import dao.BookingDAO;
import dao.EventDAO;
import model.Event;
import ui.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class AdminPage {

        private JFrame frame;

        private RoundedTextField nameField;
        private RoundedTextField locationField;
        private RoundedTextField dateField;
        private RoundedTextField priceField;
        private RoundedTextField seatsField;

        private RoundedButton addButton;
        private RoundedButton deleteButton;
        private RoundedButton logoutButton;

        private JTable table;
        private DefaultTableModel model;
        private JLabel totalEventsValue;
        private JLabel totalSeatsValue;
        private JLabel revenueValue;

        private BookingDAO bookingDAO = new BookingDAO();

        private EventDAO dao = new EventDAO();

        public AdminPage() {

                AppTheme.apply();

                frame = new JFrame("EventEase - Admin Dashboard");

                frame.setSize(1400, 850);

                frame.setLocationRelativeTo(null);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new BorderLayout());

                // =========================
                // HEADER
                // =========================

                JPanel header = new JPanel(new BorderLayout());

                header.setBackground(new Color(25, 25, 30));

                header.setBorder(
                                new EmptyBorder(25, 40, 25, 40));

                JLabel title = new JLabel(
                                "EVENTEASE ADMIN");

                title.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                26));

                title.setForeground(Color.WHITE);

                JLabel subtitle = new JLabel(
                                "Manage Events & Bookings");

                subtitle.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                15));

                subtitle.setForeground(
                                new Color(160, 160, 170));

                JPanel titleBox = new JPanel();

                titleBox.setOpaque(false);

                titleBox.setLayout(
                                new BoxLayout(
                                                titleBox,
                                                BoxLayout.Y_AXIS));

                titleBox.add(title);

                titleBox.add(
                                Box.createVerticalStrut(5));

                titleBox.add(subtitle);

                header.add(
                                titleBox,
                                BorderLayout.WEST);

                frame.add(
                                header,
                                BorderLayout.NORTH);

                // =========================
                // MAIN AREA
                // =========================

                JPanel mainPanel = new JPanel(
                                new BorderLayout(20, 20));

                mainPanel.setBackground(
                                new Color(18, 18, 22));

                mainPanel.setBorder(
                                new EmptyBorder(
                                                20,
                                                20,
                                                20,
                                                20));

                frame.add(
                                mainPanel,
                                BorderLayout.CENTER);
                // =========================
                // LEFT ADD EVENT CARD
                // =========================

                ShadowPanel leftCard = new ShadowPanel(30);

                leftCard.setBackground(
                                new Color(32, 32, 38));

                leftCard.setBorder(
                                new EmptyBorder(
                                                30,
                                                35,
                                                30,
                                                35));

                leftCard.setPreferredSize(
                                new Dimension(
                                                430,
                                                0));

                leftCard.setLayout(
                                new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();

                gbc.gridx = 0;

                gbc.fill = GridBagConstraints.HORIZONTAL;

                gbc.weightx = 1;

                gbc.insets = new Insets(
                                8,
                                0,
                                8,
                                0);

                JLabel addTitle = new JLabel(
                                "ADD NEW EVENT");

                addTitle.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                22));

                addTitle.setForeground(
                                Color.WHITE);

                nameField = new RoundedTextField(20);

                locationField = new RoundedTextField(20);

                dateField = new RoundedTextField(10);

                priceField = new RoundedTextField(10);

                seatsField = new RoundedTextField(10);

                dateField.setText(
                                "YYYY-MM-DD");

                JLabel nameLabel = createLabel(
                                "Event Name");

                JLabel locationLabel = createLabel(
                                "Location");

                JLabel dateLabel = createLabel(
                                "Date");

                JLabel priceLabel = createLabel(
                                "Price (₹)");

                JLabel seatsLabel = createLabel(
                                "Seats");

                addButton = new RoundedButton(
                                "ADD EVENT");

                addButton.setButtonColor(
                                new Color(
                                                34,
                                                197,
                                                94));

                deleteButton = new RoundedButton(
                                "DELETE EVENT");

                deleteButton.setButtonColor(
                                new Color(
                                                239,
                                                68,
                                                68));

                logoutButton = new RoundedButton(
                                "LOGOUT");

                logoutButton.setButtonColor(
                                new Color(
                                                107,
                                                114,
                                                128));

                Dimension fieldSize = new Dimension(
                                340,
                                42);

                nameField.setPreferredSize(fieldSize);
                locationField.setPreferredSize(fieldSize);
                seatsField.setPreferredSize(fieldSize);

                // ADD COMPONENTS

                gbc.gridy = 0;
                leftCard.add(addTitle, gbc);

                gbc.gridy++;
                leftCard.add(nameLabel, gbc);

                gbc.gridy++;
                leftCard.add(nameField, gbc);

                gbc.gridy++;
                leftCard.add(locationLabel, gbc);

                gbc.gridy++;
                leftCard.add(locationField, gbc);

                // DATE PRICE ROW

                JPanel datePrice = new JPanel(
                                new GridLayout(
                                                1,
                                                2,
                                                15,
                                                0));

                datePrice.setOpaque(false);

                JPanel dPanel = createFieldPanel(
                                dateLabel,
                                dateField);

                JPanel pPanel = createFieldPanel(
                                priceLabel,
                                priceField);

                datePrice.add(dPanel);

                datePrice.add(pPanel);

                gbc.gridy++;

                leftCard.add(
                                datePrice,
                                gbc);

                gbc.gridy++;

                leftCard.add(
                                seatsLabel,
                                gbc);

                gbc.gridy++;

                leftCard.add(
                                seatsField,
                                gbc);

                gbc.gridy++;

                leftCard.add(
                                addButton,
                                gbc);

                gbc.gridy++;

                leftCard.add(
                                deleteButton,
                                gbc);

                gbc.gridy++;

                leftCard.add(
                                logoutButton,
                                gbc);

                mainPanel.add(
                                leftCard,
                                BorderLayout.WEST);
                // =========================
                // RIGHT DASHBOARD AREA
                // =========================

                JPanel rightArea = new JPanel(
                                new BorderLayout(15, 15));

                rightArea.setOpaque(false);

                // =========================
                // STATS PANEL
                // =========================

                JPanel statsPanel = new JPanel(
                                new GridLayout(
                                                1,
                                                3,
                                                15,
                                                0));

                statsPanel.setOpaque(false);

                totalEventsValue = new JLabel("0");

                totalSeatsValue = new JLabel("0");

                revenueValue = new JLabel("₹0");

                JPanel eventStat = createStatCard(
                                "◉",
                                "TOTAL EVENTS",
                                totalEventsValue);

                JPanel seatStat = createStatCard(
                                "●",
                                "AVAILABLE SEATS",
                                totalSeatsValue);

                JPanel revenueStat = createStatCard(
                                "₹",
                                "REVENUE",
                                revenueValue);

                statsPanel.add(eventStat);

                statsPanel.add(seatStat);

                statsPanel.add(revenueStat);

                rightArea.add(
                                statsPanel,
                                BorderLayout.NORTH);

                // =========================
                // TABLE CARD
                // =========================

                ShadowPanel tableCard = new ShadowPanel(30);

                tableCard.setBackground(
                                new Color(
                                                32,
                                                32,
                                                38));

                tableCard.setLayout(
                                new BorderLayout());

                tableCard.setBorder(
                                new EmptyBorder(
                                                25,
                                                25,
                                                25,
                                                25));

                JLabel tableTitle = new JLabel(
                                "AVAILABLE EVENTS");

                tableTitle.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                20));

                tableTitle.setForeground(
                                Color.WHITE);

                tableCard.add(
                                tableTitle,
                                BorderLayout.NORTH);

                model = new DefaultTableModel() {

                        @Override
                        public boolean isCellEditable(
                                        int row,
                                        int column) {

                                return false;

                        }

                };

                model.addColumn("ID");

                model.addColumn("EVENT");

                model.addColumn("LOCATION");

                model.addColumn("DATE");

                model.addColumn("PRICE");

                model.addColumn("SEATS");

                table = new JTable(model);

                table.setRowHeight(38);

                table.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                14));

                table.setBackground(
                                new Color(
                                                32,
                                                32,
                                                38));

                table.setForeground(
                                Color.WHITE);

                table.setShowGrid(false);

                JTableHeader tableHeader = table.getTableHeader();

                tableHeader.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                14));

                tableHeader.setBackground(
                                new Color(
                                                79,
                                                70,
                                                229));

                tableHeader.setForeground(
                                Color.WHITE);

                JScrollPane scroll = new JScrollPane(
                                table);

                scroll.setBorder(null);

                scroll.getViewport()
                                .setBackground(
                                                new Color(
                                                                32,
                                                                32,
                                                                38));

                tableCard.add(
                                scroll,
                                BorderLayout.CENTER);

                rightArea.add(
                                tableCard,
                                BorderLayout.CENTER);

                mainPanel.add(
                                rightArea,
                                BorderLayout.CENTER);
                // =========================
                // ADD EVENT ACTION
                // =========================

                addButton.addActionListener(e -> {

                        try {

                                Event event = new Event();

                                event.setEventName(
                                                nameField.getText().trim());

                                event.setLocation(
                                                locationField.getText().trim());

                                event.setEventDate(
                                                Date.valueOf(
                                                                dateField.getText().trim()));

                                event.setPrice(
                                                Double.parseDouble(
                                                                priceField.getText().trim()));

                                int seats = Integer.parseInt(
                                                seatsField.getText().trim());

                                event.setTotalSeats(seats);

                                event.setAvailableSeats(seats);

                                if (dao.addEvent(event)) {

                                        JOptionPane.showMessageDialog(
                                                        frame,
                                                        "Event Added Successfully!");

                                        loadEvents();

                                        clearFields();

                                } else {

                                        JOptionPane.showMessageDialog(
                                                        frame,
                                                        "Failed To Add Event");

                                }

                        } catch (Exception ex) {

                                JOptionPane.showMessageDialog(
                                                frame,
                                                "Enter Valid Details");

                        }

                });

                // =========================
                // DELETE EVENT
                // =========================

                deleteButton.addActionListener(e -> {

                        int row = table.getSelectedRow();

                        if (row == -1) {

                                JOptionPane.showMessageDialog(
                                                frame,
                                                "Select Event First");

                                return;

                        }

                        int id = (Integer) model.getValueAt(row, 0);

                        if (dao.deleteEvent(id)) {

                                JOptionPane.showMessageDialog(
                                                frame,
                                                "Event Deleted");

                                loadEvents();

                        }

                });

                // =========================
                // LOGOUT
                // =========================

                logoutButton.addActionListener(e -> {

                        frame.dispose();

                        new LoginPage();

                });

                // =========================
                // SHOW FRAME
                // =========================

                loadEvents();

                frame.setVisible(true);

        }
        // =========================
        // LOAD EVENTS
        // =========================

        private void loadEvents() {

                model.setRowCount(0);

                ArrayList<Event> list = dao.getAllEvents();

                for (Event event : list) {

                        model.addRow(new Object[] {

                                        event.getId(),
                                        event.getEventName(),
                                        event.getLocation(),
                                        event.getEventDate(),
                                        "₹ " + event.getPrice(),
                                        event.getAvailableSeats()

                        });     

                }

                // UPDATE DASHBOARD STATS

                totalEventsValue.setText(
                                String.valueOf(
                                                dao.getTotalEvents()));

                totalSeatsValue.setText(
                                String.valueOf(
                                                dao.getAvailableSeats()));

                revenueValue.setText(
                                "₹ " +
                                                bookingDAO.getTotalRevenue());

        }

        // =========================
        // CLEAR INPUTS
        // =========================

        private void clearFields() {

                nameField.setText("");

                locationField.setText("");

                dateField.setText("YYYY-MM-DD");

                priceField.setText("");

                seatsField.setText("");

        }

        // =========================
        // CREATE LABEL
        // =========================

        private JLabel createLabel(String text) {

                JLabel label = new JLabel(text);

                label.setForeground(
                                Color.WHITE);

                label.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                14));

                return label;

        }

        // =========================
        // FIELD PANEL
        // =========================

        private JPanel createFieldPanel(
                        JLabel label,
                        JTextField field) {

                JPanel panel = new JPanel();

                panel.setOpaque(false);

                panel.setLayout(
                                new BoxLayout(
                                                panel,
                                                BoxLayout.Y_AXIS));

                panel.add(label);

                panel.add(
                                Box.createVerticalStrut(5));

                panel.add(field);

                return panel;

        }

        // =========================
        // STAT CARD
        // =========================

        private JPanel createStatCard(
                        String icon,
                        String title,
                        JLabel valueLabel) {

                ShadowPanel card = new ShadowPanel(25);

                card.setBackground(
                                new Color(
                                                32,
                                                32,
                                                38));

                card.setPreferredSize(
                                new Dimension(
                                                230,
                                                120));

                card.setLayout(
                                new BorderLayout(
                                                15,
                                                10));

                card.setBorder(
                                new EmptyBorder(
                                                20,
                                                20,
                                                20,
                                                20));

                // ICON BOX

                JLabel iconLabel = new JLabel(icon);

                iconLabel.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                28));

                iconLabel.setForeground(
                                new Color(
                                                129,
                                                140,
                                                248));

                // TITLE

                JLabel titleLabel = new JLabel(
                                title);

                titleLabel.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                13));

                titleLabel.setForeground(
                                new Color(
                                                160,
                                                160,
                                                170));

                // VALUE

                valueLabel.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                28));

                valueLabel.setForeground(
                                Color.WHITE);

                JPanel textPanel = new JPanel();

                textPanel.setOpaque(false);

                textPanel.setLayout(
                                new BoxLayout(
                                                textPanel,
                                                BoxLayout.Y_AXIS));

                textPanel.add(
                                titleLabel);

                textPanel.add(
                                Box.createVerticalStrut(8));

                textPanel.add(
                                valueLabel);

                card.add(
                                iconLabel,
                                BorderLayout.WEST);

                card.add(
                                textPanel,
                                BorderLayout.CENTER);

                return card;

        }

}