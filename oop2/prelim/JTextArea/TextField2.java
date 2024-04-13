import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	public class TextField2
	{
		public static void main(String[] args) {
	        JFrame frame = new JFrame("Word & Character Counter");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
	        frame.setSize(400, 300);

	        JPanel panel = new JPanel(new GridLayout(4, 1));
	        JTextArea textArea = new JTextArea();
	        textArea.setName("textArea");

	        JLabel wordCountMal = new JLabel();
	        wordCountMal.setName("wordCountLabel");

	        JLabel charCountMal = new JLabel();
	        charCountMal.setName("charCountLabel");

	        JButton countButton = new JButton("Display Count");
	        countButton.setName("countButton");
	        countButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String text = textArea.getText();
	                int wordCount = countWords(text);
	                int charCount = text.length();
	                wordCountMal.setText(String.valueOf(wordCount));
	                charCountMal.setText(String.valueOf(charCount));
	            }
	        });

	        panel.add(textArea);
	        panel.add(wordCountMal);
	        panel.add(charCountMal);
	        panel.add(countButton);
	        frame.getContentPane().add(panel);
	        frame.setVisible(true);
		}

		private static int countWords(String text) {
	        if (text == null || text.isEmpty()) {
	            return 0;
	        }
	        String[] words = text.split("\\s+");
	        return words.length;
	    }
	}