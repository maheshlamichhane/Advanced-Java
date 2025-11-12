import java.math.BigInteger;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class CRUDMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = DBUtil.getConnection();

        // Create Table
        createTable(con);

        // Insert Single Rows
        Anonymous anonymous1 = new Anonymous("maheshlamichhane048@gmail.com","NEPAL");
        insertSingleRow(con,anonymous1);

        // Insert Multiple Rows
        Anonymous anonymous2 = new Anonymous("pawanlc@gmail.com","AMERICA");
        Anonymous anonymous3 = new Anonymous("akashlc@gmail.com","SAUDI ARABIA");
        List<Anonymous> list = Arrays.asList(anonymous2,anonymous3);
        insertingMultipleRows(con,list);

        // Delete Single Rows
        deleteSingleRow(con,3);

        // Delete Multiple Rows
        deleteMultipleRows(con);

        // Fetch Single Row
        selectSingleRow(con,1);

        // Fetch Multiple Rows
        selectMultipleRows(con);

        // Fetch ALL Rows
        selectAllRows(con);

        // Update Single Rows
        Anonymous anonymous4 = new Anonymous("akashlc@gmail.com","SAUDI ARABIA");
        updateSingleRow(con,anonymous4,2);

        // Update Multiple Rows
        updateMultipleRows(con);

        // Delete All Rows
        deleteAllRows(con);

        // Drop Table
        dropTable(con);

    }

    public static void createTable(Connection con) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS anonymous(id serial,email varchar(100),country varchar(100))");
        System.out.println("Table created successfully");
    }

    public static void insertSingleRow(Connection con,Anonymous anonymous) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO anonymous(email,country) VALUES(?,?)");
        st.setString(1, anonymous.getEmail());
        st.setString(2, anonymous.getCountry());
        System.out.println("One row inserted successfully"+st.executeUpdate());
    }

    public static void insertingMultipleRows(Connection con, List<Anonymous> list) throws SQLException {
        for(Anonymous an : list){
            PreparedStatement pst = con.prepareStatement("insert into anonymous(email,country) values (?,?)");
            pst.setString(1, an.getEmail());
            pst.setString(2, an.getCountry());
            pst.executeUpdate();
        }
    }

    public static void deleteSingleRow(Connection con,int id) throws SQLException {
        PreparedStatement st = con.prepareStatement("delete from anonymous where id = ?");
        st.setInt(1, id);
        st.executeUpdate();
        System.out.println("Table data with id = "+id+" deleted successfully");
    }

    public static void deleteMultipleRows(Connection con) throws SQLException {
        PreparedStatement pst = con.prepareStatement("delete from anonymous where id > ?");
        pst.setInt(1, 10);
        pst.executeUpdate();
        System.out.println("Table multiple rows deleted successfully");
    }

    public static void selectSingleRow(Connection con,int  id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("select * from anonymous where id = ?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            System.out.println("Id="+rs.getInt("id")+", Email="+rs.getString("email")+", Country="+rs.getString("country"));
        }
    }

    public static void selectMultipleRows(Connection con) throws SQLException {
        PreparedStatement pst = con.prepareStatement("select * from anonymous where id > ?");
        pst.setInt(1, 1);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            System.out.println("Id="+rs.getInt("id")+", Email="+rs.getString("email")+", Country="+rs.getString("country"));
        }
    }

    public static void selectAllRows(Connection con) throws SQLException {
        PreparedStatement pst = con.prepareStatement("select * from anonymous order by id desc");
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            System.out.println("Id="+rs.getInt("id")+", Email="+rs.getString("email")+", Country="+rs.getString("country"));
        }
    }


    public static void updateSingleRow(Connection con,Anonymous anonymous,int id) throws SQLException {
        PreparedStatement st = con.prepareStatement("update anonymous set email = ? , country = ? where id = ?");
        st.setString(1, anonymous.getEmail());
        st.setString(2, anonymous.getCountry());
        st.setInt(3, id);
        st.executeUpdate();
        System.out.println("Table updated successfully");
    }

    public static void updateMultipleRows(Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("update anonymous set country = ? where id >=1 ");
        st.setString(1,"mangal");
        st.executeUpdate();
        System.out.println("Table updated successfully");
    }

    public  static void deleteAllRows(Connection con) throws SQLException {
        PreparedStatement pst = con.prepareStatement("delete from anonymous");
        pst.executeUpdate();
        System.out.println("Table all rows deleted successfully");
    }



    public static void dropTable(Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("drop table anonymous");
        st.executeUpdate();
        System.out.println("Table dropped successfully");
        con.close();
    }


}
