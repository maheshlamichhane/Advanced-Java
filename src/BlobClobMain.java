import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlobClobMain {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        // Inserting text file into db
        insertClobType();

        // Retrieving the text file data from db
        retrievingClobType();

        // Inserting binary data into db
        insertingBlobType();

        // Retrieving the binary data from db
        retrievingBlobType();


    }

    public static void retrievingBlobType() throws SQLException, FileNotFoundException {
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from person");
        try (FileOutputStream fos = new FileOutputStream("hello.jpg")) {
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString(1);
                System.out.println("Name=" + name);
                InputStream in = rs.getBinaryStream(2);
                byte[] buffer = new byte[1024];
                while (in.read(buffer) > 0) {
                    fos.write(buffer);
                }
                fos.flush();
                System.out.println("Open hello.jpg file read the file");

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertingBlobType() throws SQLException, FileNotFoundException {
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into person values(?,?)");
        File f = new File("Screenshot.png");
        FileInputStream fis = new FileInputStream(f);
        pst.setString(1,"screenshot");
        pst.setBinaryStream(2,fis);
        int result = pst.executeUpdate();
        if(result == 0) {
            System.out.println("Data Insert Not Successful");
        }
        else {
            System.out.println("Data Insert Successful");
        }
    }

    public static void retrievingClobType() throws SQLException {
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from animal");
        try (FileWriter fw = new FileWriter("text-output.txt")) {
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString(1);
                System.out.println("Name=" + name);
                Reader r = rs.getCharacterStream(2);
                char[] buffer = new char[1024];
                while (r.read(buffer) > 0) {
                    fw.write(buffer);
                }
                fw.flush();
                System.out.println("Open text-output.txt file read the file");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void insertClobType() throws FileNotFoundException, SQLException {
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into animal values(?,?)");
        File f = new File("Screenshot.txt");
        FileReader fis = new FileReader(f);
        pst.setString(1,"screen");
        pst.setCharacterStream(2,fis);
        int result = pst.executeUpdate();
        if(result == 0) {
            System.out.println("Data Insert Not Successful");
        }
        else {
            System.out.println("Data Insert Successful");
        }
    }
}
