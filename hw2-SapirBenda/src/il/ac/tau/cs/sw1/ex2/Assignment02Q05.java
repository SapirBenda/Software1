package il.ac.tau.cs.sw1.ex2;
import java.util.Arrays;

public class Assignment02Q05 {

	public static void main(String[] args){
		// do not change this part below
		int N = Integer.parseInt(args[0]);
		
		int[][] matrix = new int[N][N]; // the input matrix to be
		int[][] rotatedMatrix= new int[N][N]; // the rotated matrix
		int [] colTorow = new int[N];
		
		for(int i=0;i < N; i++){
			for(int j=0; j < N; j++){
				matrix[i][j] = Integer.parseInt(args[1+(i*N)+j]); // the value at [i][j] is the i*N+j item in args (without considering args[0])
			}
		}
		for(int i=0;i < N; i++)   // printing the original matrix
			System.out.println(Arrays.toString(matrix[i]));
		System.out.println("");
		
		int column;
		for (column = 0; column < N ; column++){
			int row;
			for (row = 0 ; row < N; row++ ){ 
				colTorow[row] = matrix[row][column];
			}
			for (int j =0; j < N; j ++) {
				rotatedMatrix[column][j] = colTorow[N-1-j];
			}
		}
		
		// do not change this part below
		for(int i=0;i < N; i++){ 
			System.out.println(Arrays.toString(rotatedMatrix[i])); // this would compile after you would put value in transposedMatrix
		}
	}
}
