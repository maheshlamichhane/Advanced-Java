import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateMain {

    public static void main(String[] args) throws SQLException, ParseException {

        // Inserting Date
        insertingDate();

        // Retrieving Data
        retrievingDate();

        java.util.Date uDate = new java.util.Date();
        System.out.println("UDate ="+uDate);
        long l = uDate.getTime();
        java.sql.Date sDate = new java.sql.Date(l);
        System.out.println("SqlDate="+sDate);
    }

    public static void insertingDate() throws ParseException, SQLException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = format.parse("20/04/1994");

        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into info values(?,?)");
        pst.setString(1,"Zero");
        pst.setDate(2,new java.sql.Date(utilDate.getTime()));
        int x = pst.executeUpdate();
        if(x == 1) {
            System.out.println("Insertion Successful");
        }
    }

    public static void retrievingDate() throws SQLException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from info");
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getString(1)+"..."+format.format(rs.getDate(2)));
        }
    }
}
