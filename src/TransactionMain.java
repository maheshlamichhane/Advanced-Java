import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionMain {

    public static void main(String[] args) throws SQLException {

        // transaction
        transactionDemo();

        // save point
        savePointDemo();
    }

    public static void savePointDemo() throws SQLException {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        con.setAutoCommit(false);
        st.executeUpdate("insert into anonymous values(300,'hello','world')");
        st.executeUpdate("insert into anonymous values(400,'wow','wow')");
        Savepoint point = con.setSavepoint();
        st.executeUpdate("insert into anonymous values(500,'zero','zero')");
        System.out.println("Oops wrong entry just rollback");
        con.rollback(point);
//		con.releaseSavepoint(point); this is not supported by type-4 driver
        System.out.println("All Operations are rollback from savepoint");
        con.commit();
        con.close();
    }

    public static void transactionDemo() throws SQLException {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        System.out.println("Transaction Begins");
        con.setAutoCommit(false);
        st.executeUpdate("update account set balance = balance+1000 where name='mahesh'");
        st.executeUpdate("update account set balance = balance-1000 where name ='nabin'");
        Scanner sc = new Scanner(System.in);
        System.out.println("Can you please confirm this transaction of 1000 yes/no");
        String option = sc.next();
        if(option.equalsIgnoreCase("yes")) {
            con.commit();
            System.out.println("transaction committed");
        }
        else {
            con.rollback();
            System.out.println("transaction failed");
        }
    }
}
