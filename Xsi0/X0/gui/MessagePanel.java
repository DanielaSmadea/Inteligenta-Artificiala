/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author cipi
 *
 */
@SuppressWarnings("serial")
public class MessagePanel extends JPanel {
	private JLabel messageLabel = null;
	private JLabel emptyLabel = null;
	private static MessagePanel instance = null;
	
	private MessagePanel(){
		initComponents();
		initialize();
	}
	
	public static MessagePanel getInstance() {
		if(instance == null) {
			instance = new MessagePanel();
		}
		
		return instance;
	}
	
	private void initComponents() {
		messageLabel = new JLabel();
		messageLabel.setForeground(Color.MAGENTA);
		messageLabel.setFont(new Font("myFont", Font.BOLD, 24));
		messageLabel.setText(" ");
		
		emptyLabel = new JLabel();
		emptyLabel.setFont(new Font("myFont", Font.BOLD, 20));
		emptyLabel.setText(" ");
	}
	
	private void initialize(){
		this.setLayout(new BorderLayout());
		this.add(messageLabel, BorderLayout.NORTH);
		this.add(emptyLabel, BorderLayout.SOUTH);
		
		this.setOpaque(false);
	}
	
	public void setText(String text){
		messageLabel.setText(text);
	}
}
