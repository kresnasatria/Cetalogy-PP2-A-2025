package Cetalogy_PP2_A_2025.view;

import Cetalogy_PP2_A_2025.controller.MahasiswaController;
import Cetalogy_PP2_A_2025.util.ExportPDF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormMahasiswa extends JFrame {

    private JTextField txtNim, txtNama, txtJurusan, txtAngkatan;
    private JTable table;
    private MahasiswaController controller = new MahasiswaController();
    private Dashboard dashboard;

    // ===== CONSTRUCTOR (TERIMA DASHBOARD) =====
    public FormMahasiswa(Dashboard dashboard) {
        this.dashboard = dashboard;

        setTitle("Form Data Mahasiswa");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // ===== INPUT FIELD =====
        txtNim = new JTextField();
        txtNama = new JTextField();
        txtJurusan = new JTextField();
        txtAngkatan = new JTextField();

        // ===== BUTTON =====
        JButton btnSimpan = new JButton("Simpan");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus  = new JButton("Hapus");
        JButton btnPdf    = new JButton("Export PDF");

        // ===== TABLE =====
        table = new JTable();

        // ===== PANEL INPUT =====
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data Mahasiswa"));

        panelInput.add(new JLabel("NIM"));
        panelInput.add(txtNim);
        panelInput.add(new JLabel("Nama"));
        panelInput.add(txtNama);
        panelInput.add(new JLabel("Jurusan"));
        panelInput.add(txtJurusan);
        panelInput.add(new JLabel("Angkatan"));
        panelInput.add(txtAngkatan);

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
                        txtNama.getText(),
                        txtJurusan.getText(),
                        Integer.parseInt(txtAngkatan.getText())
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
                controller.update(
                        txtNim.getText(),
                        txtNama.getText(),
                        txtJurusan.getText(),
                        Integer.parseInt(txtAngkatan.getText())
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

                String nim = table.getValueAt(row, 0).toString();

                int konfirmasi = JOptionPane.showConfirmDialog(
                        this,
                        "Yakin ingin menghapus mahasiswa ini?\nData nilai & absensi juga akan terhapus.",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );

                if (konfirmasi == JOptionPane.YES_OPTION) {
                    controller.hapus(nim);
                    refresh();
                    clearForm();
                    txtNim.setEditable(true);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // ===== EVENT KLIK TABEL =====
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtNim.setText(table.getValueAt(row, 0).toString());
                txtNama.setText(table.getValueAt(row, 1).toString());
                txtJurusan.setText(table.getValueAt(row, 2).toString());
                txtAngkatan.setText(table.getValueAt(row, 3).toString());
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

    // ===== METHOD REFRESH TABLE =====
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
        txtNama.setText("");
        txtJurusan.setText("");
        txtAngkatan.setText("");
    }
}
