/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cmc.edu.vn.bai3;

import java.util.concurrent.*;

public class BankingService {
    // Tạo ThreadPool riêng để quản lý tài nguyên, không dùng luồng mặc định của hệ thống
    private final Executor executor = Executors.newFixedThreadPool(3);

    public CompletableFuture<String> processAsync(Transaction tx) {
        return CompletableFuture.supplyAsync(() -> verifyCustomer(tx), executor)
                .thenCompose(v -> checkBalanceAsync(tx))
                .thenApply(v -> executeTransfer(tx))
                .handle((res, ex) -> {
                    if (ex != null) return "Giao Dich " + tx.txId() + " That Bai: " + ex.getCause().getMessage();
                    return "OK " + res;
                });
    }

    private String verifyCustomer(Transaction tx) {
        delay(1500); // Giả lập xác thực
        return "Verified";
    }

    private CompletableFuture<String> checkBalanceAsync(Transaction tx) {
        return CompletableFuture.supplyAsync(() -> {
            delay(1000);
            if (tx.amount() > 1000) throw new BankingException("So du khong du!");
            return "Balance OK";
        }, executor);
    }

    private String executeTransfer(Transaction tx) {
        delay(2000);
        return "Giao Dich  " + tx.txId() + " thanh cong " + tx.customerName();
    }

    private void delay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}