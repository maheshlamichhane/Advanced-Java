import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionPoolMain {

    public static void main(String[] args) throws Exception {

        // Java provided data source
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setURL("jdbc:postgresql://localhost:5432/ad_db");
        ds.setUser("ad_java");
        ds.setPassword("ad_java");


        Connection con = ds.getConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM anonymous");

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + "..." +
                            rs.getString(2) + "..." +
                            rs.getString(3)
            );
        }

        con.close();






        // Custom connection pool
        ConnectionPool pool = new ConnectionPool(
                "jdbc:postgresql://localhost:5432/ad_db",
                "ad_java",
                "ad_java",
                2
        );

        Runnable task = () -> {
            try {
                Connection conn = pool.getConnection();
                System.out.println(Thread.currentThread().getName() + " GOT connection");

                Thread.sleep(2000);

                pool.releaseConnection(conn);
                System.out.println(Thread.currentThread().getName() + " RELEASED connection");

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // create multiple threads to use the pool
        for (int i = 0; i < 10; i++) {
            new Thread(task, "Worker-" + i).start();
        }
    }
}


