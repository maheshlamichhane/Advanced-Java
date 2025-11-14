import java.sql.*;

public class RowSetMain {

    public static void main(String[] args) throws SQLException {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select id,email,country from anonymous");
        while(rs.next()) {
            RowId id = rs.getRowId(1);
            byte[] b = id.getBytes();
            String rowId = new String(b);
            System.out.println(rowId+"..."+rs.getInt(2)+"..."+rs.getString(3)+"..."+rs.getString(4));
        }
        con.close();
    }
}
