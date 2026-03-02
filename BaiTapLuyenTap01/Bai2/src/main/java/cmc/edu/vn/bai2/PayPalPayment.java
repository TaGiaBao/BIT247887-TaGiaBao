/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmc.edu.vn.bai2;

public class PayPalPayment implements Payment{
    @Override
    public void processPayment(double amount) {
        System.out.println("[Hinh Thuc Thanh Toan ] PayPal Loading:"+ amount+"VND");
        System.out.println("Ngon Luon!.");
    }
}
