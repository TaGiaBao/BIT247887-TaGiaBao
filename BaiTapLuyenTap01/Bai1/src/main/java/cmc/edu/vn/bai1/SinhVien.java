package cmc.edu.vn.bai1;
public class SinhVien {
    private String mssv;
    private String hoTen;
    private double gpa;

    public SinhVien(String mssv, String hoTen, double gpa) {
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.gpa = gpa;
    }

    // Getters
    public String getMssv() { return mssv; }
    public String getHoTen() { return hoTen; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return String.format("MSSV: %-10s | Tên: %-20s | GPA: %.2f", mssv, hoTen, gpa);
    }
}
    
