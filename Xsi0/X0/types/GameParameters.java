/**
 * 
 */
package types;

/**
 * @author cipi
 *
 */
public class GameParameters {
	private int matrixDimension;
	private int nbToWin;
	private int nbOfMovesAhead;
	private boolean gameOver;
	private static GameParameters instance = null;
	
	private GameParameters() {
		matrixDimension = 3;
		nbToWin = 3;
		nbOfMovesAhead = 10;
		gameOver = false;
	}

	public static GameParameters getInstance() {
		if(instance == null) {
			instance = new GameParameters();
		}
		
		return instance;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getMatrixDimension() {
		return matrixDimension;
	}

	public void setMatrixDimension(int matrixDimension) {
		this.matrixDimension = matrixDimension;
	}

	public int getNbOfMovesAhead() {
		return nbOfMovesAhead;
	}

	public void setNbOfMovesAhead(int nbOfMovesAhead) {
		this.nbOfMovesAhead = nbOfMovesAhead;
	}

	public int getNbToWin() {
		return nbToWin;
	}

	public void setNbToWin(int nbToWin) {
		this.nbToWin = nbToWin;
	}
}
