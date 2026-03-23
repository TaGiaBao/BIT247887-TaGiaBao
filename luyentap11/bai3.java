import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Bài 3: Xử lý Object & Collectors

// Lớp Employee
class Employee {
    private int id;
    private String name;
    private double salary;

    // Constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', salary=%.2f}", id, name, salary);
    }
}

// Chương trình demo
public class bai3 {
    public static void main(String[] args) {
        System.out.println("========== DEMO OBJECT & COLLECTORS ==========\n");

        // 1. Tạo danh sách nhân viên (4 nhân viên với mức lương khác nhau)
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, " A", 800));
        employees.add(new Employee(2, " B", 1200));
        employees.add(new Employee(3, " C", 950));
        employees.add(new Employee(4, " D", 1500));

        // Hiển thị danh sách ban đầu
        System.out.println("--- Danh sách nhân viên ban đầu ---");
        employees.forEach(System.out::println);

        // 2. Xử lý với Stream & Collectors:
        //    - Lọc nhân viên có lương > 1000
        //    - Lấy tên của họ
        //    - Sắp xếp theo thứ tự Alphabet
        //    - Gom kết quả vào List

        System.out.println("\n--- Stream ---");
        List<String> namesHighSalary = employees.stream()
                .filter(emp -> emp.getSalary() > 1000)  // Lọc lương > 1000
                .map(Employee::getName)                 // Lấy tên
                .sorted()                               // Sắp xếp Alphabet
                .collect(Collectors.toList());          // Gom vào List

        System.out.println("nhan vien co luong  > 1000 (sap xep Alphabet):");
        namesHighSalary.forEach(name -> System.out.println("  - " + name));

        // Thống kê bổ sung
        System.out.println("\n--- thong ke bo sung ---");
        System.out.println("Tong nhan vien: " + employees.size());
        System.out.println("Nhan vien co luong > 1000: " + namesHighSalary.size());
        
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.printf("muc luong trung binh: %.2f\n", averageSalary);

        System.out.println("\n========== End ==========");
    }
}
