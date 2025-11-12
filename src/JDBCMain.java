import java.sql.*;
public class JDBCMain {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/ad_db",
                "ad_java",
                "ad_java"
        );
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM employee");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " ... " + rs.getString(2) + " ... " + rs.getString(3));
        }
        con.close();
    }
}