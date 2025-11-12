import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AggregateMain {

    public static void main(String[] args) throws SQLException {

        Connection con = DBUtil.getConnection();
        CRUDMain.createTable(con);
        Anonymous anonymous2 = new Anonymous("pawanlc@gmail.com","AMERICA");
        Anonymous anonymous3 = new Anonymous("akashlc@gmail.com","SAUDI ARABIA");
        List<Anonymous> list = Arrays.asList(anonymous2,anonymous3);
        CRUDMain.insertingMultipleRows(con, list);
        PreparedStatement pst = con.prepareStatement("select SUM(id) FROM anonymous");
        ResultSet rs = pst.executeQuery();
        rs.next();
        System.out.println(rs.getInt(1));
        CRUDMain.dropTable(con);
    }
}
