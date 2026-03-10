/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cmc.edu.vn.bai4;

/**
 *
 * @author Admin
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai4 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chuyển đổi nhiệt độ");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Nhãn và TextField cho độ C
        JLabel labelC = new JLabel("Nhập độ C:");
        labelC.setBounds(20, 20, 100, 25);
        frame.add(labelC);

        JTextField txtC = new JTextField();
        txtC.setBounds(120, 20, 150, 25);
        frame.add(txtC);

        // Nút bấm tính toán
        JButton btnConvert = new JButton("Chuyển sang độ F");
        btnConvert.setBounds(20, 60, 250, 30);
        frame.add(btnConvert);

        // Nhãn hiển thị kết quả
        JLabel lblResult = new JLabel("Kết quả: ");
        lblResult.setBounds(20, 100, 250, 25);
        frame.add(lblResult);

        // Xử lý sự kiện khi nhấn nút
        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double celsius = Double.parseDouble(txtC.getText());
                    // Công thức: $F = C \times 9/5 + 32$
                    double fahrenheit = (celsius * 9 / 5) + 32;
                    lblResult.setText("Kết quả: " + String.format("%.2f", fahrenheit) + " °F");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Vui lòng nhập số hợp lệ!");
                }
            }
        });

        frame.setVisible(true);
    }
}