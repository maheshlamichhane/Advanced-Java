import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class PropertiesMain {
    public static void main(String[] args) throws IOException, SQLException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("db.properties");
        properties.load(fis);
        String jdbcUrl = properties.getProperty("url");
        String username = properties.getProperty("user");
        String password = properties.getProperty("password");

        Connection con = DriverManager.getConnection(jdbcUrl,username,password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from anonymous");
        while(rs.next()) {
            System.out.println(rs.getInt(1)+"..."+rs.getString(2)+"..."+rs.getString(3));
        }
        con.close();
    }
}
