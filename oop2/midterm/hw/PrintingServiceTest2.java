import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PrintingServiceTest2 {

    private Frame Frame;
    private Label Label;
    private Label sizelabel, styleLabel, cashLabel, changelbl, change;
    private List ProcessList;
    private TextField cash;
    private JRadioButton sm;
    private Checkbox chkStar;
    private Button processbtn, cancelBtn, exitbtn, computeBtn;
    private Panel control;
    private ButtonGroup sizes;

    int Total = 0;
    int smPrice = 200;
    int medPrice = 250;
    int lgPrice = 350;
    int starPrice = 30;
    int textPrice = 50;
    int logoPrice = 35;

    public PrintingServiceTest2() {
        Frame = new Frame("PRINTING SERVICE");
        Frame.setSize(600, 350);
        Frame.setResizable(false);
        Label = new Label("T-SHIRT PRINTING");
        Label.setBounds(220, 0, 170, 50);
        Label.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 18));

        sizelabel = new Label("SIZES");
        sizelabel.setBounds(80, 50, 60, 20);

        sizes = new ButtonGroup();

        // sm, med, lg
        sm = new JRadioButton("SMALL    P200");
        sm.setBounds(30, 90, 120, 20);

        sizes.add(sm);

        styleLabel = new Label("DESIGN");

        chkStar = new Checkbox("STAR      P30");
        chkStar.setBounds(225, 90, 120, 35);

        // pocessbtn
        processbtn = new Button("PROCESS");
        processbtn.setBounds(80, 210, 80, 30);

        // cancelbtn
        cancelBtn = new Button("Cancel");
        cancelBtn.setBounds(80, 210, 30, 30);

        // computeBtn
        computeBtn = new Button("COMPUTE");
        computeBtn.setBounds(190, 210, 80, 30);

        // exitbtn
        exitbtn = new Button("EXIT");
        exitbtn.setBounds(190, 250, 80, 30);

        // processlist
        ProcessList = new List();
        ProcessList.setBounds(350, 50, 200, 150);

        // cashfield
        cash = new TextField();
        cash.setBounds(420, 220, 130, 30);

        // cashlbl
        cashLabel = new Label("CASH");
        cashLabel.setBounds(350, 225, 60, 20);

        // changelbl
        changelbl = new Label("CHANGE");
        changelbl.setBounds(350, 275, 60, 20);

        change = new Label();
        change.setBounds(420, 270, 130, 30);

        // control
        control = new Panel();
        control.setLayout(null);
        control.add(Label);
        control.add(sizelabel);
        control.add(sm);
        control.add(styleLabel);
        control.add(chkStar);
        control.add(processbtn);
        control.add(cancelBtn);
        control.add(computeBtn);
        control.add(exitbtn);
        control.add(ProcessList);
        control.add(cash);
        control.add(cashLabel);
        control.add(changelbl);
        control.add(change);

        Frame.add(control);
        Frame.setVisible(true);
    }

    // process logic

    public void showResult() {
        computeBtn.setEnabled(false);
        chkStar.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String item = (e.getStateChange() == 1 ? "true" : "false");

                if (item.equals("true")) {

                    ProcessList.add("Star  P30");
                    Total += starPrice;

                } else {
                    ProcessList.remove("Star  P30");
                    Total -= starPrice;
                }
            }
        });

        sm.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    ProcessList.add("Small  P200");
                    Total += smPrice;

                } else if (e.getStateChange() == ItemEvent.DESELECTED) {

                    ProcessList.remove("Small  P200");
                    Total -= smPrice;

                }
            }
        });

        // cancel all
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sizes.clearSelection();
                chkStar.setState(false);
                Total = 0;
                cash.setText("");
                change.setText("");
                ProcessList.removeAll();
                computeBtn.setEnabled(false);

            }
        });

        exitbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you have a nice day!");
                System.exit(0);
            }
        });

        processbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProcessList.add("");
                ProcessList.add("Total Amount: P" + Total);
                computeBtn.setEnabled(true);
                cash.requestFocus();
            }
        });

        computeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int money = Integer.parseInt(cash.getText());
                    if (money < Total) {

                        JOptionPane.showMessageDialog(null, "Insufficient amount!");
                        cash.setText("");
                        cash.requestFocus();
                    } else {

                        money = money - Total;
                        change.setText("P " + money);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for cash!");
                    cash.setText("");
                    cash.requestFocus();
                }
            }

        });

    }

    public static void main(String[] args) {
        PrintingServiceTest2 ps = new PrintingServiceTest2();
        ps.showResult();
    }
}
//fixed text