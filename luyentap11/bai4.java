import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class bai4 extends JFrame {
    private JTextField displayField;
    private double firstNumber = 0;
    private String operator = "";
    private boolean isNewNumber = true;

    public bai4() {
        setTitle("Calculator - Máy Tính");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Tạo panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        // === Panel Display ===
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBackground(new Color(50, 50, 50));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        displayField = new JTextField("0");
        displayField.setFont(new Font("Arial", Font.BOLD, 36));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        displayField.setBackground(new Color(50, 50, 50));
        displayField.setForeground(Color.WHITE);
        displayField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        displayPanel.add(displayField, BorderLayout.CENTER);

        // === Panel Buttons ===
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Định nghĩa các nút
        String[] buttons = {
                "7", "8", "9", "÷",
                "4", "5", "6", "×",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "←", "%", "√"
        };

        for (String btnText : buttons) {
            JButton btn = createButton(btnText);
            buttonPanel.add(btn);
        }

        // Thêm các panel vào main panel
        mainPanel.add(displayPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Thêm main panel vào frame
        add(mainPanel);
        setVisible(true);
    }

    // Phương thức tạo nút
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setFocusPainted(false);

        // Đặt màu cho các loại nút khác nhau
        if (text.matches("[0-9.]")) {
            btn.setBackground(new Color(100, 100, 100));
            btn.setForeground(Color.WHITE);
        } else if (text.equals("=")) {
            btn.setBackground(new Color(76, 175, 80));
            btn.setForeground(Color.WHITE);
        } else if (text.equals("C") || text.equals("←")) {
            btn.setBackground(new Color(244, 67, 54));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(33, 150, 243));
            btn.setForeground(Color.WHITE);
        }

        btn.addActionListener(new CalculatorActionListener(text));
        return btn;
    }

    // Lớp xử lý sự kiện nút bấm
    private class CalculatorActionListener implements ActionListener {
        private String buttonText;

        public CalculatorActionListener(String text) {
            this.buttonText = text;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String currentDisplay = displayField.getText();

            if (buttonText.matches("[0-9]")) {
                // Nút số
                if (isNewNumber) {
                    displayField.setText(buttonText);
                    isNewNumber = false;
                } else {
                    if (!currentDisplay.equals("0")) {
                        displayField.setText(currentDisplay + buttonText);
                    } else {
                        displayField.setText(buttonText);
                    }
                }
            } else if (buttonText.equals(".")) {
                // Nút dấu phẩy
                if (!currentDisplay.contains(".")) {
                    displayField.setText(currentDisplay + ".");
                    isNewNumber = false;
                }
            } else if (buttonText.matches("[+\\-×÷%]")) {
                // Nút phép toán
                double currentNumber = Double.parseDouble(currentDisplay);

                if (!operator.isEmpty()) {
                    double result = performOperation(firstNumber, currentNumber, operator);
                    displayField.setText(String.valueOf(result));
                    firstNumber = result;
                } else {
                    firstNumber = currentNumber;
                }

                operator = buttonText;
                isNewNumber = true;
            } else if (buttonText.equals("=")) {
                // Nút bằng
                double currentNumber = Double.parseDouble(currentDisplay);
                double result = performOperation(firstNumber, currentNumber, operator);
                displayField.setText(String.valueOf(result));
                operator = "";
                isNewNumber = true;
            } else if (buttonText.equals("C")) {
                // Nút xóa toàn bộ
                displayField.setText("0");
                firstNumber = 0;
                operator = "";
                isNewNumber = true;
            } else if (buttonText.equals("←")) {
                // Nút xóa một ký tự
                if (currentDisplay.length() > 1) {
                    displayField.setText(currentDisplay.substring(0, currentDisplay.length() - 1));
                } else {
                    displayField.setText("0");
                    isNewNumber = true;
                }
            } else if (buttonText.equals("√")) {
                // Căn bậc 2
                double number = Double.parseDouble(currentDisplay);
                double result = Math.sqrt(number);
                displayField.setText(String.valueOf(result));
                isNewNumber = true;
            }
        }
    }

    // Phương thức thực hiện phép toán
    private double performOperation(double firstNum, double secondNum, String op) {
        switch (op) {
            case "+":
                return firstNum + secondNum;
            case "-":
                return firstNum - secondNum;
            case "×":
                return firstNum * secondNum;
            case "÷":
                if (secondNum != 0) {
                    return firstNum / secondNum;
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể chia cho 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            case "%":
                return firstNum % secondNum;
            default:
                return secondNum;
        }
    }

    // Hàm main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new bai4());
    }
}
