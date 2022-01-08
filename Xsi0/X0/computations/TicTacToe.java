package computations;





import java.util.LinkedList;
import java.util.List;

import types.BestResult;
import types.GameParameters;
import types.Pair;
import types.PossibleMove;


public class TicTacToe {
	private int nbToWin;
//	private Pair forcedCell = null;

	public TicTacToe() {
		this.nbToWin = GameParameters.getInstance().getNbToWin();
	}
	
	/**
	 * This method will take again the winning number of cells
	 * from GameParameters
	 */
	public void resetNbToWin() {
		this.nbToWin = GameParameters.getInstance().getNbToWin();
	}

	/**
	 * It determines the best result (the value of the best position)
	 * for a given situation
	 * 
	 * @param mat - actual game matrix
	 * @param user - true if the player is the human
	 * @return the best existing position for that player 
	 * (the cells which are composing that position)
	 * and a measure of that position
	 */
	public BestResult lookUp(int[][] mat, boolean user) {
		BestResult bestResult = new BestResult(nbToWin, user);
		BestResult intermediateResult = new BestResult(nbToWin, user);
		
		if(mat.length < nbToWin) {
			// This case should never happen
			return null;
		}
		
		/*
		 * human positions are marked with '-1'
		 * computer positions are marked vith '1'
		 */
		int searchNumber = 1;
		if(user) {
			searchNumber = -1;
		}
		
		/*
		 * we will search only in 4 directions:
		 * 		- horizontal (to the right)
		 * 		- vertical (down)
		 * 		- diagonal (down + to the left)
		 * 		- diagonal (down + to the right)
		 */
		
		for (int i = 0; i < mat.length - 1; i++) {
			// in first column we will not search diagonal to left
			if(mat[i][0] == searchNumber) {
				//this comparation will be performed in the search methods
				//but if it misses, more operations have to be perfomed
				
				/* search horizontally */
				intermediateResult = searchHR(mat, i, 0, user);
				if(intermediateResult.isWinningPosition()) {
					return intermediateResult;
				}
				if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
					bestResult = intermediateResult;
				}
				
				/* search vertically */
				intermediateResult = searchVD(mat, i, 0, user);
				if(intermediateResult.isWinningPosition()) {
					return intermediateResult;
				}
				if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
					bestResult = intermediateResult;
				}
				
				/* search diagonally to the right */
				intermediateResult = searchDR(mat, i, 0, user);
				if(intermediateResult.isWinningPosition()) {
					return intermediateResult;
				}
				if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
					bestResult = intermediateResult;
				}
			}
			
			for (int j = 1; j < mat.length - 1; j++) {
				if(mat[i][j] == searchNumber) {
					
					/* search horizontally */
					intermediateResult = searchHR(mat, i, j, user);
					if(intermediateResult.isWinningPosition()) {
						return intermediateResult;
					}
					if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
						bestResult = intermediateResult;
					}
					
					/* search vertically */
					intermediateResult = searchVD(mat, i, j, user);
					if(intermediateResult.isWinningPosition()) {
						return intermediateResult;
					}
					if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
						bestResult = intermediateResult;
					}
					
					/* search diagonally to the right */
					intermediateResult = searchDR(mat, i, j, user);
					if(intermediateResult.isWinningPosition()) {
						return intermediateResult;
					}
					if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
						bestResult = intermediateResult;
					}
					
					/* search diagonally to the left */
					intermediateResult = searchDL(mat, i, j, user);
					if(intermediateResult.isWinningPosition()) {
						return intermediateResult;
					}
					if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
						bestResult = intermediateResult;
					}
				}
			}
			
			// in the last column we will not search diagonal to right or horizontal line
			if(mat[i][mat.length-1] == searchNumber) {
				
				/* search vertically */
				intermediateResult = searchVD(mat, i, mat.length-1, user);
				if(intermediateResult.isWinningPosition()) {
					return intermediateResult;
				}
				if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
					bestResult = intermediateResult;
				}
				
				/* search diagonally to the left */
				intermediateResult = searchDL(mat, i, mat.length-1, user);
				if(intermediateResult.isWinningPosition()) {
					return intermediateResult;
				}
				if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
					bestResult = intermediateResult;
				}
			}
		}
		
		// on last line we will search only for horizontal lines
		for (int j = 0; j < mat.length-1; j++) {
			/* search horizontally */
			intermediateResult = searchHR(mat, mat.length-1, j, user);
			if(intermediateResult.isWinningPosition()) {
				return intermediateResult;
			}
			if(betterScore(bestResult.value(), intermediateResult.value(), user)) {
				bestResult = intermediateResult;
			}
		}

		return bestResult;
	}
	
	public PossibleMove getComputerMove(int[][] mat){
			PossibleMove move = new PossibleMove(mat, 0, null);
			PossibleMove max = new PossibleMove(mat, 1000, null);
			PossibleMove min = new PossibleMove(mat, -1000, null);
			
			long time = - System.currentTimeMillis();
			
			PossibleMove compMove = minimax(move, GameParameters.getInstance().getNbOfMovesAhead(), false, min, max);
			
			time += System.currentTimeMillis();
			System.err.println("Val = " + compMove.getValue() + " (computed in " + time + " millis)");
			return compMove;
	//		return minimax(move, GameParameters.getInstance().getNbOfMovesAhead(), false, min, max);
		}

	private boolean betterScore(int oldScore, int newScore, boolean user) {
		if(user) {
			if(oldScore > newScore) {
				return true;
			}
			return false;
		} else {
			if(oldScore > newScore) {
				return false;
			}
			return true;
		}
	}
	
	private BestResult searchHR(int[][] mat, int line, int col, boolean user) {
		BestResult rez = new BestResult(nbToWin, user);
		int searchNumber = 1;
		if(user) {
			searchNumber = -1;
		}
		
		if(mat[line][col] == searchNumber) {
			rez.addPair(new Pair(line, col));
			//can build to the left?
			if(col > 0) {
				if (mat[line][col-1] == searchNumber) {
					//it is not the first owned cell in line
					//this potential situation has been studied
					return new BestResult(nbToWin, user);
				}
				if (mat[line][col-1] == 0) {
					// the left cell can be used
					rez.addFreeSpace(new Pair(line, col-1));
				}
			}
			
			//search other owned cells to the right
			do {
				col++;
				if(col >= mat.length) {
					// the cell is not in the matrix anymore
					// there is no cell that can be used to the right
					return rez;
				}
				if(mat[line][col] == searchNumber) {
					rez.addPair(new Pair(line, col));
				} else {
					//this cell is not owned
					//can be used the cell for further construction?
					if(mat[line][col] == 0) {
						rez.addFreeSpace(new Pair(line, col));
					}
					return rez;
				}
			} while (true);
		}
		
		return rez; // this line will never be reached
	}
	
	private BestResult searchVD(int[][] mat, int line, int col, boolean user) {
		BestResult rez = new BestResult(nbToWin, user);
		int searchNumber = 1;
		if(user) {
			searchNumber = -1;
		}
		
		if(mat[line][col] == searchNumber) {
			rez.addPair(new Pair(line, col));
			//can build to upper cell?
			if(line > 0) {
				if (mat[line-1][col] == searchNumber) {
					//it is not the first owned cell in vertical line
					//this potential situation has been studied
					return new BestResult(nbToWin, user);
				}
				if (mat[line-1][col] == 0) {
					// the upper cell can be used
					rez.addFreeSpace(new Pair(line-1, col));
				}
			}
			
			//search other owned cells to the right
			do {
				line++;
				if(line >= mat.length) {
					// the cell is not in the matrix anymore
					// there is no downwards cell that can be used 
					return rez;
				}
				if(mat[line][col] == searchNumber) {
					rez.addPair(new Pair(line, col));
				} else {
					//this cell is not owned
					//can be used the cell for further construction?
					if(mat[line][col] == 0) {
						rez.addFreeSpace(new Pair(line, col));
					}
					return rez;
				}
			} while (true);
		}
		
		return rez; // this line will never be reached
	}
	
	private BestResult searchDR(int[][] mat, int line, int col, boolean user) {
		BestResult rez = new BestResult(nbToWin, user);
		int searchNumber = 1;
		if(user) {
			searchNumber = -1;
		}
		
		if(mat[line][col] == searchNumber) {
			rez.addPair(new Pair(line, col));
			//can build to upper cell?
			if((line > 0) && (col > 0)) {
				if (mat[line-1][col-1] == searchNumber) {
					//it is not the first owned cell in diagonal line
					//this potential situation has been studied
					return new BestResult(nbToWin, user);
				}
				if (mat[line-1][col-1] == 0) {
					// the upper cell can be used
					rez.addFreeSpace(new Pair(line-1, col-1));
				}
			}
			
			//search other owned cells in diagonal to the right
			do {
				line++; col++;
				if((line >= mat.length) || (col >= mat.length)) {
					// the cell is not in the matrix anymore
					// there is no downwards cell that can be used 
					return rez;
				}
				if(mat[line][col] == searchNumber) {
					rez.addPair(new Pair(line, col));
				} else {
					//this cell is not owned
					//can be used the cell for further construction?
					if(mat[line][col] == 0) {
						rez.addFreeSpace(new Pair(line, col));
					}
					return rez;
				}
			} while (true);
		}
		
		return rez; // this line will never be reached
	}
	
	private BestResult searchDL(int[][] mat, int line, int col, boolean user) {
		BestResult rez = new BestResult(nbToWin, user);
		int searchNumber = 1;
		if(user) {
			searchNumber = -1;
		}
		
		if(mat[line][col] == searchNumber) {
			rez.addPair(new Pair(line, col));
			//can build to upper cell?
			if((line > 0) && (col < mat.length-1)) {
				if (mat[line-1][col+1] == searchNumber) {
					//it is not the first owned cell in diagonal line
					//this potential situation has been studied
					return new BestResult(nbToWin, user);
				}
				if (mat[line-1][col+1] == 0) {
					// the upper cell can be used
					rez.addFreeSpace(new Pair(line-1, col+1));
				}
			}
			
			//search other owned cells in diagonal to the right
			do {
				line++; col--;
				if((line >= mat.length) || (col < 0)) {
					// the cell is not in the matrix anymore
					// there is no downwards cell that can be used 
					return rez;
				}
				if(mat[line][col] == searchNumber) {
					rez.addPair(new Pair(line, col));
				} else {
					//this cell is not owned
					//can be used the cell for further construction?
					if(mat[line][col] == 0) {
						rez.addFreeSpace(new Pair(line, col));
					}
					return rez;
				}
			} while (true);
		}
		
		return rez; // this line will never be reached
	}

	private List<PossibleMove> getPossibleMoves(int[][] mat, boolean user) {
		List<PossibleMove> moves = new LinkedList<PossibleMove>();
		
		/*
		 * human cells are marked with '-1'
		 * computer cells are marked vith '1'
		 */
		int usedNumber = 1;
		if(user) {
			usedNumber = -1;
		}
		
		// maybe we are forced to put in one cell
//		if(forcedCell != null) {
//			int[][] modifMat = makeACopy(mat);
//			modifMat[forcedCell.getLine()][forcedCell.getCol()] = usedNumber;
//			BestResult modifMatResult = lookUp(modifMat, user);
//			PossibleMove possibleMove = new PossibleMove(modifMat,
//					modifMatResult.value(), forcedCell);
//			moves.add(possibleMove);
//			return moves;
//		}
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if((mat[i][j] == 0) && adjacent(mat, i, j)) {
					int[][] modifMat = makeACopy(mat);
					modifMat[i][j] = usedNumber;
					BestResult modifMatResult = lookUp(modifMat, user);
					Pair newCell = new Pair(i, j);
					PossibleMove possibleMove = new PossibleMove(modifMat,
							modifMatResult.value(), newCell);
					moves.add(possibleMove);
				}
			}
		}
		
		return moves;
	}

	private int[][] makeACopy(int[][] originalMatrix){
		int[][] modifiedMatrix = new int[originalMatrix.length][originalMatrix.length];
		
		for (int i = 0; i < originalMatrix.length; i++) {
			for (int j = 0; j < originalMatrix.length; j++) {
				modifiedMatrix[i][j] = originalMatrix[i][j];
			}
		}
		
		return modifiedMatrix;
	}
	
	private boolean adjacent(int[][] mat, int line, int col) {
		if(line == 0) {
			if(col == 0) {
				if(mat[line+1][col] != 0) {
					return true;
				}
				if(mat[line][col+1] != 0) {
					return true;
				}
				if(mat[line+1][col+1] != 0) {
					return true;
				}
			} else if(col == mat.length-1) {
				if(mat[line+1][col] != 0) {
					return true;
				}
				if(mat[line][col-1] != 0) {
					return true;
				}
				if(mat[line+1][col-1] != 0) {
					return true;
				}
			} else {
				if(mat[line][col-1] != 0) {
					return true;
				}
				if(mat[line][col+1] != 0) {
					return true;
				}
				if(mat[line+1][col-1] != 0) {
					return true;
				}
				if(mat[line+1][col] != 0) {
					return true;
				}
				if(mat[line+1][col+1] != 0) {
					return true;
				}
			}
		} else if(line == mat.length-1) {
			if(col == 0) {
				if(mat[line-1][col] != 0) {
					return true;
				}
				if(mat[line][col+1] != 0) {
					return true;
				}
				if(mat[line-1][col+1] != 0) {
					return true;
				}
			} else if(col == mat.length-1) {
				if(mat[line-1][col] != 0) {
					return true;
				}
				if(mat[line][col-1] != 0) {
					return true;
				}
				if(mat[line-1][col-1] != 0) {
					return true;
				}
			} else {
				if(mat[line][col-1] != 0) {
					return true;
				}
				if(mat[line][col+1] != 0) {
					return true;
				}
				if(mat[line-1][col-1] != 0) {
					return true;
				}
				if(mat[line-1][col] != 0) {
					return true;
				}
				if(mat[line-1][col+1] != 0) {
					return true;
				}
			}
		} else {
			if(col == 0) {
				if(mat[line+1][col] != 0) {
					return true;
				}
				if(mat[line-1][col] != 0) {
					return true;
				}
				if(mat[line][col+1] != 0) {
					return true;
				}
				if(mat[line+1][col+1] != 0) {
					return true;
				}
				if(mat[line-1][col+1] != 0) {
					return true;
				}
			} else if(col == mat.length-1) {
				if(mat[line+1][col] != 0) {
					return true;
				}
				if(mat[line-1][col] != 0) {
					return true;
				}
				if(mat[line][col-1] != 0) {
					return true;
				}
				if(mat[line+1][col-1] != 0) {
					return true;
				}
				if(mat[line-1][col-1] != 0) {
					return true;
				}
			} else {
				if(mat[line][col-1] != 0) {
					return true;
				}
				if(mat[line][col+1] != 0) {
					return true;
				}
				if(mat[line+1][col-1] != 0) {
					return true;
				}
				if(mat[line+1][col] != 0) {
					return true;
				}
				if(mat[line+1][col+1] != 0) {
					return true;
				}
				if(mat[line-1][col-1] != 0) {
					return true;
				}
				if(mat[line-1][col] != 0) {
					return true;
				}
				if(mat[line-1][col+1] != 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean drawGame(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if(mat[i][j] == 0){
					return false;
				}
			}
		}
		
		return true;
	}
	
	private PossibleMove minimax(PossibleMove move, int depth, boolean user, PossibleMove min, PossibleMove max) {
//		String forPrint = "";
//		switch (depth) {
//		case 0: 
//			forPrint = "\t\t\t\t\t\t\t";
//			break;
//		case 1:
//			forPrint = "\t\t\t\t\t\t";
//			break;
//		case 2:
//			forPrint = "\t\t\t\t\t";
//			break;
//		case 3:
//			forPrint = "\t\t\t\t";
//			break;
//		case 4:
//			forPrint = "\t\t\t";
//			break;
//		case 5:
//			forPrint = "\t\t";
//			break;
//		case 6:
//			forPrint = "\t";
//			break;
//			
//		default:
//			break;
//		}
//		if(user) {
//			System.out.println("MIN");
//		} else {
//			System.out.println("MAX");
//		}
//		System.out.println(forPrint + "Depth = " + depth + ", min = " + min.getValue() + ", max = " + max.getValue());
//		for (int i = 0; i < move.getPosition().length; i++) {
//			for (int j = 0; j < move.getPosition().length; j++) {
//				if(j==0){
//					System.out.print(forPrint);
//				}
//				System.out.print(move.getPosition()[i][j] + "\t");
//			}
//			System.out.println();
//		}
		
		/* does the computer won? */
		BestResult computerResult = lookUp(move.getPosition(), false);
		if(computerResult.isWinningPosition()) {
			return new PossibleMove(move.getPosition(), computerResult.value(), move.getNewCell());
		}
		
		/* does the human won? */
		BestResult humanResult = lookUp(move.getPosition(), true);
		if(humanResult.isWinningPosition()) {
			return new PossibleMove(move.getPosition(), humanResult.value(), move.getNewCell());
		}
		//a move that must be made
		//TODO: it will be great when the computer will know that is 
		//almost winning even if it has an empty space inside the potential winning cells
		//(not only at the edges)
//		if((humanResult.isAlmostWinning()) && (!computerResult.isAlmostWinning())) {
//			forcedCell = humanResult.getFreeSpace();
//		} else {
//			forcedCell = null;
//		}
		
		/* are filled all cells? */
		if(drawGame(move.getPosition())) {
			return new PossibleMove(move.getPosition(), 0, move.getNewCell());
		}
		
		/* is depth search touched? */
		if(depth == 0) {
			return new PossibleMove(move.getPosition(), computerResult.value(), move.getNewCell());
		}
				
		if(user) { // a min node
			for (PossibleMove possibleMove : getPossibleMoves(move.getPosition(), user)) {
				if(move.getNewCell() != null){
					possibleMove.setNewCell(move.getNewCell());
				}
				
				PossibleMove newVal = minimax(possibleMove, depth - 1, !user, min, max);
				if(newVal.getValue() < max.getValue()){
					max = newVal;
				}
				if(max.getValue() <= min.getValue()){
					return min;
				}
			}
//			System.out.println(forPrint + "Depth = " + depth + ", min = " + min.getValue() + ", max = " + max.getValue());
//			System.out.println(forPrint + "--> " + max.getValue()+ "\tcell = " + max.getNewCell().getLine() + ", " + max.getNewCell().getCol());
			return max;
		} else {
			for (PossibleMove possibleMove : getPossibleMoves(move.getPosition(), user)) {
				if(move.getNewCell() != null){
					possibleMove.setNewCell(move.getNewCell());
				}
				
				PossibleMove newVal = minimax(possibleMove, depth - 1, !user, min, max);
				if(newVal.getValue() > min.getValue()){
					min = newVal;
				}
				if(min.getValue() >= max.getValue()){
					return max;
				}
			}
//			System.out.println(forPrint + "Depth = " + depth + ", min = " + min.getValue() + ", max = " + max.getValue());
//			System.out.println(forPrint + "--> " + min.getValue()+ "\tcell = " + min.getNewCell().getLine() + ", " + min.getNewCell().getCol());
			return min;
		}
	}
	
}
