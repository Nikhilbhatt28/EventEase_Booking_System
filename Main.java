import db.DBConnection;
import ui.LoginPage;
import ui.components.AppTheme;

public class Main {

    public static void main(String[] args) {

        AppTheme.apply();

        DBConnection.getConnection();

        new LoginPage();
    }
}