package cmc.edu.vn.bai1;
import java.util.ArrayList;
import java.util.Scanner;


public class QuanLySinhVien {
    public static void main(String[] args) {
        ArrayList<SinhVien> dsSV = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int luaChon;

        do {
            System.out.println("\n He Thong Quan Ly Sinh Vien");
            System.out.println("1. Add");
            System.out.println("2. Show");
            System.out.println("3. Search");
            System.out.println("4. Remove");
            System.out.println("0. Exit");
            System.out.print("Select: ");
            luaChon = sc.nextInt();
            sc.nextLine();

            switch (luaChon) {
                case 1:
                    System.out.print("MSSV: ");
                    String mssv = sc.nextLine();
                    System.out.print("Name: ");
                    String ten = sc.nextLine();
                    System.out.print("GPA: ");
                    double gpa = sc.nextDouble();
                    dsSV.add(new SinhVien(mssv, ten, gpa));
                    System.out.println("Ok!");
                    break;

                case 2:
                    System.out.println("\nDanh Sach Sinh Vien");
                    if (dsSV.isEmpty()) System.out.println("Null");
                    else {
                        for (SinhVien sv : dsSV) System.out.println(sv);
                    }
                    break;

                case 3:
                    System.out.print("Ten can tim: ");
                    String tenTim = sc.nextLine();
                    boolean timThay = false;
                    for (SinhVien sv : dsSV) {
                        if (sv.getHoTen().toLowerCase().contains(tenTim.toLowerCase())) {
                            System.out.println(sv);
                            timThay = true;
                        }
                    }
                    if (!timThay) System.out.println("Khong co: " + tenTim);
                    break;

                case 4:
                    System.out.print("MSSV muon xoa: ");
                    String mssvXoa = sc.nextLine();
                    boolean daXoa = dsSV.removeIf(sv -> sv.getMssv().equalsIgnoreCase(mssvXoa));
                    if (daXoa) System.out.println("Ok!.");
                    else System.out.println("Khong thay de xoa!.");
                    break;

                case 0:
                    System.out.println("GoodBye!");
                    break;
                default:
                    System.out.println("Error!");
            }
        } while (luaChon != 0);
    }
}