import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calculator extends JFrame implements MouseListener
{
	JButton [] numbers = new JButton[10];
	JButton plus;
	JButton minus;
	JButton mult;
	JButton div;
	JButton clears;
	JButton dot;
	JTextField output;
	JTextField operand1;
	JTextField operand2;

	public Calculator()
	{
		super("My Calculator");
		numbers = new JButton[10];
		for(int i = 0; i < 10; i++)
			numbers[i] = new JButton("" + i);

		plus = new JButton("+");
		minus = new JButton("-");
		mult = new JButton("x");
		div = new JButton("/");
		clears = new JButton("C");
		dot = new JButton(".");
		operand1 = new JTextField(7);
		operand2 = new JTextField(7);
		output = new JTextField(15);
		setSize(225,300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//adding Listeners
		plus.addMouseListener(this);
		minus.addMouseListener(this);
		mult.addMouseListener(this);
		div.addMouseListener(this);
		clears.addMouseListener(this);
		dot.addMouseListener(this);
		for(int i = 0; i < 10 ; i++)
			numbers[i].addMouseListener(this);

		JPanel top = new JPanel();
		top.setLayout(new GridLayout(2,1));
		add(top, BorderLayout.NORTH);

		JPanel input = new JPanel();
		input.add(operand1);
		input.add(operand2);
		top.add(input);

		JPanel results = new JPanel();
		results.add(output);
		top.add(results);

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(4,1));

		center.add(getRow(numbers[7], numbers[8], numbers[9], plus));
		center.add(getRow(numbers[4], numbers[5], numbers[6], minus));
		center.add(getRow(numbers[1], numbers[2], numbers[3], mult));
		center.add(getRow(		 dot, numbers[0],     clears, div));
		add(center);

	}
	public void mouseClicked(MouseEvent e)
	{
		int button = e.getButton();
		JTextField dest = null;
		if(button == 1) dest = operand1;  //left click == left operand
		if(button == 3) dest = operand2;  //right operand  == right operand

		Object src = e.getSource();
		if(src == clears) clear();  //helper method
		else if(src == mult||src == div||src == plus||src == minus)
			performOperation(src); //helper method
		else{
			int i = 0;
			for(; i < numbers.length; i++)
				if(src == numbers[i]) break;
			StringBuffer text = new StringBuffer(dest.getText());
			if (src == dot) text.append(dot.getText());
			else text.append(numbers[i].getText());
			dest.setText(text.toString());
		}
	}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

	private void clear()
	{
		operand1.setText("");
		operand2.setText("");
		output.setText("");
	}
	private void performOperation(Object src){
		float f1 = 0;float f2 = 0;
		try {
			f1 = Float.parseFloat(operand1.getText());
			f2 = Float.parseFloat(operand2.getText());
		}catch (NumberFormatException e){
			output.setText("Invalid Number Format");
		}
		try{
			float ans = 0;
			if(src == mult) ans = f1 * f2;
			else if(src == plus) ans = f1 + f2;
			else if(src == minus) ans = f1 - f2;
			else if(src == div) ans = f1 / f2;
			output.setText(Float.toString(ans));
		} catch (Exception e) {
			output.setText("Invalid Operation");
		}
	}


	private JPanel getRow(JButton b1, JButton b2, JButton b3, JButton b4)
	{
		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row,BoxLayout.X_AXIS));
		row.add(Box.createHorizontalGlue());
		row.add(b1);row.add(b2);row.add(b3);row.add(b4);
		row.add(Box.createHorizontalGlue());
		return row;
	}

	public static void main(String args[])
	{
		Calculator c = new Calculator();
		c.setVisible(true);
	}
}