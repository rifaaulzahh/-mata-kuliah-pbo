import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/inventaris_db";
    private static final String USER = "root"; // Sesuaikan dengan user MySQL Anda
    private static final String PASSWORD = ""; // Sesuaikan dengan password MySQL Anda
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi ke database berhasil!");
            } catch (SQLException e) {
                System.out.println("Koneksi gagal! Periksa database MySQL.");
                e.printStackTrace();
            }
        }
        return conn;
    }
}
