import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class FunctionsMain {

    public static void main(String[] args) throws SQLException {
        Connection con = DBUtil.getConnection();
        CallableStatement cst = con.prepareCall("{? = call get_sum_of_id(?,?)}");
        cst.setInt(2,5);
        cst.setInt(3,7);
        cst.registerOutParameter(1, Types.NUMERIC);
        cst.execute();
        java.math.BigDecimal sum = cst.getBigDecimal(1);
        System.out.println("Sum of id = " + sum.doubleValue());
    }
}
