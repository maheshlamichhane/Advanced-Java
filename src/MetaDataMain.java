import java.sql.*;

public class MetaDataMain {

    public static void main(String[] args) throws SQLException {

        // Database MetaData
        databaseMetadata();

        // ResultSet MetaData
        resultSetMetaData();

        // Parameter Metadata
        parameterMetadata();


    }



    public static void databaseMetadata() throws SQLException {
        Connection con = DBUtil.getConnection();
        java.sql.DatabaseMetaData dbmd = con.getMetaData();
        System.out.println("Database Product Name:"+dbmd.getDatabaseProductName());
        System.out.println("Database Product Version:"+dbmd.getDatabaseProductVersion());
        System.out.println("Database Major Version:"+dbmd.getDatabaseMajorVersion());
        System.out.println("Database Minor Version::"+dbmd.getDatabaseMinorVersion());
        System.out.println("JDBC Major Version:"+dbmd.getJDBCMajorVersion());
        System.out.println("JDBC Minor Version:"+dbmd.getJDBCMinorVersion());
        System.out.println("Driver Name:"+dbmd.getDriverName());
        System.out.println("URL:"+dbmd.getURL());
        System.out.println("Username:"+dbmd.getUserName());
        System.out.println("Max Columns In Table:"+dbmd.getMaxColumnsInTable());
        System.out.println("Max Row Size:"+dbmd.getMaxRowSize());
        System.out.println("Max Statement Length:"+dbmd.getMaxStatementLength());
        System.out.println("Max Tables In Select:"+dbmd.getMaxTablesInSelect());
        System.out.println("Max Table Name Length:"+dbmd.getMaxTableNameLength());
        System.out.println("SQL Keywords:"+dbmd.getSQLKeywords());
        System.out.println("Numeric Functions:"+dbmd.getNumericFunctions());
        System.out.println("String Functions:"+dbmd.getStringFunctions());
        System.out.println("System Functions:"+dbmd.getSystemFunctions());
        System.out.println("Supports Full Outer Joins:"+dbmd.supportsFullOuterJoins());
        System.out.println("Supports Stored Procedures:"+dbmd.supportsStoredProcedures());
        con.close();
    }

    public static void resultSetMetaData() throws SQLException {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from anonymous");
        ResultSetMetaData rsmd = rs.getMetaData();
        for(int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.println("Column Number="+i);
            System.out.println("Column Name="+rsmd.getColumnName(i));
            System.out.println("Column Type="+rsmd.getColumnType(i));
            System.out.println("Column Size="+rsmd.getColumnDisplaySize(i));
            System.out.println("------------------------------------");
        }
    }

    public static void parameterMetadata() throws SQLException {
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into anonymous values(?,?,?)");
        pst.setInt(1,34);
        pst.setString(2,"jljl");
        pst.setString(3,"lskjl");
        ParameterMetaData pmd = pst.getParameterMetaData();
        for(int i=1; i <= pmd.getParameterCount(); i++) {
            System.out.println("Parameter Count="+i);
            System.out.println("Parameter Mode="+pmd.getParameterMode(i));
            System.out.println("Parameter Type="+pmd.getParameterType(i));
            System.out.println("Parameter Type Name="+pmd.getParameterTypeName(i));
            System.out.println("Precision="+pmd.getPrecision(i));
            System.out.println("Scale="+pmd.getScale(i));
            System.out.println("Nullable="+pmd.isNullable(i));
            System.out.println("IsSigned="+pmd.isSigned(i));
            System.out.println("===============================================");
        }
    }

}
