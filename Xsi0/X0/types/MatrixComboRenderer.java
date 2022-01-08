/**
 * Created on 29.04.2007
 */
package types;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * @author cipi
 *
 */
@SuppressWarnings("serial")
public class MatrixComboRenderer extends JLabel implements ListCellRenderer {
	
	public MatrixComboRenderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
		
		if (value instanceof Integer) {
			setText(value + " x " + value);
		} else if (value != null){
			setText(value.toString());
		}
		
		return this;
	}

}
