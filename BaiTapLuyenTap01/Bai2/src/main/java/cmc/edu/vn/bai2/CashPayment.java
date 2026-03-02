/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmc.edu.vn.bai2;

public class CashPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Loading: " + amount + " VND");
        System.out.println("Waiting.");
    }
}
