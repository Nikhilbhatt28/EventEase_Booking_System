package ui;

import dao.EventDAO;
import model.Event;
import ui.components.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class HomePage {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private int userId;

    private RoundedButton refreshButton;
    private RoundedButton bookButton;
    private RoundedButton bookingButton;
    private RoundedButton logoutButton;

    public HomePage(int userId) {

        this.userId = userId;

        AppTheme.apply();

        frame = new JFrame("EventEase - Home");
        frame.setSize(1100,700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(AppColors.BACKGROUND);

        //========================
        // TOP BAR
        //========================

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(AppColors.PRIMARY);
        topBar.setPreferredSize(new Dimension(1100,80));

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

        JLabel title = new JLabel("EVENTEASE");

        title.setFont(AppFonts.TITLE);
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Smart Event Booking Dashboard");

        subtitle.setFont(AppFonts.SUBTITLE);
        subtitle.setForeground(Color.WHITE);
                leftPanel.add(title);
        leftPanel.add(subtitle);

        JPanel rightPanel = new JPanel(new FlowLayout(
                FlowLayout.RIGHT,
                15,
                18
        ));

        rightPanel.setOpaque(false);

        JLabel welcome = new JLabel("Welcome User");

        welcome.setFont(AppFonts.TEXT);

        welcome.setForeground(Color.WHITE);

        logoutButton = new RoundedButton("LOGOUT");

        logoutButton.setButtonColor(AppColors.DANGER);

        rightPanel.add(welcome);
        rightPanel.add(logoutButton);

        topBar.add(leftPanel, BorderLayout.WEST);
        topBar.add(rightPanel, BorderLayout.EAST);

        root.add(topBar, BorderLayout.NORTH);

        //========================
        // TABLE
        //========================

        model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(
                    int row,
                    int column){

                return false;

            }

        };

        model.addColumn("ID");
        model.addColumn("Event");
        model.addColumn("Location");
        model.addColumn("Date");
        model.addColumn("Price");
        model.addColumn("Seats");

        table = new JTable(model);
                table.setRowHeight(38);

        table.setFont(AppFonts.TEXT);

        table.setForeground(AppColors.TEXT);

        table.setBackground(AppColors.CARD);

        table.setSelectionBackground(AppColors.PRIMARY);

        table.setSelectionForeground(Color.WHITE);

        table.setGridColor(AppColors.BORDER);

        JTableHeader header = table.getTableHeader();

        header.setFont(AppFonts.LABEL);

        header.setBackground(AppColors.PRIMARY);

        header.setForeground(Color.WHITE);

        header.setPreferredSize(new Dimension(100,40));

        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        root.add(scrollPane, BorderLayout.CENTER);

        //========================
        // BOTTOM PANEL
        //========================

        JPanel bottomPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        20,
                        15
                )
        );

        bottomPanel.setBackground(
                AppColors.BACKGROUND
        );
                refreshButton = new RoundedButton("REFRESH");

        bookButton = new RoundedButton("BOOK EVENT");

        bookingButton = new RoundedButton("MY BOOKINGS");

        logoutButton.setButtonColor(AppColors.DANGER);

        bookingButton.setButtonColor(new Color(249,115,22));

        refreshButton.setButtonColor(AppColors.PRIMARY);

        bookButton.setButtonColor(AppColors.SUCCESS);

        bottomPanel.add(refreshButton);

        bottomPanel.add(bookButton);

        bottomPanel.add(bookingButton);

        root.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(root);

        //========================
        // LOAD EVENTS
        //========================

        loadEvents();

        if(table.getRowCount()>0){

            table.setRowSelectionInterval(0,0);

            table.requestFocusInWindow();

        }

        //========================
        // ENTER SUPPORT
        //========================

        table.getInputMap(
                JComponent.WHEN_FOCUSED
        ).put(
                KeyStroke.getKeyStroke("ENTER"),
                "bookEvent"
        );  
                table.getActionMap().put(

                "bookEvent",

                new AbstractAction() {

                    @Override
                    public void actionPerformed(

                            java.awt.event.ActionEvent e) {

                        int row = table.getSelectedRow();

                        if (row != -1) {

                            int eventId = (Integer) model.getValueAt(row,0);

                            new BookEventPage(eventId,userId);

                        }

                    }

                }

        );

        //========================
        // BUTTON EVENTS
        //========================

        refreshButton.addActionListener(e -> {

            loadEvents();

            if(table.getRowCount()>0){

                table.setRowSelectionInterval(0,0);

                table.requestFocusInWindow();

            }

        });
                bookButton.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1){

                JOptionPane.showMessageDialog(
                        frame,
                        "Please select an event first!"
                );

                return;

            }

            int eventId = (Integer) model.getValueAt(row,0);

            new BookEventPage(eventId,userId);

        });

        bookingButton.addActionListener(e -> {

            new MyBookingsPage(userId);

        });

        logoutButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(

                    frame,

                    "Are you sure you want to logout?",

                    "Logout",

                    JOptionPane.YES_NO_OPTION

            );

            if(choice == JOptionPane.YES_OPTION){

                frame.dispose();

                new LoginPage();

            }

        });

        frame.setVisible(true);

    }

// ========================
// LOAD EVENTS
// ========================

private void loadEvents() {

        model.setRowCount(0);

        EventDAO dao = new EventDAO();

        ArrayList<Event> events = dao.getAllEvents();
        for (Event event : events) {

            model.addRow(new Object[] {

                    event.getId(),

                    event.getEventName(),

                    event.getLocation(),

                    event.getEventDate(),

                    "₹ " + event.getPrice(),

                    event.getAvailableSeats()

            });

        }

    }

}