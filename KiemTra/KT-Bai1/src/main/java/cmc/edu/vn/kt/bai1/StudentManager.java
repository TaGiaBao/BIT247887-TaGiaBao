/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmc.edu.vn.kt.bai1;

import java.util.ArrayList;
import java.util.List;
        
public class StudentManager<T> {
    private List<T> data;
    public StudentManager() {
        data = new ArrayList<>();
    }
    public void add (T iteam){
        data.add(iteam);
    }
    public List<T> getAll(){
        return data;
    }
}
