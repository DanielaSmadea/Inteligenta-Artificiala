/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * @author cipi
 *
 */
@SuppressWarnings("serial")
public class MainClass extends JFrame {
	JScrollPane scrollPane;
	ImageIcon icon;
	Image image;
	private static MainPanel pnlMain = new MainPanel();
 
	public MainClass()
	{
		icon = new ImageIcon(getClass().getResource("images/sky.jpg"));
 
		JPanel panel = new JPanel()
		{
			protected void paintComponent(Graphics g)
			{
				//  Dispaly image at at full size
//				g.drawImage(icon.getImage(), 0, 0, null);
 
				//  Scale image to size of component
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
 
				//  Fix the image position in the scroll pane
//				Point p = scrollPane.getViewport().getViewPosition();
//				g.drawImage(icon.getImage(), p.x, p.y, null);
 
				super.paintComponent(g);
			}
		};
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(600, 400));
		scrollPane = new JScrollPane(panel);
		getContentPane().add(scrollPane);

		panel.setLayout(new BorderLayout());
		panel.add(pnlMain, BorderLayout.CENTER);
	}
 
	public static void main(String [] args)
	{
		MainClass frame = new MainClass();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tic Tac Toe");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
