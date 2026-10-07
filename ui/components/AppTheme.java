package ui.components;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class AppTheme {

    public static void apply() {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());

            UIManager.put("Button.arc", 18);
            UIManager.put("Component.arc", 18);
            UIManager.put("TextComponent.arc", 18);

            UIManager.put("Button.focusWidth", 0);

            UIManager.put("Button.font", AppFonts.BUTTON);
            UIManager.put("Label.font", AppFonts.TEXT);
            UIManager.put("TextField.font", AppFonts.TEXT);
            UIManager.put("PasswordField.font", AppFonts.TEXT);

            UIManager.put("Button.background", AppColors.PRIMARY);
            UIManager.put("Button.foreground", Color.WHITE);

            UIManager.put("TextField.background", AppColors.INPUT);
            UIManager.put("PasswordField.background", AppColors.INPUT);

            UIManager.put("Panel.background", AppColors.BACKGROUND);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}