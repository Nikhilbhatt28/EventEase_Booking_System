package ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundedTextField extends JTextField {

    private int radius = 18;

    public RoundedTextField(int columns) {

        super(columns);

        setOpaque(false);

        setFont(AppFonts.TEXT);

        setForeground(AppColors.TEXT);

        setCaretColor(Color.WHITE);

        setBackground(AppColors.INPUT);

        setBorder(new EmptyBorder(10, 15, 10, 15));
        setHorizontalAlignment(JTextField.LEFT);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                radius,
                radius);

        super.paintComponent(g2);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (isFocusOwner()) {
            g2.setColor(AppColors.PRIMARY);
        } else {
            g2.setColor(AppColors.BORDER);
        }

        g2.drawRoundRect(
                1,
                1,
                getWidth() - 3,
                getHeight() - 3,
                radius,
                radius);

        g2.dispose();
    }

    @Override
    public Insets getInsets() {
        return new Insets(10, 15, 10, 15);
    }
    
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}