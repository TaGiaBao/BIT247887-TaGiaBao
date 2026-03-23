public class bai1 {
    public static <E> void printArray(E[] arr) {
        System.out.println("Noi dung mang:");
        for (E element : arr) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        // Test với mảng Integer
        System.out.println("=== Test voi mang Integer ===");
        Integer[] intArray = {10, 20, 30, 40, 50};
        printArray(intArray);

        System.out.println("\n=== Test voi mang String ===");
        String[] stringArray = {"Java", "Generic", "Method", "Demo"};
        printArray(stringArray);

        // Test thêm với mảng Double
        System.out.println("\n=== Test voi mang Double ===");
        Double[] doubleArray = {1.5, 2.7, 3.9, 4.1};
        printArray(doubleArray);
    }
}