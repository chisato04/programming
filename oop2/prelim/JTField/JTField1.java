import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class JTField1 {
    public static void main(String[] args)
    {
        JFrame Frame = new JFrame("Text Display");
        Frame.setSize(400, 200);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField(20);
        textField.setName("textField");

        JLabel label = new JLabel();
        label.setName("label");

        JButton displayButton = new JButton("Display Text");
        displayButton.setName("displayButton");

        displayButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredText = textField.getText();
                label.setText(enteredText);
            }
        });

        Frame.setLayout(new FlowLayout());
        Frame.add(textField);
        Frame.add(displayButton);
        Frame.add(label);
        Frame.setVisible(true);
    }
}