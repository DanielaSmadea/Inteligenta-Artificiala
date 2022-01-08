/**
 * Created on 28.04.2007
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import types.GameParameters;
import types.MatrixComboRenderer;
import types.MyLabel;

/**
 * @author cipi
 *
 */

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {
	private GameParameters gameParameters = null;
	private GamePanel gamePanel = null;
	private MyLabel lbDimension = null;
	private MyLabel lbNbToWin = null;
	private MyLabel lbNbAhead = null;
	private JComboBox cmbDimension = null;
	private JComboBox cmbNbToWin = null;
	private JComboBox cmbNbAhead = null;
	private JButton restartBtn = null;
	
	public OptionsPanel() {
		initComponents();
		initialize();
	}

	private void initComponents() {
		gameParameters = GameParameters.getInstance();
		gamePanel = GamePanel.getInstance();

		lbDimension = new MyLabel("Dimensiunea suprafetei de joc: ");
		lbNbToWin = new MyLabel("Numarul de celule castigatoare: ");
		lbNbAhead = new MyLabel("Numarul de mutari studiate de calc: ");
		
		cmbNbAhead = new JComboBox();
		cmbNbAhead.setBackground(Color.WHITE);
		for (int i = 5; i < 11; i++) {
			cmbNbAhead.addItem(i);
		}
		cmbNbAhead.setEnabled(false);
		
		cmbDimension = new JComboBox();
		cmbDimension.setBackground(Color.WHITE);
		for (int i = 3; i < 11; i++) {
			cmbDimension.addItem(i);
		}
		cmbDimension.setRenderer(new MatrixComboRenderer());
		cmbDimension.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				resetNbToWinCombo((Integer)cmbDimension.getSelectedItem());
			}
		});
		
		cmbNbToWin = new JComboBox();
		cmbNbToWin.setBackground(Color.WHITE);
		resetNbToWinCombo((Integer)cmbDimension.getSelectedItem());
		
		restartBtn = new JButton("Restart");
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameParameters.setGameOver(false);
				gameParameters.setMatrixDimension((Integer)cmbDimension.getSelectedItem());
				gameParameters.setNbToWin((Integer)cmbNbToWin.getSelectedItem());
				if(gameParameters.getMatrixDimension() == 3) {
					gameParameters.setNbOfMovesAhead(10);
				} else {
					gameParameters.setNbOfMovesAhead((Integer)cmbNbAhead.getSelectedItem());
				}
				
				gamePanel.restart();
				}
			});
	}
	
	private void initialize() {
		this.setOpaque(false);
	    this.setLayout(null);
	    
	    this.add(lbDimension);
	    lbDimension.setBounds(10, 50, 260, 20);
	    this.add(cmbDimension);
	    cmbDimension.setBounds(280, 50, 70, 20);
	    
	    this.add(lbNbToWin);
	    lbNbToWin.setBounds(10, 75, 260, 20);
	    this.add(cmbNbToWin);
	    cmbNbToWin.setBounds(280, 75, 50, 20);
	    
	    this.add(lbNbAhead);
	    lbNbAhead.setBounds(10, 100, 260, 20);
	    this.add(cmbNbAhead);
	    cmbNbAhead.setBounds(280, 100, 50, 20);
	    
	    this.add(restartBtn);
	    restartBtn.setBounds(150, 20, 100, 20);
	    this.setBackground(Color.YELLOW);
	    this.setPreferredSize(new Dimension(400, 200));
	}
	
	private void resetNbToWinCombo(int selectedDimension) {
		cmbNbToWin.removeAllItems();
		if(selectedDimension == 3) {
			cmbNbToWin.addItem(3);
			cmbNbAhead.setEnabled(false);
		} else if (selectedDimension == 4){
			cmbNbToWin.addItem(4);
			cmbNbAhead.setEnabled(true);
		} else if ((selectedDimension > 4) && (selectedDimension < 7)) {
			cmbNbToWin.addItem(4);
			cmbNbToWin.addItem(5);
			cmbNbAhead.setEnabled(true);
		} else {
			cmbNbToWin.addItem(5);
			cmbNbToWin.addItem(6);
			cmbNbAhead.setEnabled(true);
		}
	}
}
