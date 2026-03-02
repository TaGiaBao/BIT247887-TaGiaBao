package cmc.edu.vn.bai2;

public class Main {
    public static void main(String[] args) {
        // Danh sách các phương thức thanh toán muốn kiểm tra
        String[] types = {"CREDIT_CARD", "PAYPAL", "CASH"};
        double amount = 250000.0; // Số tiền giả định

        System.out.println("He Thong Tu Dong Thanh Toan");
        System.out.println("------------------------------------------");

        for (String type : types) {
            try {
                // Factory tự động tạo đối tượng dựa trên tên trong mảng
                Payment p = PaymentFactory.getPaymentMethod(type);
                
                // Tự động gọi hàm xử lý
                p.processPayment(amount);
                
                System.out.println("------------------------------------------");
            } catch (Exception e) {
                System.out.println("Loi" + type + ": " + e.getMessage());
            }
        }
        
        System.out.println("Xong");
    }
}