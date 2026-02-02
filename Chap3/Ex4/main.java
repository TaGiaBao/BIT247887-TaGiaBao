import java.util.concurrent.CompletableFuture;

public class main {
    public static void main(String[] args) {

        CompletableFuture<Void> checkProduct = CompletableFuture.runAsync(() -> {
            System.out.println("Dang kiem tra san pham...");
            sleep(2000);
            System.out.println("Kiem tra san pham xong!");
        });

        CompletableFuture<Void> payment = CompletableFuture.runAsync(() -> {
            System.out.println("Dang thanh toan...");
            sleep(3000);
            System.out.println("Thanh toan xong!");
        });

        CompletableFuture<Void> shipping = CompletableFuture.runAsync(() -> {
            System.out.println("Dang van chuyen...");
            sleep(4000);
            System.out.println("Van chuyen xong!");
        });

        CompletableFuture.allOf(checkProduct, payment, shipping)
                .thenRun(() -> {
                    System.out.println("Don hang da duoc xu ly hoan tat!");
                })
                .join();
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

