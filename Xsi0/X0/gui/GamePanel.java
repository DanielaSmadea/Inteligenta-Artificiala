/**
 * 
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import types.BestResult;
import types.GameParameters;
import types.Pair;
import types.PossibleMove;

import computations.TicTacToe;

/**
 * @author cipi
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private int n;
	private int nbMouves;
	private int[][] mat = null; 
	private JLabel[][] fig = null;
	private ImageIcon xIcon = null;
	private ImageIcon xIconFinal = null;
	private ImageIcon oIcon = null;
	private ImageIcon oIconFinal = null;
	private ImageIcon icon = null;
	private ImageIcon icon_roll = null;
	private MouseListener mouseListener = null;
	
	private int posx = 0;
	private int posy = 0;
	private boolean canPlay;
	
	private TicTacToe ticTacToe = null;
	private GameParameters gameParameters = null;
	
	private MessagePanel messagePanel = null;
	
	private static GamePanel instance = null;
	
	private GamePanel(){
		initComponents();
		setup();
		initialize();
	}
	
	public static GamePanel getInstance() {
		if(instance == null) {
			instance = new GamePanel();
		}
		
		return instance;
	}

	private void initComponents() {
		gameParameters = GameParameters.getInstance();
		ticTacToe = new TicTacToe();
		
		messagePanel = MessagePanel.getInstance();
		
		icon = new ImageIcon(getClass().getClassLoader().getResource("gui/images/patrat.jpg"));
		icon_roll = new ImageIcon(getClass().getClassLoader().getResource("gui/images/patrat_roll.jpg"));
		xIcon = new ImageIcon(getClass().getClassLoader().getResource("gui/images/ics.jpg"));
		xIconFinal = new ImageIcon(getClass().getClassLoader().getResource("gui/images/ics_finish.jpg"));
		oIcon = new ImageIcon(getClass().getClassLoader().getResource("gui/images/zero.jpg"));
		oIconFinal = new ImageIcon(getClass().getClassLoader().getResource("gui/images/zero_finish.jpg"));
		
		mouseListener = new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				click(getMousePosition().x, getMousePosition().y);
			}

			public void mouseEntered(MouseEvent arg0) {
				if(getMousePosition() != null) {
					roll_in(getMousePosition().x, getMousePosition().y);
				}
			}

			public void mouseExited(MouseEvent arg0) {
				restoreIcon();
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		};
		
	}
	
	private void setup() {
		messagePanel.setText(" ");
		
		n = gameParameters.getMatrixDimension();
		canPlay = true;
		nbMouves = 0;
		
		mat = new int[n][n];
		fig = new JLabel[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = 0;
				
				fig[i][j] = new JLabel(icon);
				fig[i][j].addMouseListener(mouseListener);
			}
		}
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridy = 0;
		
		for (int i = 0; i < n; i++) {
			c.gridx = 0;
			for (int j = 0; j < n; j++) {
				this.add(fig[i][j], c);
				c.gridx++;
			}
			c.gridy++;
			
		}
		
		this.setOpaque(false);
	}
	
	private void roll_in(int x, int y){
		int i = y/30;
		int j = x/30;
		
		if(mat[i][j] == 0) {
			fig[i][j].setIcon(icon_roll);
			posx = j;
			posy = i;
		}
	}
	
	private void restoreIcon(){
		if(mat[posy][posx] == 0) {
			fig[posy][posx].setIcon(icon);
		}
	}
	
	private void click(int x, int y){
		int i = y/30;
		int j = x/30;
		
		if((mat[i][j] == 0) && canPlay) {
			fig[i][j].setIcon(xIcon);
			mat[i][j] = -1;
			
			BestResult bestResult = ticTacToe.lookUp(mat, true);
			if(bestResult.isWinningPosition()) {
				for (Pair pair : bestResult.getPositions()) {
					fig[pair.getLine()][pair.getCol()].setIcon(xIconFinal);
				}
				canPlay = false;
				messagePanel.setText("Felicitari! Ati castigat.");
				return;
			}
			
			nbMouves++;
			if(nbMouves == n*n) {
				canPlay = false;
				messagePanel.setText("Nu mai sunt celule goale...");
				return;
			}
			
			playerMoved();
		}
	}

	private void playerMoved(){
		PossibleMove computerMove = ticTacToe.getComputerMove(mat);
		int line = computerMove.getNewCell().getLine();
		int col = computerMove.getNewCell().getCol();
		mat[line][col] = 1;
		fig[line][col].setIcon(oIcon); 
		
		BestResult bestResult = ticTacToe.lookUp(mat, false);
		if(bestResult.isWinningPosition()) {
			for (Pair pair : bestResult.getPositions()) {
				fig[pair.getLine()][pair.getCol()].setIcon(oIconFinal);
			}
			canPlay = false;
			messagePanel.setText("Ati pierdut");
			return;
		}
		
		nbMouves++;
		if(nbMouves == n*n) {
			canPlay = false;
			messagePanel.setText("Nu mai sunt celule goale...");
		}
	}
	
	public void restart() {
		this.removeAll();
		this.updateUI();
		ticTacToe.resetNbToWin();
		setup();
		initialize();
	}
}
