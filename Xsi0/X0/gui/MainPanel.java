/**
 * 
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * @author cipi
 *
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	private MessagePanel messagePanel = null;
	private GamePanel gamePanel = null;
	private OptionsPanel optionsPanel = null;

	public MainPanel(){
		initComponents();
		initialize();
	}

	private void initComponents() {
		messagePanel = MessagePanel.getInstance();
		gamePanel = GamePanel.getInstance();
		optionsPanel = new OptionsPanel();
	}

	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		this.add(messagePanel, c);
		
		c.gridy++;
		this.add(gamePanel, c);
		
		c.gridy++;
		this.add(optionsPanel, c);
	
		this.setOpaque(false);
	}
	
}
