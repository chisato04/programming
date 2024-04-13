import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTextField1
{
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Text Repeater");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo (null);

		JPanel panel = new JPanel (new GridLayout(4,1));

		JTextField textField = new JTextField();
		textField.setName("textField");

		JTextField numTextField = new JTextField();
		numTextField.setName("numberTextField");

		JTextArea result = new JTextArea();
		result.setName ("resultTextArea");
		result.setEditable(false);

		JButton displayTextButton = new JButton ("Display Text");
		displayTextButton.setName ("displayTextButton");
		displayTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                String repeatCountText = numTextField.getText();
                boolean validInput = true;
                for (char c : repeatCountText.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        validInput = false;
                        break;
                    }
                }
                if (validInput && !repeatCountText.isEmpty()) {
                    int repeatCount = 0;
                    for (int i = 0; i < repeatCountText.length(); i++) {
                        repeatCount = repeatCount * 10 + (repeatCountText.charAt(i) - '0');
                    }

                    StringBuilder repeatedText = new StringBuilder();
                    for (int i = 0; i < repeatCount; i++) {
                        repeatedText.append(text);
                        if (i != repeatCount - 1) {
                            repeatedText.append(" ");
                        }
                    }

                    result.setText(repeatedText.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter a valid number.");
                }
            }
        });

		panel.add(textField);
		panel.add(numTextField);
		panel.add(displayTextButton);
		panel.add(new JScrollPane(result));
		frame.getContentPane().add(panel);
		frame.setVisible(true);

	}

}