/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cmc.edu.vn.bai3;

/**
 *
 * @author Admin
 */
import java.util.concurrent.CompletableFuture;

public class Bai3 {
    public static void main(String[] args) {
        // 1. Tác vụ xác thực thông tin khách hàng
        CompletableFuture<String> verifyCustomer = CompletableFuture.supplyAsync(() -> {
            simulateDelay(2000); // Giả lập chờ 2 giây
            System.out.println("-> Đã xác thực thông tin khách hàng.");
            return "Khách hàng hợp lệ";
        });

        // 2. Tác vụ xuất vé phim
        CompletableFuture<String> exportTicket = CompletableFuture.supplyAsync(() -> {
            simulateDelay(3000); // Giả lập chờ 3 giây
            System.out.println("-> Đã xuất vé phim.");
            return "Vé số #123";
        });

        // 3. Kết hợp các tác vụ bằng thenCombine()
        CompletableFuture<Void> finalStep = verifyCustomer.thenCombine(exportTicket, (res1, res2) -> {
            System.out.println("\n[THÔNG BÁO]: Hệ thống hoàn thành tất cả tác vụ!");
            System.out.println("Kết quả: " + res1 + " & " + res2);
            return null;
        });

        // Chờ tác vụ hoàn thành để xem kết quả (vì là bất đồng bộ)
        finalStep.join();
    }

    private static void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}