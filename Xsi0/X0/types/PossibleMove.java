/**
 * Created on 29.04.2007
 */
package types;

/**
 * @author cipi
 *
 */
public class PossibleMove {
	private int[][] mat = null;
	private int value;
	private Pair newCell = null;
	
	public PossibleMove(int[][] mat, int value, Pair newCell) {
		this.mat = mat;
		this.value = value;
		this.newCell = newCell;
	}
	
	public int[][] getPosition(){
		return mat;
	}

	public int getValue() {
		return value;
	}

	public Pair getNewCell() {
		return newCell;
	}

	public void setNewCell(Pair newCell) {
		this.newCell = newCell;
	}
}
