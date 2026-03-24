
@FunctionalInterface
interface MathOperation {
    int compute(int a, int b);
}

public class bai1 {
    public static void main(String[] args) {
        int a = 20;
        int b = 10;

        System.out.println("===== BAI 1: LAMBDA EXPRESSION VOI MATHOPERATION =====\n");

        // Lambda Expression: Phep cong (Addition)
        MathOperation addition = (x, y) -> x + y;
        System.out.println("Phep cong: " + a + " + " + b + " = " + addition.compute(a, b));

        // Lambda Expression: Phep tru (Subtraction)
        MathOperation subtraction = (x, y) -> x - y;
        System.out.println("Phep tru: " + a + " - " + b + " = " + subtraction.compute(a, b));

        // Lambda Expression: Phep nhan (Multiplication)
        MathOperation multiplication = (x, y) -> x * y;
        System.out.println("Phep nhan: " + a + " * " + b + " = " + multiplication.compute(a, b));

        // Lambda Expression: Phep chia (Division)
        MathOperation division = (x, y) -> {
            if (y == 0) {
                System.out.println("Loi: Khong the chia cho 0!");
                return 0;
            }
            return x / y;
        };
        System.out.println("Phep chia: " + a + " / " + b + " = " + division.compute(a, b));

        // Test them voi cac so khac
        System.out.println("\n===== TEST VOI CAC SO KHAC =====\n");
        int c = 15;
        int d = 3;
        
        System.out.println("Phep cong: " + c + " + " + d + " = " + addition.compute(c, d));
        System.out.println("Phep tru: " + c + " - " + d + " = " + subtraction.compute(c, d));
        System.out.println("Phep nhan: " + c + " * " + d + " = " + multiplication.compute(c, d));
        System.out.println("Phep chia: " + c + " / " + d + " = " + division.compute(c, d));

        // Test them voi cac so khac (chia het)
        System.out.println("\n===== TEST THEM =====\n");
        int e = 100;
        int f = 5;
        
        System.out.println("Phep cong: " + e + " + " + f + " = " + addition.compute(e, f));
        System.out.println("Phep tru: " + e + " - " + f + " = " + subtraction.compute(e, f));
        System.out.println("Phep nhan: " + e + " * " + f + " = " + multiplication.compute(e, f));
        System.out.println("Phep chia: " + e + " / " + f + " = " + division.compute(e, f));
        
        // Test chia cho 0
        System.out.println("\n===== TEST CHIA CHO 0 =====\n");
        System.out.println("Phep chia: " + a + " / 0 = " + division.compute(a, 0));
    }
}
