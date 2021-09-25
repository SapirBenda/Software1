package il.ac.tau.cs.sw1.ex4;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class WordPuzzle {
	
//	private String Completing_zeros(String s, int k, int wordlen) {
//		int index;
//		for(index=0)
//	}
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;

	/*
	 * @pre: template is legal for word
	 */
	public static char[] createPuzzleFromTemplate(String word, boolean[] template) { // Q - 1
		char [] puzzle = word.toCharArray();
		int index;
		for(index = 0; index < puzzle.length; index++) {
			if (template[index])
				puzzle[index] = HIDDEN_CHAR;
		}
		return puzzle;
	}

	public static boolean checkLegalTemplate(String word, boolean[] template) { // Q - 2
		int index = 0, counter = 1;
		int wordlen = word.length();
		int templatelen = template.length;
		if (wordlen != templatelen || wordlen <= 1)
			return false;
		
		boolean firstintemplate = template[0];
		for(index =1; index<templatelen; index++) {
			if (firstintemplate == template[index])
					counter++;
		}
		if (counter == templatelen) // all characters are hidden or visible
			return false;
		
		int innerindex =0;
		for(index = 0; index< templatelen; index ++) {
			for(innerindex =index; innerindex< templatelen; innerindex++) {
				if (word.charAt(index) == word.charAt(innerindex) && template[index] != template[innerindex]) // the same charecter but one hidden an one known
					return false;
			}
		}
		return true;
	}
	
	
	
	private static int factorial(int x)	{
		int y = 1;
	    for (int i = 2; i <= x; i++)
	        y = y * i;
	    return y;
	}
	
	private static int nCk (int n, int k) { // computing n choose k
		return factorial(n) / (factorial(k) * factorial(n - k));
	}
	
	
	/*
	 * @pre: 0 < k < word.lenght(), word.length() <= 10
	 */
	
	public static boolean[][] getAllLegalTemplates(String word, int k){  // Q - 3
		
		int index, alltemplateindex=0, innerindex, onecounter=0, optionscounter=0;
		int wordlen = word.length();
		
		if(k == 0 || k >= wordlen) //  at least on word need to be hidden or known
			return new boolean[0][0];
		
		int nCk = nCk(wordlen,k);
		
		boolean [][] allttemplate = new boolean[nCk][wordlen]; // all the good possible templates
		boolean [] indexoftemplate = new boolean[nCk]; // will help us later
		
		int minbinary=0, maxbinary=0;
		for(index=0; index<k;index++) { // finding the scope of the possible binary strings
			minbinary += (int)Math.pow(2, index);
			maxbinary += (int)Math.pow(2, wordlen-1-index);
			}
		
		String indexasbinary;
		char [] optemplate = new char [wordlen];// a template - may be bad for us
		
		for(index = minbinary; index<= maxbinary; index++) {
			boolean [] ktemplate = new boolean[wordlen]; // a template with k hiiden figures - may be good for us
			indexasbinary = Integer.toBinaryString(index);
			indexasbinary = new String(new char[word.length() - indexasbinary.length()]).replace("\0","0") + indexasbinary;
			optemplate= indexasbinary.toCharArray();
			onecounter =0;
			for(innerindex=0; innerindex<optemplate.length; innerindex++) { // creating boolean array according indexasstring
				if(optemplate[innerindex] == '1') {
					onecounter++;
					ktemplate[innerindex]= true;
				}
			}

			if (onecounter == k && checkLegalTemplate(word,ktemplate) ) {// a good option
				allttemplate[alltemplateindex] = ktemplate; // adding to all options matrix
				indexoftemplate[alltemplateindex] = true;// saving the rows of good templats for later
				alltemplateindex++;
				optionscounter++;
				}
		}
		
		if(optionscounter == 0) // dosent have any posiible template
			return new boolean[0][0]; // an empty matrix
		
		boolean [][] goodtemplats = new boolean[optionscounter][wordlen];
		int goodindex=0;
		for(index = 0; index<indexoftemplate.length;index++) {
			if(indexoftemplate[index]) {
				goodtemplats[goodindex] = allttemplate[index];
				goodindex++;
			}	
		}
		return goodtemplats;
	}
	
	
	/*
	 * @pre: puzzle is a legal puzzle constructed from word, guess is in [a...z]
	 */
	public static int applyGuess(char guess, String word, char[] puzzle) { // Q - 4
		int index, counter =0;
		for(index =0; index<puzzle.length; index++) {
			if(word.charAt(index) == guess && puzzle[index] == HIDDEN_CHAR) {
				puzzle[index] = guess;
				counter ++;
			}
		}
		return counter;
	}
	

	/*
	 * @pre: puzzle is a legal puzzle constructed from word
	 * @pre: puzzle contains at least one hidden character. 
	 * @pre: there are at least 2 letters that don't appear in word, and the user didn't guess
	 */
	public static char[] getHint(String word, char[] puzzle, boolean[] already_guessed) { // Q - 5
		char [] hint = new char[2];
		char goodhint,badhint;
		char [] optionsforgoodhint = new char[word.length()]; // good possible options
		char [] optionsforbadhint = new char[26]; // bad posiible options
		int index,innerindex=0,indexgoodhint=0,indexbadhint =0 ;
		boolean inword = false;
	

		// finding all options for a good hint
		for(index=0; index<puzzle.length;index++) {
			char charinword = word.charAt(index);
			if(puzzle[index]== HIDDEN_CHAR && !already_guessed[(int)charinword -97]) { // hiiden charecter && haven't guessed yet
				optionsforgoodhint[indexgoodhint] = charinword;
				indexgoodhint++;
			}
		}
		
		char [] good = new char[indexgoodhint];
		System.arraycopy(optionsforgoodhint, 0, good, 0, indexgoodhint);
		
		// finding all options for a bad hint
		for(index =0; index<already_guessed.length; index++) {
			if(!already_guessed[index]) { // haven't guessed yet
				for(innerindex=0; innerindex < word.length(); innerindex++) {
					if((int)word.charAt(innerindex) - 97 == index)
						inword= true;					
						
				}
				if (!inword) { // not in word
					optionsforbadhint[indexbadhint] = (char)(index+97);
					indexbadhint ++;
				}
			}
			inword = false;
		}
		
		char [] bad = new char[indexbadhint];
		System.arraycopy(optionsforbadhint, 0, bad, 0, indexbadhint);
		
		Random r = new Random();
		int randomindexgood = r.nextInt(good.length);
		int randomindexbad = r.nextInt(bad.length);
		goodhint = good[randomindexgood];
		badhint = bad[randomindexbad];
		
		if(goodhint<badhint) { 
			hint[0] = goodhint;
			hint[1] = badhint;
		}else {
			hint[1] = goodhint;
			hint[0] = badhint;
		}
		
		return hint;
	}

	

	public static char[] mainTemplateSettings(String word, Scanner inputScanner) { // Q - 6
		char [] puzzle = new char[word.length()]; // returned array
		boolean [] gametemplate = new boolean[word.length()]; // template
		int gametemplateindex;
		int  index;
		boolean legal = false;
		
		printSettingsMessage();
		
		while(!legal) {
			gametemplateindex =0;
			printSelectTemplate();
			int way = inputScanner.nextInt();// 1 or 2
	
			if (way == 1) { //  random template
				printSelectNumberOfHiddenChars();
				boolean [][] allLegaltemplate = getAllLegalTemplates(word,inputScanner.nextInt());

				if(allLegaltemplate.length !=0) {// there are options - choose one randomly
					Random r = new Random();
					int randline = r.nextInt(allLegaltemplate.length);
					gametemplate = allLegaltemplate[randline];
					legal = true;
				}
				else
					printWrongTemplateParameters(); // there arent options templats.
			}	
			else {// way == 2 --> manual template
				printEnterPuzzleTemplate();
				String s = inputScanner.next();
				String [] scannertemplate = s.split(",");
				for(index = 0; index<scannertemplate.length;index++) { // converting to a boolean array
					if (scannertemplate[index].equals("X"))
						gametemplate[gametemplateindex] = false;
					else
						gametemplate[gametemplateindex] = true; // a hidden char
					gametemplateindex++;		
					}
				legal = checkLegalTemplate(word,gametemplate);
				if (!legal)
					printWrongTemplateParameters();		
				}
				
			}// legal == true
		
		puzzle = createPuzzleFromTemplate(word,gametemplate); // creating the puzzle
		return puzzle;
		
	}
	
	
	public static void mainGame(String word, char[] puzzle, Scanner inputScanner){ // Q - 7
		printGameStageMessage();
		int numberofattempts = 3,index, numberofhiiden = 0;
		boolean [] already_guessed = new boolean[26];
		
		for(index = 0; index< puzzle.length;  index++) {
			if(puzzle[index] == HIDDEN_CHAR) {
				numberofattempts++;
				numberofhiiden++;
			}
		}
		
		while(numberofhiiden > 0 && numberofattempts > 0) { // stage c
			printPuzzle(puzzle);
			printEnterYourGuessMessage();
			String s = inputScanner.next();
			if ( s.equals("H")) {
				char[] hint = getHint(word,puzzle,already_guessed);
				printHint(hint);
			}
			else { // guessed a letter
				numberofattempts--;
				char c = s.charAt(0);
				already_guessed[(int)c-97] = true; // keeping the guess
				int guessednum = applyGuess(c,word,puzzle);
				numberofhiiden -= guessednum;
				if(guessednum >0) { // good guess
					if(numberofhiiden == 0) 
						printWinMessage();
					else
						printCorrectGuess(numberofattempts);
				}
				else // bad guess
					printWrongGuess(numberofattempts);
			}
		}
		if(numberofattempts ==0)
			printGameOver();
	}
				
				


/*************************************************************/
/********************* Don't change this ********************/
/*************************************************************/

	public static void main(String[] args) throws Exception { 
		if (args.length < 1){
			throw new Exception("You must specify one argument to this program");
		}
		String wordForPuzzle = args[0].toLowerCase();
		if (wordForPuzzle.length() > 10){
			throw new Exception("The word should not contain more than 10 characters");
		}
		Scanner inputScanner = new Scanner(System.in);
		char[] puzzle = mainTemplateSettings(wordForPuzzle, inputScanner);
		mainGame(wordForPuzzle, puzzle, inputScanner);
		inputScanner.close();	
		
	}


	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterWord() {
		System.out.println("Enter word:");
	}
	
	public static void printSelectNumberOfHiddenChars(){
		System.out.println("Enter number of hidden characters:");
	}
	public static void printSelectTemplate() {
		System.out.println("Choose a (1) random or (2) manual template:");
	}
	
	public static void printWrongTemplateParameters() {
		System.out.println("Cannot generate puzzle, try again.");
	}
	
	public static void printEnterPuzzleTemplate() {
		System.out.println("Enter your puzzle template:");
	}


	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}


	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printHint(char[] hist){
		System.out.println(String.format("Here's a hint for you: choose either %s or %s.", hist[0] ,hist[1]));

	}
	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle!");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}
}
