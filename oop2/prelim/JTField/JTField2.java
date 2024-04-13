import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class JTField2
{
    public static void main(String[] args)
    {
        JFrame Frame = new JFrame("Text Display");
        Frame.setSize(400, 200);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField(20);
        textField.setName("textField");

        JLabel label = new JLabel();
        label.setName("label");

        JButton enableButton = new JButton("Enable");
        enableButton.setName("enableButton");

        JButton disableButton = new JButton("Disable");
        disableButton.setName("disableButton");

        disableButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textField.setEnabled(false);
            }
        });

        enableButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textField.setEnabled(true);
            }
        });

        Frame.setLayout(new FlowLayout());
        Frame.add(textField);
        Frame.add(enableButton);
        Frame.add(disableButton);
        Frame.add(label);
        Frame.setVisible(true);
    }
}