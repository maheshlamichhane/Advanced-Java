import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BatchUpdatesMain {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Batch Update With Simple Statement
//        batchUpdateWithSimpleStatement();

        // Batch Update With Prepared Statement
        batchUpdateWithPreparedStatement();
    }

    public static void batchUpdateWithSimpleStatement() throws SQLException {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        st.addBatch("insert into anonymous values(200,'halland@gmail.com','norway')");
        st.addBatch("update anonymous set email='goalmachine@gmail.com'");
        st.addBatch("insert into anonymous values(300,'mambappe@gmail.com','franche')");
        int[] result = st.executeBatch();
        for(int i: result) {
            System.out.println(i+ " ROWS UPDATED");
        }
    }

    public static void batchUpdateWithPreparedStatement() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        Connection con = DBUtil.getConnection();
        PreparedStatement st = con.prepareStatement("insert into anonymous values(?,?,?)");
        while(true) {
            System.out.println("Enter Id");
            int x = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Email");
            String email = sc.nextLine();
            System.out.println("Enter Country");
            String country = sc.nextLine();

            st.setInt(1,x);
            st.setString(2,email);
            st.setString(3,country);

            st.addBatch();

            System.out.println("Enter do you want to continue yes/no");
            String decision = sc.nextLine();
            if(decision.equals("no")) {
                break;
            }
        }
        st.executeBatch();
        System.out.println("Records Inserted Successfully");
    }
}
