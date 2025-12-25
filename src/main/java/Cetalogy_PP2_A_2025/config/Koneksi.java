package Cetalogy_PP2_A_2025.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_mahasiswa",
                "root", ""
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
