package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.Comparator;

import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

class RankedWordComparator implements Comparator<RankedWord>{
	private rankType rank_type;
	public RankedWordComparator(rankType cType) {
		this.rank_type = cType;
	}
	
	@Override
	public int compare(RankedWord o1, RankedWord o2) {
		int r1 = o1.getRankByType(rank_type);
		int r2 = o2.getRankByType(rank_type);
		return Integer.compare(r1, r2);
	}	
}
