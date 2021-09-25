package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IllegalKValueException;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	public static final int UNRANKED_CONST = 30;
	public HashMap <String,HashMapHistogram<String>> AppearancesByFile = new HashMap<>(); // for each file dict - for each word number of appearance
	public HashMap<String,RankedWord> wordsRankedWord = new HashMap<>();// for each word keeps it's RankedWord
	public HashMap<String,List<String>> wordsInEveryFile = new HashMap<>(); // for every file keeps a list with it's word sorted
	public Set<String> allwords = new HashSet<String>(); // set with all the words
	
	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */
  	public void indexDirectory(String folderPath) {
		//This code iterates over all the files in the folder. add your code wherever is needed	
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				try {
					String filename = file.getName();
					List<String> wordsInFile = FileUtils.readAllTokens(file); // list with all the word from the file
					HashMapHistogram <String> wordsApperancesInFile = creatwordsApperancesInFile(wordsInFile);
					AppearancesByFile.put(file.getName(), wordsApperancesInFile);
					List<String> ranks = createRankList(wordsApperancesInFile);
					allwords.addAll(ranks);
					wordsInEveryFile.put(filename, ranks);
				} catch(IOException e){ // file isnt real :(
					continue;}
			}
		}
		buildwordsRankedWord();
	}
  	
  	
  	public HashMapHistogram<String> creatwordsApperancesInFile(List<String> wordsInFile){
  		HashMapHistogram <String> wordsApperancesInFile = new HashMapHistogram<>();
  		int index, innerindex, wordCounter =1;
  		String word, checkword;
  		for(index=0; index< wordsInFile.size();index++) {
			word = wordsInFile.get(index).toLowerCase();
			if(wordsApperancesInFile.getCountForItem(word) == 0) { // didnt count this word yet
				for(innerindex = index+1; innerindex < wordsInFile.size(); innerindex++) {
					checkword = wordsInFile.get(innerindex);
					if (word.equals(checkword)) 
						wordCounter++;
				}
				try {
				wordsApperancesInFile.addItemKTimes(word, wordCounter);
				} catch(IllegalKValueException e) {
					continue; }
				wordCounter = 1; // initial counter
			}
		}
  		return wordsApperancesInFile;
  	}
  	
  	public List<String> createRankList(HashMapHistogram<String> wordsApperancesInFile){
  		Iterator<String> wordsasiterator = wordsApperancesInFile.iterator();
		List<String> ranks = new ArrayList<String>(); // sorted list with all words in file - their index+1 is their rank
		while (wordsasiterator.hasNext()) {
			ranks.add(wordsasiterator.next());
		}
		return ranks;
  	}
  	
  	
  	public void printwordsRankedWord() {
  		Map<String,Integer> hash;
  		RankedWord c;
  		System.out.println("----------wordsRankedWord----------");
  		for(String key:wordsRankedWord.keySet()) {
  			System.out.println(key + ": ");
  			c = wordsRankedWord.get(key);
  			hash = c.getRanksForFile();
  			for(String file:hash.keySet()) {
  				System.out.println("          "+file+": " + hash.get(file));
  			}
  		}
  		System.out.println();
  	}
  	
  	
  	public void printAppearancesByFile() {
  		System.out.println("--------------AppearancesByFile--------------");
  		for(String file: AppearancesByFile.keySet()) {
  			System.out.println("---------- "+file+": ----------");
  			AppearancesByFile.get(file).printhashmap();
  		}
  		System.out.println();
  	}
	
  	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException{
		HashMapHistogram<String> wordsappearanceshistogram = AppearancesByFile.get(filename);
		if(wordsappearanceshistogram == null)
			throw new FileIndexException("filename not in index !");
		String lowercaseword = word.toLowerCase();
		return wordsappearanceshistogram.getCountForItem(lowercaseword);
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException{
		List<String> ranks = wordsInEveryFile.get(filename);
		if(ranks == null)
			throw new FileIndexException("filename not in index !");
		int index = ranks.indexOf(word);
		if(index != -1)
			return index +1;
		return ranks.size() + UNRANKED_CONST;
	}
	
	
	public void buildwordsRankedWord() {
		for(String word:allwords) {
			HashMap <String,Integer> ranksByFile = new HashMap<>();
			for(String filename : wordsInEveryFile.keySet()) {
				try {
					int rank = getRankForWordInFile(filename,word);
					ranksByFile.put(filename,rank);
				}catch(FileIndexException e) {
					continue;}
			}
			RankedWord rankword = new RankedWord(word,ranksByFile);
			wordsRankedWord.put(word, rankword);
		}
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word){
		return wordsRankedWord.get(word).getAverageRank();
	}
	
	public List<String> sortedListSmallerThenk(rankType cType, int k){
		List<String> allRankedWordsSmallerThenk = new ArrayList<String>();
		for(String word: allwords) {
			int type = wordsRankedWord.get(word).getRankByType(cType);
			if (type <k)
				allRankedWordsSmallerThenk.add(word);
		}
		Collections.sort(allRankedWordsSmallerThenk);
		return allRankedWordsSmallerThenk;
	}
	
	public List<String> getWordsWithAverageRankSmallerThanK(int k){
		return sortedListSmallerThenk(RankedWord.rankType.average, k);
	}
	
	public List<String> getWordsWithMinRankSmallerThanK(int k){
		return sortedListSmallerThenk(RankedWord.rankType.min, k);
	}
	
	public List<String> getWordsWithMaxRankSmallerThanK(int k){
		return sortedListSmallerThenk(RankedWord.rankType.max, k);
	}

}
