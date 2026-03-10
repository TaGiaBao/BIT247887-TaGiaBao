/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cmc.edu.vn.bai1;

import java.util.TreeSet;

public class Bai1 {
    public static void main(String[] args) {
       
        TreeSet<String> names = new TreeSet<>();
        names.add("Bao");
        names.add("Ha");
        names.add("Duong");
        names.add("CTer");

      
        System.out.println("Danh sach ten trong TreeSet: " + names);

       
        String first = names.first();
        String last = names.last();

        System.out.println("Phan tu dau tien: " + first);
        System.out.println("Phan tu cuoi cung: " + last);
    }
}