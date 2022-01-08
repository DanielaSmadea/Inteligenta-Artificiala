import javax.swing.JButton;

import jess.JessException;


public class NoButton extends JButton implements Command{
	
	public NoButton()
	{
		super("No");
	}
	
	public void Execute() {
		// TODO Auto-generated method stub
		CreateFacts.rasp=0;
	}

}
