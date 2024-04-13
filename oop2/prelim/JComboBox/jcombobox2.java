import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class jcombobox2 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Pet");
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField petNameTextField = new JTextField();

        petNameTextField.setName("petNameTextField");

        petNameTextField.setSize(100, 50);

        JComboBox<String> petTypeComboBox = new JComboBox<>();
        petTypeComboBox.addItem("Dog");
        petTypeComboBox.addItem("Cat");
        petTypeComboBox.addItem("Rabbit");
        petTypeComboBox.addItem("Parrot");
        petTypeComboBox.setName("petTypeComboBox");

        JButton generateTextButton = new JButton("Generate");
        generateTextButton.setName("generateTextButton");

        JLabel displayLabel = new JLabel();
        displayLabel.setName("displayLabel");

        generateTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pet = (String) petTypeComboBox.getSelectedItem();
                String name = (String) petNameTextField.getText();
                String firstLetter = name.substring(0, 1);
                firstLetter = firstLetter.toUpperCase();
                String restofname = name.substring(1);
                String fullname = firstLetter + restofname;
                displayLabel.setText("The pet is a " + pet.toLowerCase() + " named " + fullname);
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(petNameTextField);
        frame.add(petTypeComboBox);
        frame.add(generateTextButton);
        frame.add(displayLabel);
        frame.setVisible(true);
    }
}
