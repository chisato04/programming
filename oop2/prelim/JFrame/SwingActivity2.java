import javax.swing.*;

public class SwingActivity2 extends JFrame {
    public SwingActivity2() {
        setTitle("Frame"); // title of the window
        setName("frmPractice"); // name of the frame
        setSize(500, 400); // set window dimensions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when press close button
        setVisible(true); //makes the frame visible
    }

    public static void main(String[] args) {
        new SwingActivity2();
    }
}

// malinao, james patrick m. | bsit 2-y2-1 | feb 5, 2023