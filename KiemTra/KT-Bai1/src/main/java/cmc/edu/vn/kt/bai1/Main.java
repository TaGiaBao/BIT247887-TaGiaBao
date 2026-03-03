/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmc.edu.vn.kt.bai1;


 public class Main {

    public static void main(String[] args) {

        StudentManager<Student> manager = new StudentManager<>();

        manager.add(new Student("SV01", "Nguyen Van A", 8.2));
        manager.add(new Student("SV02", "Nguyen Van B", 4.6));
        manager.add(new Student("SV03", "Nguyen Van C", 5.9));

        for (Student s : manager.getAll()) {
            System.out.println(s);
        }
    }
}
