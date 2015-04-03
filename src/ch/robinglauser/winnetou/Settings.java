package ch.robinglauser.winnetou;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Robin on 22.03.2015.
 */
public class Settings {

    private Connection connection;

    public Settings(Connection connection){
        this.connection = connection;
    }

    public String get(String name){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `value` FROM settings WHERE `name` = \""+name+"\" LIMIT 1;");
            while (rs.next()) {
                return rs.getString("value");
            }
            rs.close();
        } catch ( Exception e ) {

        }
        return "";
    }

    public void set(String name, String value){
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("INSERT INTO settings (`value`, `name`) VALUES ('"+name+"', '"+value+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
