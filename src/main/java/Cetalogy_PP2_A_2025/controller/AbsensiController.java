package Cetalogy_PP2_A_2025.controller;

import Cetalogy_PP2_A_2025.config.Koneksi;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AbsensiController {

    public void simpan(String nim, String tanggal, String status) throws Exception {
        if (nim.isEmpty())
            throw new Exception("NIM wajib diisi!");

        String sql = "INSERT INTO absensi VALUES (NULL,?,?,?)";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, nim);
        ps.setString(2, tanggal);
        ps.setString(3, status);
        ps.executeUpdate();
    }

    public void tampil(JTable table) throws Exception {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID","NIM","Tanggal","Status"}, 0);

        Statement st = Koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM absensi");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("nim"),
                rs.getDate("tanggal"),
                rs.getString("status")
            });
        }
        table.setModel(model);
    }
    
    public void update(int id, String tanggal, String status) throws Exception {
        String sql = "UPDATE absensi SET tanggal=?, status=? WHERE id=?";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, tanggal);
        ps.setString(2, status);
        ps.setInt(3, id);
        ps.executeUpdate();
    }

    public void hapus(int id) throws Exception {
        String sql = "DELETE FROM absensi WHERE id=?";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}