import java.sql.*;

public class StoredProcedureMain {

    public static void main(String[] args) throws SQLException {
        // Stored Procedure For Sum
        addNum();

        // Stored Procedure for Delete the rows and return row affected
        deleteRowsAndReturnAffected();

        // Stored Procedure for fetch email info using user id
        fetchEmailUsingUserId();



    }

    public static void fetchEmailUsingUserId() throws SQLException {
        Connection con = DBUtil.getConnection();
        CallableStatement cst = con.prepareCall("call user_by_id(?,?)");
        cst.setInt(1,5);
        cst.registerOutParameter(2,Types.VARCHAR);
        cst.execute();
        String email = cst.getString(2);
        System.out.println("Email="+email);

        /*
        CREATE OR REPLACE PROCEDURE public.user_by_id(
                IN x INT,
                OUT z TEXT
        )
        LANGUAGE plpgsql
        AS $$
        BEGIN
        SELECT email
        INTO z
        FROM anonymous
        WHERE id = x;
        END;
        $$;
         */

    }

    public static void deleteRowsAndReturnAffected() throws SQLException {
        Connection con = DBUtil.getConnection();
        con.setAutoCommit(false);
        CallableStatement cst = con.prepareCall("CALL public.delete_by_id(?, ?, ?)");
        cst.setInt(1,6);
        cst.registerOutParameter(2,Types.INTEGER);
        cst.registerOutParameter(3,Types.REF_CURSOR);
        cst.execute();
        int deleted = cst.getInt(2);
        System.out.println("No of rows effected="+deleted);
        ResultSet rs  = (ResultSet) cst.getObject(3);
        while(rs.next()) {
            System.out.println(rs.getInt(1)+"..."+rs.getString(2)+"..."+rs.getString(3));
        }
        con.commit();

        /*
            CREATE OR REPLACE PROCEDURE public.delete_by_id(
            IN id1 INT,
            OUT id3 INT,
             OUT users REFCURSOR
            )
            LANGUAGE plpgsql
            AS $$
            BEGIN
            -- Open a cursor for the selected rows
            OPEN users FOR SELECT * FROM anonymous WHERE id = id1;

            -- Delete those rows
            DELETE FROM anonymous WHERE id = id1;

            -- Return the number of deleted rows
            GET DIAGNOSTICS id3 = ROW_COUNT;

            END;
            $$;
         */

    }

    public static void addNum() throws SQLException {
        Connection con = DBUtil.getConnection();
        CallableStatement cst = con.prepareCall("CALL add_proc(?, ?, ?)");
        cst.setInt(1, 100);
        cst.setInt(2, 200);
        cst.registerOutParameter(3, Types.INTEGER);
        cst.execute();
        int result = cst.getInt(3);
        System.out.println(result);


        /*
            CREATE OR REPLACE PROCEDURE add_proc(
            IN x INT,
            IN y INT,
            OUT z INT
            )
            LANGUAGE plpgsql
            AS $$
            BEGIN
            z := x + y;
            END;
            $$;
         */
    }

}
