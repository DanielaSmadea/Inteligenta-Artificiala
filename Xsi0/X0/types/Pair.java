package types;

public class Pair {
	private int line;
	private int col;
	
	public Pair(int x, int y) {
		this.line = x;
		this.col = y;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getCol() {
		return col;
	}
}
