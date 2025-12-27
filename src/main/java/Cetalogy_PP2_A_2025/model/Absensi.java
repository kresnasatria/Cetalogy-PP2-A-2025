package Cetalogy_PP2_A_2025.model;

import java.sql.Date;

public class Absensi {

    private int id;
    private String nim;
    private Date tanggal;
    private String status;

    // Constructor kosong
    public Absensi() {}

    // Constructor dengan parameter
    public Absensi(int id, String nim, Date tanggal, String status) {
        this.id = id;
        this.nim = nim;
        this.tanggal = tanggal;
        this.status = status;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
