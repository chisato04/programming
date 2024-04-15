import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FoodService extends JFrame implements ActionListener {
    private JRadioButton dineInRadioButton, takeOutRadioButton;
    private JCheckBox sausageCheckBox, mushroomCheckBox, pepperoniCheckBox;
    private JList<String> sizeList, orderList;
    private JComboBox<String> crustComboBox;
    private JButton placeOrderButton, voidButton, proceedButton;
    private DefaultListModel<String> orderListModel;

    public FoodService() {
        setTitle("Food Service");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));

        // Radio buttons
        dineInRadioButton = new JRadioButton("Dine In");
        dineInRadioButton.setName("dineInRadioButton");
        dineInRadioButton.addActionListener(this); // Add action listener
        takeOutRadioButton = new JRadioButton("Take Out");
        takeOutRadioButton.setName("takeOutRadioButton");
        takeOutRadioButton.addActionListener(this); // Add action listener
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(dineInRadioButton);
        radioGroup.add(takeOutRadioButton);

        // Checkboxes
        sausageCheckBox = new JCheckBox("Sausage P40");
        sausageCheckBox.setName("sausageCheckBox");
        mushroomCheckBox = new JCheckBox("Mushroom P30");
        mushroomCheckBox.setName("mushroomCheckBox");
        pepperoniCheckBox = new JCheckBox("Pepperoni P50");
        pepperoniCheckBox.setName("pepperoniCheckBox");

        // Size list
        String[] sizes = {"Solo P50", "Medium 10inch P100", "Large 12inch P150", "Family 14inch P200"};
        sizeList = new JList<>(sizes);
        sizeList.setName("sizeList");

        // Crust combo box
        String[] crusts = {"Thin P30", "Thick P50", "Stuffed P80", "Hand-Tossed P100"};
        crustComboBox = new JComboBox<>(crusts);
        crustComboBox.setName("crustComboBox");

        // Order list
        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        orderList.setName("orderList");

        // Buttons
        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setName("placeOrderButton");
        placeOrderButton.addActionListener(this);

        voidButton = new JButton("Void");
        voidButton.setName("voidButton");
        voidButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "TRANSACTION VOIDED", "", 0));

        voidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderListModel.removeAllElements();
                radioGroup.clearSelection();
                sausageCheckBox.setSelected(false);
                mushroomCheckBox.setSelected(false);
                pepperoniCheckBox.setSelected(false);
                orderList.clearSelection();
                sizeList.clearSelection();
            }
        });

        proceedButton = new JButton("Proceed");
        proceedButton.setName("proceedButton");
        proceedButton.addActionListener(this);

        // Adding components to the frame
        add(dineInRadioButton);
        add(takeOutRadioButton);
        add(sausageCheckBox);
        add(mushroomCheckBox);
        add(pepperoniCheckBox);
        add(sizeList);
        add(crustComboBox);
        add(orderList);
        add(placeOrderButton);
        add(voidButton);
        add(proceedButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == placeOrderButton) {
            @SuppressWarnings("unused")
            StringBuilder order = new StringBuilder();
            if (dineInRadioButton.isSelected()) {
                orderListModel.addElement("Dine In"); // Add Dine In to order list
            } else if (takeOutRadioButton.isSelected()) {
                orderListModel.addElement("Take Out"); // Add Take Out to order list
            }

            if (sausageCheckBox.isSelected()) {
                orderListModel.addElement("Sausage P40");
            }
            if (mushroomCheckBox.isSelected()) {
                orderListModel.addElement("Mushroom P30");
            }
            if (pepperoniCheckBox.isSelected()) {
                orderListModel.addElement("Pepperoni P50");
            }

            String size = sizeList.getSelectedValue();
            if (size != null) {
                orderListModel.addElement(size);
            }

            String crust = (String) crustComboBox.getSelectedItem();
            if (crust != null) {
                orderListModel.addElement(crust);
            }

            orderListModel.addElement("\n"); // Add an empty line
            orderListModel.addElement("Total Amount: P" + calculateTotal()); // Add total amount
        } else if (e.getSource() == proceedButton) {
            int totalAmount = calculateTotal();
            String input = JOptionPane.showInputDialog(this, "Enter tendered amount:");
            if (input != null) {
                try {
                    int tenderedAmount = Integer.parseInt(input);
                    if (tenderedAmount >= totalAmount) {
                        int change = tenderedAmount - totalAmount;
                        JOptionPane.showMessageDialog(this, "Change: " + change);
                    } else {
                        JOptionPane.showMessageDialog(this, "INSUFFICIENT CASH");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input");
                }
            }
        }
    }

    private int calculateTotal() {
        int total = 0;
        if (sausageCheckBox.isSelected()) {
            total += 40;
        }
        if (mushroomCheckBox.isSelected()) {
            total += 30;
        }
        if (pepperoniCheckBox.isSelected()) {
            total += 50;
        }
        String size = sizeList.getSelectedValue();
        if (size != null) {
            total += Integer.parseInt(size.split("P")[1]);
        }
        String crust = (String) crustComboBox.getSelectedItem();
        if (crust != null) {
            total += Integer.parseInt(crust.split("P")[1]);
        }
        return total;
    }

    public static void main(String[] args) {
        new FoodService();
    }
}