package ui.components;

import javax.swing.*;
import java.awt.*;

public class ShadowPanel extends RoundedPanel {

    public ShadowPanel(int radius) {
        super(radius);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Shadow

        g2.setColor(new Color(0, 0, 0, 60));

        g2.fillRoundRect(
                8,
                8,
                getWidth() - 8,
                getHeight() - 8,
                25,
                25);

        // Main Card

        g2.setColor(getBackground());

        g2.fillRoundRect(
                0,
                0,
                getWidth() - 8,
                getHeight() - 8,
                25,
                25);

        g2.dispose();

        super.paintComponent(g);
    }
}