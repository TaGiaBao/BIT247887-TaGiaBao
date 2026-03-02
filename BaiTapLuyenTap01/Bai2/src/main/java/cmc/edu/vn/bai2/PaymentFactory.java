/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmc.edu.vn.bai2;

public class PaymentFactory {
    public static Payment getPaymentMethod(String type) {
        if (type == null) return null;
        
        // Chuyển về chữ hoa để so sánh cho chính xác        
        switch (type.toUpperCase()) {
            case "CREDIT_CARD":
                return new CreditCardPayment();
            case "PAYPAL":
                return new PayPalPayment();
            case "CASH":
                return new CashPayment();
            default:
                throw new IllegalArgumentException("Hinh Thuc Thanh Toan '" + type + "' không hỗ trợ.");
        }
    }
}