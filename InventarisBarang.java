import java.sql.*;
import java.util.Scanner;

public class InventarisBarang {
    private static final String URL = "jdbc:mysql://localhost:3306/inventaris_db";
    private static final String USER = "root";  // Sesuaikan dengan user MySQL Anda
    private static final String PASSWORD = "";  // Sesuaikan dengan password MySQL Anda
    private static Connection conn;

    public static void main(String[] args) {
        conn = getConnection();
        if (conn == null) {
            System.out.println("Gagal terhubung ke database!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== MENU INVENTARIS BARANG ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Tampilkan Barang");
            System.out.println("3. Update Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Buang newline

            switch (pilihan) {
                case 1:
                    tambahBarang(scanner);
                    break;
                case 2:
                    tampilkanBarang();
                    break;
                case 3:
                    updateBarang(scanner);
                    break;
                case 4:
                    hapusBarang(scanner);
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // Koneksi ke database
    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Tambah barang
    private static void tambahBarang(Scanner scanner) {
        System.out.print("Masukkan Nama Barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Kategori: ");
        String kategori = scanner.nextLine();
        System.out.print("Masukkan Jumlah: ");
        int jumlah = scanner.nextInt();
        System.out.print("Masukkan Harga: ");
        double harga = scanner.nextDouble();

        String sql = "INSERT INTO barang (nama_barang, kategori, jumlah, harga) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nama);
            stmt.setString(2, kategori);
            stmt.setInt(3, jumlah);
            stmt.setDouble(4, harga);
            stmt.executeUpdate();
            System.out.println("Barang berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tampilkan semua barang
    private static void tampilkanBarang() {
        String sql = "SELECT * FROM barang";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n=== DAFTAR BARANG ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nama: " + rs.getString("nama_barang") +
                        ", Kategori: " + rs.getString("kategori") + ", Jumlah: " + rs.getInt("jumlah") +
                        ", Harga: " + rs.getDouble("harga"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update barang
    private static void updateBarang(Scanner scanner) {
        System.out.print("Masukkan ID barang yang ingin diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Masukkan Nama Baru: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Kategori Baru: ");
        String kategori = scanner.nextLine();
        System.out.print("Masukkan Jumlah Baru: ");
        int jumlah = scanner.nextInt();
        System.out.print("Masukkan Harga Baru: ");
        double harga = scanner.nextDouble();

        String sql = "UPDATE barang SET nama_barang=?, kategori=?, jumlah=?, harga=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nama);
            stmt.setString(2, kategori);
            stmt.setInt(3, jumlah);
            stmt.setDouble(4, harga);
            stmt.setInt(5, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Barang berhasil diperbarui!");
            } else {
                System.out.println("Barang dengan ID tersebut tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hapus barang
    private static void hapusBarang(Scanner scanner) {
        System.out.print("Masukkan ID barang yang ingin dihapus: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM barang WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Barang berhasil dihapus!");
            } else {
                System.out.println("Barang dengan ID tersebut tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
