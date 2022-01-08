import javax.swing.JButton;

import jess.JessException;


public class YesButton extends JButton implements Command{
	
	public YesButton()
	{
		super("Yes");
	}
	public void Execute() {
		// TODO Auto-generated method stub
		CreateFacts.rasp=1;
	}

}
