package Cetalogy_PP2_A_2025.controller;

import Cetalogy_PP2_A_2025.config.Koneksi;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class NilaiController {

    public void simpan(String nim, String mk, double nilai) throws Exception {
        if (nim.isEmpty() || mk.isEmpty())
            throw new Exception("Data tidak boleh kosong!");

        String sql = "INSERT INTO nilai VALUES (NULL,?,?,?)";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, nim);
        ps.setString(2, mk);
        ps.setDouble(3, nilai);
        ps.executeUpdate();
    }

    public void tampil(JTable table) throws Exception {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID","NIM","Mata Kuliah","Nilai"}, 0);

        Statement st = Koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM nilai");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("nim"),
                rs.getString("mata_kuliah"),
                rs.getDouble("nilai")
            });
        }
        table.setModel(model);
    }
    
    public void update(int id, String mk, double nilai) throws Exception {
        if (mk.isEmpty())
            throw new Exception("Mata kuliah tidak boleh kosong!");

        String sql = "UPDATE nilai SET mata_kuliah=?, nilai=? WHERE id=?";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setString(1, mk);
        ps.setDouble(2, nilai);
        ps.setInt(3, id);
        ps.executeUpdate();
    }

    public void hapus(int id) throws Exception {
        String sql = "DELETE FROM nilai WHERE id=?";
        PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}