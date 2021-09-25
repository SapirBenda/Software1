package il.ac.tau.cs.sw1.ex5;


import java.io.IOException;
import java.util.Arrays;

public class BigramModelTester1 {
	public static final String ALL_YOU_NEED_FILENAME = "resources/hw5/all_you_need.txt";
	public static final String EMMA_FILENAME = "resources/hw5/emma.txt";
	public static final String ALL_YOU_NEED_MODEL_DIR = "resources/hw5/all_you_need_model";

	public static void main(String[] args) throws IOException{
		BigramModel sG = new BigramModel();
		String[] voc = sG.buildVocabularyIndex(ALL_YOU_NEED_FILENAME);
		if (voc.length != 5){
			System.out.println(Arrays.toString(voc));
			System.out.println("Error 1.1");
		}
		if (!Arrays.equals(voc, new String[]{"love", "all", "you", "need", "is"})){
			System.out.println("Error 1.2");
		}
		
		int[][] counts = sG.buildCountsArray(ALL_YOU_NEED_FILENAME, voc);
		if (counts[0][0] != 3){ //count of "love love"
			System.out.println("Error 2.1");
		}
		
		if (counts[1][2] != 3){ //count of "all you"
			System.out.println("Error 2.2");
		}
		
		sG.initModel(ALL_YOU_NEED_FILENAME);
		sG.saveModel(ALL_YOU_NEED_MODEL_DIR);
		sG.loadModel(ALL_YOU_NEED_MODEL_DIR);
		
		if (!Arrays.equals(sG.mVocabulary, new String[]{"love", "all", "you", "need", "is"})){
			System.out.println("Error 4.1");
		}
		
		if (sG.getWordIndex("love") != 0){
			System.out.println("Error 5.1");
		}
		
		if (sG.mBigramCounts[0][0] != 3){ //count of "love love"
			System.out.println("Error 6.1");
		}
		
		if (sG.mBigramCounts[1][2] != 3){ //count of "all you"
			System.out.println("Error 6.2");
		}
		
		if (sG.getBigramCount("is", "love") != 2){
			System.out.println("Error 6.3");
		}
		
		if (sG.getBigramCount("penny", "lane") != 0){
			System.out.println("Error 6.4");
		}
		if (!sG.getMostFrequentProceeding("is").equals("love")){
			System.out.println("Error 7.1");
		}
		if (!sG.getMostFrequentProceeding("you").equals("need")){
			System.out.println("Error 7 my example");
		}
		if (!sG.getMostFrequentProceeding("love").equals("love")){
			System.out.println("Error 7 my example");
		}
		//add man to the end of all_you_need.txt and check this
//		if (sG.getMostFrequentProceeding("man") != null){
//			System.out.println("Error 7 my example");
//		}

		
		if (!sG.isLegalSentence("love is all")){
			System.out.println("Error 8.1");
		}
		
		if (sG.isLegalSentence("love is is")){
			System.out.println("Error 8.2");
		}
		
		if (sG.isLegalSentence("love the beatles")){
			System.out.println("Error 8.3");
		}
		if (!sG.isLegalSentence("you need")){
			System.out.println("Error 8 my example");
		}
		
		if (BigramModel.calcCosineSim(new int[] {1,2,0,4, 2}, new int[] {5, 0, 3, 1, 1}) != 11./30){
			System.out.println("Error 9.1");
		}
		if (BigramModel.calcCosineSim(new int[] {0,0,0,0,0}, new int[] {5, 0, 3, 1, 1}) != -1){
			System.out.println("Error 9 my example");
		}
		if (BigramModel.calcCosineSim(new int[] {4,0,0,0,0}, new int[] {2, 1, 3, 1, 1}) != 0.5){
			System.out.println("Error 9 my example");
		}

		sG.initModel(EMMA_FILENAME);
	
	
		if (!sG.getClosestWord("good").equals("great")){
			System.out.println("Error 10.1");
		}
		
		if (!sG.getClosestWord("emma").equals("she")){
			System.out.println("Error 10.2");
		}

		System.out.println("done!");
		
	}
}
