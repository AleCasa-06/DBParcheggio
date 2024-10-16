import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class DB {

    private Connection conn;

    public DB(String address, String port, String dbName, String user, String pass){
        String dbUrl = "jdbc:mysql://" + address + ":" + port + "/" + dbName;
        try{
            conn = DriverManager.getConnection(dbUrl, user, pass);
            if(conn!=null){
                System.out.println("connessione avvenuta");
            }

        }
        catch(SQLException e){

            e.getMessage();
        }
    }

    public boolean insertData(String targa, String marca, String modello){
        String query = "INSERT INTO auto (targa, marca, modello) VALUES (?, ?, ?)";
        try{
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, targa);
            stat.setString(2, marca);
            stat.setString(3, modello);
            stat.executeUpdate();
        }
        catch (SQLException e){
            e.getMessage();
            return false;
        }
        return true;
    }

    public String select(String what, String from, String where, String is) {
        String result = "";
        try {
            if (!conn.isValid(5)) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        String query = "SELECT " + what + " FROM " + from + " WHERE " + where + " = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, is);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    result += rs.getString(i) + "\t";
                    if (rs.getString(i).length() < 8) result += "\t";
                }
                result += "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public String selectALL(){
        String result = "";
        try {
            if (!conn.isValid(5)) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        String query = "SELECT * FROM auto";

        try {
            PreparedStatement statement = conn.prepareStatement(query);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    result += rs.getString(i) + "\t";
                    if (rs.getString(i).length() < 8) result += "\t";
                }
                result += "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }



}
