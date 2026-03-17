/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmc.edu.vn.bai3;


public class MainApp {
    public static void main(String[] args) {
        BankingService service = new BankingService();

        // Tạo danh sách giao dịch giả lập
        Transaction[] list = {
            new Transaction("TX01", "Gia Bao", 500.0),
            new Transaction("TX02", "Brother", 2500.0) // Sẽ gây lỗi
        };

        System.out.println(">>> HE THONG DANG XU LY ...");

        for (Transaction tx : list) {
            service.processAsync(tx).thenAccept(System.out::println);
        }

        // Giữ main thread để đợi kết quả bất đồng bộ
        try { Thread.sleep(6000); } catch (InterruptedException e) {}
    }
}