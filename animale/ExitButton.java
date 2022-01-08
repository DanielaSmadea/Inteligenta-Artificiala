import javax.swing.JButton;


public class ExitButton extends JButton implements Command{
	
	public ExitButton()
	{
		super("Exit");
	}
	public void Execute() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
