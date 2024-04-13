
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jpass2
{

	public jpass2 ()
	{
		JLabel Lbl = new JLabel("Username:");
		Lbl.setName("TextLabel");
		Lbl.setBounds(30, 50, 200, 30);

		JTextField Username = new JTextField(20);
		Username.setName("usernameTextField");
		Username.setBounds(100, 50, 200, 30);

		JLabel Label = new JLabel("Password:");
		Label.setName("label");
		Label.setBounds(30, 100, 200, 30);

		JPasswordField Password = new JPasswordField (20);
		Password.setName ("passwordTextField");
		Password.setBounds(100, 100, 200, 30);
		Password.setEchoChar('*');

		JButton loginButton = new JButton("Login");
		loginButton.setName("loginButton");
		loginButton.setBounds(100, 180, 120, 50);
		loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	  String username = Username.getText();
                  String password = new String(Password.getPassword());

                  if (username.equals(password)) {
                      JOptionPane.showMessageDialog(null, "Success!");
                  } else {
                      JOptionPane.showMessageDialog(null, "Failed!");
                  }

            }
        });

		//Panel
        JPanel Panel = new JPanel(null);
        Panel.add(Lbl);
        Panel.add(Username);
        Panel.add(Label);
        Panel.add(Password);
        Panel.add(loginButton);

        //Frame
        JFrame Frame = new JFrame();
        Frame.setTitle("Text Validator");
        Frame.add(Panel);
        Frame.setSize(350, 350);
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		new jpass2();
	}
}