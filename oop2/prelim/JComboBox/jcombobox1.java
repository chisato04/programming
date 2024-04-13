import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class jcombobox1 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Calculator");
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField num1TextField = new JTextField();
        JTextField num2TextField = new JTextField();
        num1TextField.setName("num1TextField");
        num2TextField.setName("num2TextField");

        num1TextField.setSize(100, 50);
        num2TextField.setSize(100, 50);

        JComboBox<String> operationsComboBox = new JComboBox<>();
        operationsComboBox.addItem("+");
        operationsComboBox.addItem("-");
        operationsComboBox.addItem("*");
        operationsComboBox.addItem("/");
        operationsComboBox.setName("operationsComboBox");

        JButton computeButton = new JButton("Compute Result");
        computeButton.setName("computeButton");

        JLabel resultLabel = new JLabel("");
        resultLabel.setName("resultLabel");

        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int num1 = Integer.parseInt(num1TextField.getText());
                    int num2 = Integer.parseInt(num2TextField.getText());
                    String operator = (String) operationsComboBox.getSelectedItem();

                    int result = 0;
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                throw new ArithmeticException("Division by zero");
                            }
                            result = num1 / num2;
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid operator");
                    }

                    resultLabel.setText("" + result);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter numbers.");
                } catch (ArithmeticException ex) {
                    resultLabel.setText(ex.getMessage());
                }
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(num1TextField);
        frame.add(operationsComboBox);
        frame.add(num2TextField);
        frame.add(computeButton);
        frame.add(resultLabel);
        frame.setVisible(true);
    }
}