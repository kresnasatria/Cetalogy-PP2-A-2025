package Cetalogy_PP2_A_2025.model;

public class Nilai {

    private int id;
    private String nim;
    private String mataKuliah;
    private double nilai;

    // Constructor kosong
    public Nilai() {}

    // Constructor dengan parameter
    public Nilai(int id, String nim, String mataKuliah, double nilai) {
        this.id = id;
        this.nim = nim;
        this.mataKuliah = mataKuliah;
        this.nilai = nilai;
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

    public String getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }
    
}
