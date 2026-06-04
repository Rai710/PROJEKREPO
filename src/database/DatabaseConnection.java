package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    // Sesuaikan konfigurasi ini dengan database lokal Anda
    private static final String URL = "jdbc:mysql://localhost:3306/kasir_db";
    private static final String USER = "root"; // Username default XAMPP
    private static final String PASSWORD = ""; // Password default XAMPP biasanya kosong

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Mendaftarkan driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Membuka koneksi
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi Database Berhasil!");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver JDBC tidak ditemukan!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Gagal terhubung ke Database!");
                JOptionPane.showMessageDialog(null, "Gagal terhubung ke database!\nPastikan MySQL sudah berjalan dan database 'kasir_db' sudah dibuat.", "Database Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        return connection;
    }
}
