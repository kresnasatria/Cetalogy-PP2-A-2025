package Cetalogy_PP2_A_2025.view;

import Cetalogy_PP2_A_2025.controller.MahasiswaController;
import Cetalogy_PP2_A_2025.controller.NilaiController;
import Cetalogy_PP2_A_2025.controller.AbsensiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Dashboard extends JFrame {

    private JTable tblMahasiswa = new JTable();
    private JTable tblNilai = new JTable();
    private JTable tblAbsensi = new JTable();

    private MahasiswaController mhsC = new MahasiswaController();
    private NilaiController nilaiC = new NilaiController();
    private AbsensiController absC = new AbsensiController();

    public Dashboard() {
        setTitle("Dashboard Akademik Mahasiswa");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== PANEL TABLE =====
        JPanel panelTable = new JPanel(new GridLayout(3, 1));

        panelTable.add(wrap("DATA MAHASISWA", tblMahasiswa));
        panelTable.add(wrap("DATA NILAI", tblNilai));
        panelTable.add(wrap("DATA ABSENSI", tblAbsensi));

        // ===== PANEL BUTTON =====
        JButton btnMhs = new JButton("Kelola Mahasiswa");
        JButton btnNilai = new JButton("Kelola Nilai");
        JButton btnAbsen = new JButton("Kelola Absensi");

        JPanel panelButton = new JPanel();
        panelButton.add(btnMhs);
        panelButton.add(btnNilai);
        panelButton.add(btnAbsen);

        add(panelTable, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        // ===== EVENT BUTTON =====
        btnMhs.addActionListener(e -> new FormMahasiswa(this).setVisible(true));
        btnNilai.addActionListener(e -> new FormNilai(this).setVisible(true));
        btnAbsen.addActionListener(e -> new FormAbsensi(this).setVisible(true));

        // ===== LOAD DATA AWAL =====
        refreshAll();

        // ===== AUTO REFRESH SAAT DASHBOARD AKTIF =====
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                refreshAll();
            }
        });
    }

    // ===== METHOD REFRESH (KUNCI UTAMA) =====
    public void refreshAll() {
        try {
            mhsC.tampil(tblMahasiswa);
            nilaiC.tampil(tblNilai);
            absC.tampil(tblAbsensi);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ===== HELPER PANEL DENGAN JUDUL =====
    private JPanel wrap(String title, JTable table) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }
}