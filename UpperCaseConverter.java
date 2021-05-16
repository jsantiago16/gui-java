import javax.swing.*;
import java.awt.*;

public class UpperCaseConverter extends JFrame
{


	JButton upper;
	JButton clear;
	JTextField input;
	JTextArea output;

	public UpperCaseConverter(String name){
		super(name);
		setLocation(300, 100);
		setSize (400,300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel top;
		top = new JPanel();

		upper = new JButton("UPPER");
		clear = new JButton("CLEAR");
		top.add(upper);
		top.add(clear);

		add(top, BorderLayout.NORTH);

		JPanel bottom = new JPanel();
		JLabel label = new JLabel("Enter text ->");
		input = new JTextField(20);

		bottom.add(label);
		bottom.add(input);
		add(bottom, BorderLayout.SOUTH);

		output = new JTextArea(10, 20);
		add(output);




	}

	public static void main(String args[]){
		UpperCaseConverter ucc = new UpperCaseConverter("Convert to Upper Case");
		ucc.setVisible(true);
	}
}