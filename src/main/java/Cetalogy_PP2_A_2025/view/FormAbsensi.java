package Cetalogy_PP2_A_2025.view;

import Cetalogy_PP2_A_2025.controller.AbsensiController;
import Cetalogy_PP2_A_2025.util.ExportPDF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormAbsensi extends JFrame {

    private JTextField txtNim, txtTanggal;
    private JComboBox<String> cmbStatus;
    private JTable table;
    private AbsensiController controller = new AbsensiController();
    private Dashboard dashboard;

    // ===== CONSTRUCTOR (TERIMA DASHBOARD) =====
    public FormAbsensi(Dashboard dashboard) {
        this.dashboard = dashboard;

        setTitle("Form Absensi Mahasiswa");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // ===== INPUT FIELD =====
        txtNim = new JTextField();
        txtTanggal = new JTextField("2025-01-01");

        // ✅ STATUS DITAMBAHKAN: SAKIT
        cmbStatus = new JComboBox<>(new String[]{
                "Hadir",
                "Izin",
                "Sakit",
                "Alfa"
        });

        // ===== BUTTON =====
        JButton btnSimpan = new JButton("Simpan");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus  = new JButton("Hapus");
        JButton btnPdf    = new JButton("Export PDF");

        // ===== TABLE =====
        table = new JTable();

        // ===== PANEL INPUT =====
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Absensi Mahasiswa"));

        panelInput.add(new JLabel("NIM"));
        panelInput.add(txtNim);
        panelInput.add(new JLabel("Tanggal (YYYY-MM-DD)"));
        panelInput.add(txtTanggal);
        panelInput.add(new JLabel("Status"));
        panelInput.add(cmbStatus);

        panelInput.add(btnSimpan);
        panelInput.add(btnUpdate);

        // ===== PANEL BAWAH =====
        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBawah.add(btnHapus);
        panelBawah.add(btnPdf);

        // ===== LAYOUT =====
        add(panelInput, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        // ===== EVENT SIMPAN =====
        btnSimpan.addActionListener(e -> {
            try {
                controller.simpan(
                        txtNim.getText(),
                        txtTanggal.getText(),
                        cmbStatus.getSelectedItem().toString()
                );
                refresh();
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // ===== EVENT UPDATE =====
        btnUpdate.addActionListener(e -> {
            try {
                int row = table.getSelectedRow();
                if (row == -1)
                    throw new Exception("Pilih data yang akan diupdate!");

                int id = Integer.parseInt(table.getValueAt(row, 0).toString());

                controller.update(
                        id,
                        txtTanggal.getText(),
                        cmbStatus.getSelectedItem().toString()
                );
                refresh();
                clearForm();
                txtNim.setEditable(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // ===== EVENT HAPUS =====
        btnHapus.addActionListener(e -> {
            try {
                int row = table.getSelectedRow();
                if (row == -1)
                    throw new Exception("Pilih data yang akan dihapus!");

                int id = Integer.parseInt(table.getValueAt(row, 0).toString());

                int konfirmasi = JOptionPane.showConfirmDialog(
                        this,
                        "Yakin ingin menghapus data absensi ini?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );

                if (konfirmasi == JOptionPane.YES_OPTION) {
                    controller.hapus(id);
                    refresh();
                    clearForm();
                    txtNim.setEditable(true);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // ===== EVENT KLIK TABLE =====
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtNim.setText(table.getValueAt(row, 1).toString());
                txtTanggal.setText(table.getValueAt(row, 2).toString());
                cmbStatus.setSelectedItem(table.getValueAt(row, 3).toString());
                txtNim.setEditable(false);
            }
        });

        // ===== EXPORT PDF =====
        btnPdf.addActionListener(e -> ExportPDF.export(table));

        // ===== SAAT FORM DITUTUP → REFRESH DASHBOARD =====
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dashboard.refreshAll();
            }
        });

        // ===== LOAD DATA =====
        refresh();
    }

    // ===== REFRESH TABLE =====
    private void refresh() {
        try {
            controller.tampil(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ===== CLEAR FORM =====
    private void clearForm() {
        txtNim.setText("");
        txtTanggal.setText("");
        cmbStatus.setSelectedIndex(0);
    }
}