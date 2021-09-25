package il.ac.tau.cs.sw1.ex5;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);	
	}
	
	/*
	 * @post: true if word in vocabulary, else false
	 */
	private boolean In_Vocabulary(String word, String[] vocabulary,int vocabulary_index) {
		int index;
		for(index=0;index<vocabulary_index;index++) {
			if(vocabulary[index].toLowerCase().equals(word.toLowerCase()))
				return true;
		}
		return false;
	}
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		String [] vocabulary = new String[MAX_VOCABULARY_SIZE];
		FileReader file = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader (file);
		String line, word;
		char ch;
		int counter_numbers;
		String [] words_in_line;
		char [] chars_in_word;
		boolean isword = false, isnumber = false, some_num_in_voc = false;
		int index, innerindex, vocaindex =0;
		
		
		while((line = bufferedReader.readLine()) != null && vocaindex < MAX_VOCABULARY_SIZE) {
			words_in_line = line.split(" "); 
			for(index =0; index< words_in_line.length; index++) { 
				if(vocaindex < MAX_VOCABULARY_SIZE) {
					word = words_in_line[index];
					chars_in_word = word.toCharArray();
					isword = false; isnumber = false;
					
					if(!In_Vocabulary(word,vocabulary,vocaindex)) { 
						for(innerindex = 0; innerindex < chars_in_word.length;innerindex++) {
							ch = chars_in_word[innerindex];
							if(Character.isLetter(ch)) {
								isword = true;
								chars_in_word[innerindex] = Character.toLowerCase(ch);
							}
						}
						if (isword) {
							word = new String(chars_in_word);
							vocabulary[vocaindex] = word;
							vocaindex++;
						}
					}
					if(!some_num_in_voc && !isword) {
						counter_numbers = 0;
						for(innerindex = 0; innerindex< chars_in_word.length;innerindex++) {
							ch = chars_in_word[innerindex];
							if(Character.isDigit(ch)) {
								counter_numbers ++;	
							}
						}
						if(counter_numbers == chars_in_word.length ) {
							isnumber = true;
							word = SOME_NUM;
						}
						if(isnumber) {
							vocabulary[vocaindex] = word;
							vocaindex++;
							some_num_in_voc= true;
						}
					}
				}
			}
		}
		String [] returned_vocabulary = new String[vocaindex];
		System.arraycopy(vocabulary, 0, returned_vocabulary, 0, vocaindex);
		bufferedReader.close();
		return returned_vocabulary;
	}
	/*
	 * @post:if word in vocabulary --> index of word in  vocabulary
	 * @post: if word not in vocabulary --> return -1
	 */
	private int find_word_index_in_vocabulary(String word, String[] vocabulary) {
		int index;
		for(index=0;index<vocabulary.length;index++) {
			if(vocabulary[index].equals(word.toLowerCase()))
				return index;	
		}
		return -1;
	}
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		int voclen = vocabulary.length,index,index_first_word, index_second_word;
		int [][] bigramCounts = new int[voclen][voclen];
		FileReader file = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader (file);
		String line, first_word, second_word;
		String [] words_in_line;

		while((line = bufferedReader.readLine()) != null) {
			words_in_line = line.split(" ");
			
			for(index=0; index< words_in_line.length-1;index++) {
				first_word = words_in_line[index];
				second_word = words_in_line[index+1];
				try {
				    int intValue = Integer.parseInt(second_word);
				    second_word = SOME_NUM;
				} catch (NumberFormatException e) {}
				try {
				    int intValue = Integer.parseInt(first_word);
				    first_word = SOME_NUM;
				} catch (NumberFormatException e) {}

				if(In_Vocabulary(first_word, vocabulary,voclen) && In_Vocabulary(second_word, vocabulary, voclen)) {
					index_first_word = find_word_index_in_vocabulary(first_word, vocabulary);
					index_second_word = find_word_index_in_vocabulary(second_word, vocabulary);
					bigramCounts[index_first_word][index_second_word]++;
				}
			}		
		}
		bufferedReader.close();
		return bigramCounts;

	}
	
	private String add_suffix(String fileName, String suffix) {
		StringBuilder sb = new StringBuilder(fileName);
		sb.append(suffix);
		String file_name_with_suffix = sb.toString();
		return file_name_with_suffix;
	}
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		
		String voc_file = add_suffix(fileName,VOC_FILE_SUFFIX );
		String counts_file = add_suffix(fileName,COUNTS_FILE_SUFFIX );
		FileWriter file_voc = new FileWriter(voc_file);
		BufferedWriter voc = new BufferedWriter (file_voc);
		FileWriter file_counts = new FileWriter(counts_file);
		BufferedWriter counts = new BufferedWriter (file_counts);
	
		int index, row,column;
		int voclen = mVocabulary.length;
		int bigramlen = mBigramCounts.length;
		
		String first_row = voclen + " words";
		String s;
		voc.write(first_row);
		
		for(index=0; index<voclen; index++) {
			voc.newLine();
			s = index + "," + mVocabulary[index];
			voc.write(s);
		}
		for(row = 0; row<bigramlen;row++) {
			for(column = 0; column<bigramlen; column++) {
				if(mBigramCounts[row][column] !=0) {
					s = row + "," + column + ":" + mBigramCounts[row][column];
					counts.write(s);
					counts.newLine();
				}
			}
		}
		voc.close();
		counts.close();
	}
	
	
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4

		String voc_file = add_suffix(fileName,VOC_FILE_SUFFIX );
		String counts_file = add_suffix(fileName,COUNTS_FILE_SUFFIX );
		
		FileReader file_voc = new FileReader(voc_file);
		BufferedReader voc = new BufferedReader (file_voc);
		
		FileReader file_counts = new FileReader(counts_file);
		BufferedReader counts = new BufferedReader (file_counts);
		
		String first_line = voc.readLine();
		String [] firatasarray = first_line.split(" ");
		int voclen = Integer.parseInt(firatasarray[0]);
		String [] vocabulary = new String[voclen];
		String line;
		String [] words_in_line;
		
		while((line = voc.readLine()) != null) {
			words_in_line = line.split(",",2);
			vocabulary[Integer.parseInt(words_in_line[0])] = words_in_line[1];
		}
		this.mVocabulary = vocabulary.clone();
		
		int [][] BigramCounts = new int [voclen][voclen];
		int row, column, times;
		String [] s ;
		while((line = counts.readLine()) != null) {
			words_in_line = line.split(",");
			s = words_in_line[1].split(":");
			row = Integer.parseInt(words_in_line[0]);
			column = Integer.parseInt(s[0]);
			times = Integer.parseInt(s[1]);
			BigramCounts[row][column] = times;
		}
		this.mBigramCounts = BigramCounts.clone();
	
		voc.close();
		counts.close();
	}

	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: word is in lowercase
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		int index;
		for(index=0;index<mVocabulary.length;index++) {
			if(mVocabulary[index].equals(word))
				return index;	
		}
		return ELEMENT_NOT_FOUND;
	}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		int index1 = getWordIndex(word1);
		int index2 = getWordIndex(word2);
		if (index1 !=ELEMENT_NOT_FOUND &&  index2!= ELEMENT_NOT_FOUND ) {
			return mBigramCounts[index1][index2];
		}
		return 0;
	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		int index, maxcount=0, getbigcount;
		String freqword = null, word2;

		for(index=0; index < mVocabulary.length;index++) {
			word2 = mVocabulary[index];
			getbigcount = getBigramCount(word,word2);
			if(maxcount < getbigcount) {
				maxcount = getbigcount;
				freqword = word2;
			}
		}
		return freqword;
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		String [] words_in_line = sentence.split(" ");
		int sentencelen = words_in_line.length;
		int index,firstinvoc, secinvoc;
		String word1, word2;
		if (sentencelen ==0)
			return true;
		if(sentencelen == 1) {
			if(getWordIndex(words_in_line[0]) != ELEMENT_NOT_FOUND)
				return true;
			else
				return false;
		}
		
		
		for(index=0; index<sentencelen-1;index++) {
			word1 = words_in_line[index];
			word2 = words_in_line[index+1];
			firstinvoc = getWordIndex(word1);
			secinvoc = getWordIndex(word2);
			if(firstinvoc == ELEMENT_NOT_FOUND || secinvoc == ELEMENT_NOT_FOUND)
				return false;
			else if(getBigramCount(word1,word2) == 0)
				return false;
			
		}
		return true;
	}
	
	
	private static boolean ZerosCheck(int [] arr) {
		int arrlength = arr.length,index;
		for(index = 0; index<arrlength;index++) {
			if(arr[index] !=0)
				return false;
		}
		return true;
		
	}
	/*
	 * @pre: arr1.length = arr2.legnth != 0
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calcluates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9
		int denominatorA=0,denominatorB =0 ,index;
		double denominator, numerator = 0, ratio;
		int length = arr1.length;
		boolean zero1 = ZerosCheck(arr1);
		boolean zero2 =  ZerosCheck(arr2);
		
		if(zero1 || zero2)
			return ELEMENT_NOT_FOUND;
		
		for(index=0;index<length;index++) {
			numerator += arr1[index]*arr2[index];
			denominatorA += Math.pow(arr1[index], 2);
			denominatorB += Math.pow(arr2[index], 2);
		}
		
		denominator = Math.sqrt(denominatorA) * Math.sqrt(denominatorB);
		ratio = numerator/ denominator; 
		
		return ratio;
	}

	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10
		int wordindex = getWordIndex(word);
		int [] wordvector = mBigramCounts[wordindex];
		int [] otherwordvector;
		int index;
		double curr_ratio,maxratio=ELEMENT_NOT_FOUND;
		String similarword = word;

		for(index = 0; index<mBigramCounts.length;index++) {
			if(index!= wordindex) {
				otherwordvector = mBigramCounts[index];
				curr_ratio = calcCosineSim(wordvector,otherwordvector);
				if(maxratio < curr_ratio) {
					maxratio = curr_ratio;
					similarword = mVocabulary[index];
				}
			}
		}
		return similarword;
	}
	
}
