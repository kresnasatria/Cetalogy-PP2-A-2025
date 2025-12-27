package Cetalogy_PP2_A_2025.controller;

import Cetalogy_PP2_A_2025.config.Koneksi;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MahasiswaController {

    public void simpan(String nim, String nama, String jurusan, int angkatan) throws Exception {
        if (nim.isEmpty() || nama.isEmpty())
            throw new Exception("NIM dan Nama wajib diisi!");

        String sql = "INSERT INTO mahasiswa VALUES (NULL,?,?,?,?)";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, nim);
        ps.setString(2, nama);
        ps.setString(3, jurusan);
        ps.setInt(4, angkatan);
        ps.executeUpdate();
    }

    public void tampil(JTable table) throws Exception {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"NIM","Nama","Jurusan","Angkatan"}, 0);

        Statement st = Koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM mahasiswa");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("nim"),
                rs.getString("nama"),
                rs.getString("jurusan"),
                rs.getInt("angkatan")
            });
        }
        table.setModel(model);
    }
    
    public void update(String nim, String nama, String jurusan, int angkatan) throws Exception {
        if (nim.isEmpty() || nama.isEmpty())
            throw new Exception("NIM dan Nama wajib diisi!");

        String sql = "UPDATE mahasiswa SET nama=?, jurusan=?, angkatan=? WHERE nim=?";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, nama);
        ps.setString(2, jurusan);
        ps.setInt(3, angkatan);
        ps.setString(4, nim);
        ps.executeUpdate();
    }

    public void hapus(String nim) throws Exception {
        String sql = "DELETE FROM mahasiswa WHERE nim=?";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, nim);
        ps.executeUpdate();
    }
}
