/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cmc.edu.vn.bai1;

import java.util.TreeSet;

public class Bai1 {
    public static void main(String[] args) {
        // 1. Tạo một TreeSet và thêm các tên
        TreeSet<String> names = new TreeSet<>();
        names.add("John");
        names.add("Alice");
        names.add("Zack");
        names.add("Bob");

        // 2. In danh sách và quan sát thứ tự (sẽ được sắp xếp A-Z)
        System.out.println("Danh sách tên trong TreeSet (đã sắp xếp): " + names);

        // 3. Lấy phần tử đầu tiên và cuối cùng
        String first = names.first();
        String last = names.last();

        System.out.println("Phần tử đầu tiên (nhỏ nhất): " + first);
        System.out.println("Phần tử cuối cùng (lớn nhất): " + last);
    }
}