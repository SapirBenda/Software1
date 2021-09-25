package il.ac.tau.cs.sw1.hw6;

public class Polynomial {
	
	private double [] coefficients = {0};
	private int len = 1;
	
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial()
	{
		int index;
		for(index =0; index< len; index++) {
			this.coefficients[index] = 0;
		}
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		int len = coefficients.length;
		this.coefficients = new double[len];
		System.arraycopy(coefficients, 0, this.coefficients, 0,len);
		this.len = len;
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	
	private double [] zero_array (int len){
		int index;
		double [] zero_arr = new double[len];
		for(index=0; index<len;index++)
			zero_arr [index] =0;
		return zero_arr;
			
	}
	
	public Polynomial adds(Polynomial polynomial)
	{
		int max_len = Math.max(this.len,polynomial.len);
		double [] coi_sum = zero_array(max_len); 
		int index;
		
		for(index=0; index<this.len ; index++) coi_sum[index] += this.coefficients[index];
		for(index =0; index< polynomial.len; index++) coi_sum[index] += polynomial.coefficients[index];
		
		Polynomial sum = new Polynomial(coi_sum);
		return sum;
	}
	
	
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		double [] new_cofi =new double [this.len];
		System.arraycopy(coefficients, 0, new_cofi, 0, len);
		int index;
		for(index=0; index<len;index++) {
			new_cofi[index] *= a;
		}
		
		Polynomial ap = new Polynomial(new_cofi);
		return ap;
		
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		if (this.len <=1)
				return 0;
		int index = len-1;
		while(this.coefficients[index] == 0 && index > 0) {
			index--;
		}
		return index;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		return (n < len) ? coefficients[n]  : 0.0;
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)	{
		if(n < len)
			coefficients[n] = c;
		else {
			double [] new_coe = new double [n+1];
			System.arraycopy(coefficients, 0, new_coe, 0, len);
			int index;
			for(index=len; index<n-1;index++) {
				new_coe[index] = 0.0;
			}
			new_coe[n] = c;
			this.coefficients = new double[n+1];
			System.arraycopy(new_coe, 0, coefficients, 0, n+1);
			this.len = n+1;
		}	
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		if (len == 1) {
			Polynomial c = new Polynomial();
			return c;
		}
			
		double [] new_coe = new double[len-1];
		System.arraycopy(coefficients, 1, new_coe, 0, len-1);

		int index;
		for(index=0;index<new_coe.length; index++)
			new_coe [index] *= (index+1);
		
		Polynomial der = new Polynomial(new_coe);
		return der;
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{
		int index;
		double sum = 0;
		for(index =0; index<len;index++)
			sum+= this.coefficients[index]*Math.pow(x, index);
		
		return sum;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial firstdiv = this.getFirstDerivation();
		if (firstdiv.computePolynomial(x) == 0) {
			Polynomial seconddiv = firstdiv.getFirstDerivation();
			if(seconddiv.computePolynomial(x) !=0)
				return true;
		}
		return false;
	}
	
	
	public  double[] getCoe() {
		return this.coefficients;
	}
	public int getlen() {
		return this.len;
	}

    
   
}
