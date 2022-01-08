/**
 * 
 */
package types;

import java.util.LinkedList;
import java.util.List;


/**
 * @author cipi
 *
 */
public class BestResult {
	private List<Pair> freeSpaceslist = new LinkedList<Pair>();
	private List<Pair> list = new LinkedList<Pair>();
	private int numberToWin;
	private boolean user;
	
	public BestResult(int numberToWin, boolean user) {
		this.numberToWin = numberToWin;
		this.user = user;
	}
	
	public void addPair(Pair pair) {
		list.add(pair);
	}
	
	public void addFreeSpace(Pair pair) {
		freeSpaceslist.add(pair);
	}

	public List<Pair> getPositions() {
		return list;
	}

	public boolean isWinningPosition() {
		return list.size() >= numberToWin;
	}
	
	public int value() {
		int sign = 1;
		if (user) {
			sign = -1;
		}
		
		if(isWinningPosition()) {
			return sign *(list.size() * list.size());
		}
		
		if (freeSpaceslist.size() == 0) {
			return 0;
		}
		
		return sign * (2 * list.size() + freeSpaceslist.size());
		
	}
	
	public boolean isAlmostWinning(){
		return ((freeSpaceslist.size() > 0) && (list.size() == numberToWin - 1));
	}
	
	public Pair getFreeSpace() {
		return freeSpaceslist.get(0);
	}
}
