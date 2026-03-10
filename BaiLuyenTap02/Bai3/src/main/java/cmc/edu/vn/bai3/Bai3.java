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
        CompletableFuture<String> verifyCustomer = CompletableFuture.supplyAsync(() -> {
            System.out.println("-> Da xac thuc thong tin khach hang.");
            return "Khach hang hop le";
        });

        CompletableFuture<String> exportTicket = CompletableFuture.supplyAsync(() -> {
            System.out.println("-> Da xuat ve.");
            return "Ve so #123";
        });

        CompletableFuture<Void> finalStep = verifyCustomer.thenCombine(exportTicket, (res1, res2) -> {
            System.out.println("\n[THONG BAO] HOAN THANH TAT CA!");
            System.out.println("ket qua: " + res1 + " & " + res2);
            return null;
        });

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