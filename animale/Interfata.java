import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import jess.JessException;


public class Interfata extends JFrame implements ActionListener{
	public static JTextArea intrebare;
	public static YesButton yes;
	public static NoButton no;
	public ExitButton exit;
	public static CreateFacts c;
	
	public Interfata()
	{
		setLayout(new GridLayout(2,1));
		intrebare=new JTextArea();
		yes=new YesButton();
		no=new NoButton();
		exit=new ExitButton();
		JPanel frame=new JPanel();
		frame.setLayout(new FlowLayout());
		add(intrebare);
		frame.add(yes);
		frame.add(no);
		frame.add(exit);
		add(frame);
		yes.addActionListener(this);
		no.addActionListener(this);
		exit.addActionListener(this);
		intrebare.setText("Este un animal mare?");
		setSize(new Dimension(200,200));
		setVisible(true);
		
		try {
			c=new CreateFacts();
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void setComponente(String intrebareString, boolean isButtonVisible)
	{
		intrebare.setText(intrebareString);
		if (!isButtonVisible)
		{
			yes.setVisible(false);
			no.setVisible(false);
		}
	}
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String button;
		button=evt.getActionCommand();
		if (button.equalsIgnoreCase("yes"))
		{
			yes.Execute();
			try {
				c.createFact();
			} catch (JessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			if (button.equalsIgnoreCase("no"))
			{
				no.Execute();
				try {
					c.createFact();
				} catch (JessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				if (button.equalsIgnoreCase("exit"))
					exit.Execute();
	}
}
