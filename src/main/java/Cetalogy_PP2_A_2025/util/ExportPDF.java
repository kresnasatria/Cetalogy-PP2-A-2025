package Cetalogy_PP2_A_2025.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.io.FileOutputStream;

public class ExportPDF {

    public static void export(JTable table) {
        try {
            JFileChooser fc = new JFileChooser();
            fc.showSaveDialog(null);

            Document doc = new Document();
            PdfWriter.getInstance(doc,
                new FileOutputStream(fc.getSelectedFile() + ".pdf"));
            doc.open();

            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

            for (int i = 0; i < table.getColumnCount(); i++)
                pdfTable.addCell(table.getColumnName(i));

            for (int r = 0; r < table.getRowCount(); r++)
                for (int c = 0; c < table.getColumnCount(); c++)
                    pdfTable.addCell(table.getValueAt(r, c).toString());

            doc.add(pdfTable);
            doc.close();

            JOptionPane.showMessageDialog(null, "Export PDF berhasil!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
