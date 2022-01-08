/**
 * Created on 28.04.2007
 */
package types;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * @author cipi
 *
 */
@SuppressWarnings("serial")
public class MyLabel extends JLabel {

	public MyLabel(String text) {
		super(text);
		setForeground(Color.YELLOW);
		setFont(new Font("myFont", Font.BOLD, 14));
	}
	
}
