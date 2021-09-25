package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class ArrayUtils {

	public static int[][] transposeMatrix(int[][] m) {
		
		int rows = m.length;
		if (rows == 0) // empty matrix
			return m;
		int columns = m[0].length;
		int [][] transm = new int[columns][rows];// transposed matrix
		
		int row,column;
		for(column =0; column<columns;column++) {
			for(row = 0; row< rows;row++) {
				transm[column][row] = m[row][column];
			}
		}
		return transm; 
	}
	


	
	public static int[] shiftArrayCyclic(int [] array, int move, char direction) {
		int array_length = array.length;
		int correct_move= Math.abs(move) % array_length;
		if (move ==0 || move == array_length || (direction != 'R' &&  direction != 'L')) {
			return array;
		}
		int endlhs, startrhs;
		if ((move > 0 && direction == 'R') || move <0 && direction == 'L' ) { // move to the rigth
			endlhs = array_length - correct_move;
			startrhs = array_length - correct_move;	
		}
		else { // ((move < 0 && direction == 'R') || move > 0 && direction == 'L' ) {// move to the left
			endlhs = correct_move;
			startrhs = correct_move;
		}
		int [] lhs = Arrays.copyOfRange(array, 0, endlhs);
		int [] rhs = Arrays.copyOfRange(array, startrhs, array_length);

		int indexrhs;
		for (indexrhs =0 ; indexrhs < rhs.length; indexrhs ++) {
			array[indexrhs] = rhs[indexrhs];
		}

		int indexlhs;
		for(indexlhs=0 ;indexlhs< lhs.length;indexlhs++) {
			array[indexlhs + indexrhs] = lhs[indexlhs];
		}
		
		return array; 
	}

	public static int alternateSum(int[] array) {
		
		int array_length = array.length;
		if (array_length ==0)
			return 0;
		
		int [][]allaltsum = new int[array_length][array_length];
		int from,to;
		int altsum =0;
		int basepow;
		
		for(from=0; from<array_length;from++) { //Calculate all the alternate sums into a matrix
			basepow =0;
			altsum =0;
			for(to =from; to<array_length; to++) {
				altsum+= (int)Math.pow(-1, basepow)* array[to];
				allaltsum[from][to] = altsum;
				basepow++;
			}
		}
		int maxaltsum = allaltsum[0][0];  //  Find the largest sum
		for(from = 0; from <array_length; from++) {
			for(to=from;to<array_length;to++){
				if(allaltsum[from][to] > maxaltsum)
					maxaltsum = allaltsum[from][to];
			}
		}
		if (maxaltsum >=0)
			return maxaltsum;
		return 0;
		
	}
	
	
	/**
	 * @pre a and b square matrixes
	 * @post a*b
	 */
	private static int[][]multiplication(int[][] a,int[][] b){ // both square matrixes 
		int rows = a.length;
		int columns = rows;
		int  row, column, rowincolumn;
		int [][] mult = new int[rows][columns];
		for(row = 0 ;row < rows ;row++) {
			for(column = 0; column < columns; column++) {
				for(rowincolumn = 0; rowincolumn < columns; rowincolumn++) {
					mult[row][column] += a[row][rowincolumn] * b[rowincolumn][column];
				}
			}
		}	
		return mult;
	}

	public static int findPath(int[][] m, int i, int j, int k) {
		int rows = m.length;
		
		if (rows == 0 || k==0) // empty matrix
			return 0;
			
		int [][] mult = new int[rows][rows];
		System.arraycopy(m, 0, mult, 0, rows);
		int counter;
		for(counter=0;counter<k-1;counter++) { // m^k
			mult = multiplication(mult,mult);
		}
		if (mult[i][j]>=1)
			return 1;
		return 0;
	}	
}


