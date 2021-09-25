package il.ac.tau.cs.sw1.hw6;

import java.util.Arrays;

public class Test_Polynomial_sapir {
	
public static void main(String[] args) {
		
		Polynomial p1 = new Polynomial(); // p1 = 0
		double [] coe2 = {1,2,3,4};
		double [] coe3 = {2,2,2};
		double [] coe4 = {3,4,0,10};
		Polynomial p2 = new Polynomial(coe2);
		Polynomial p3 = new Polynomial(coe3);
		Polynomial p4 = new Polynomial(coe4);
		
		printpol(p1);
		printError(p1.getDegree() == 0, "the degree of p1 is 0");
		
		printpol(p2);
		printpol(p3);
		printpol(p4);
		System.out.println();
		
		System.out.println("check add:");
		Polynomial add1_2 = p1.adds(p2);
		printpol(add1_2);
		Polynomial add2_3 = p2.adds(p3);
		printpol(add2_3);
		Polynomial add3_4 = p3.adds(p4);
		printpol(add3_4);
		System.out.println();
		
		System.out.println("check multiplay:");
		Polynomial p1a = p1.multiply(5.6);
		Polynomial p2a = p2.multiply(4.0);
		Polynomial p3a = p3.multiply(-1.0);
		Polynomial p4a = p4.multiply(0.0);
		printpol(p1a);
		printpol(p2a);
		printpol(p3a);
		printpol(p4a);
		System.out.println();
		
		System.out.println("check getDgree:");
		printError(p1.getDegree() == 0, "the degree of p1 is 0");
		printError(p2.getDegree() == 3, "the degree of p2 is 3");
		printError(p3.getDegree() == 2, "the degree of p3 is 2");
		printError(p4.getDegree() == 3, "the degree of p4 is 3");
		Polynomial p5 = new Polynomial();
		printpol(p5);
		printError(p5.getDegree() == 0, "the degree of p5 is 0");
		
		System.out.println("check getCoefficient:");
		printError(p2.getCoefficient(0) == 1, "a_0 = 1");
		printError(p2.getCoefficient(2) == 3, "a_2 = 3");
		printError(p2.getCoefficient(5) == 0, "a_5 = 0");
		printError(p3.getCoefficient(0) == p3.getCoefficient(2), "a_0 = a_2");
		printError(p3.getCoefficient(4) ==0 , "a_4 = 0");
		printError(p4.getCoefficient(0) == 3, "a_0 = 3");
		printError(p4.getCoefficient(2) == 0, "a_2 = 0");
		System.out.println();
		
		System.out.println("check setCoefficient");
		printpol(p2);
		p2.setCoefficient(0, 8.0);
		printpol(p2);
		p2.setCoefficient(10, 100.5);
		printpol(p2);
		p2.setCoefficient(2, -29.7);
		printpol(p2);
		Polynomial p21 = new Polynomial(coe2);
		System.out.println();
		
		System.out.println("check firstDerivation");
		Polynomial p1_ = p1.getFirstDerivation();
		printpol(p1);
		printpol(p1_);
		Polynomial p2_ = p21.getFirstDerivation();
		printpol(p21);
		printpol(p2_);
		Polynomial p3_ = p3.getFirstDerivation();
		printpol(p3);
		printpol(p3_);
		Polynomial p4_ = p4.getFirstDerivation();
		printpol(p4);
		printpol(p4_);
		System.out.println();
		
		System.out.println("check compute");
		printpol(p21);
		double p22 = p21.computePolynomial(2);
		double p20 = p21.computePolynomial(0);
		double p2_1 = p21.computePolynomial(-1);
		System.out.println("p22 = " + p22 + " p20 = " + p20 + " p2_1 = " + p2_1);
		printError(p22 == 49 , "p22");
		printError(p20 == 1 , "p20");
		printError(p2_1 == -2 , "p2_1");
		System.out.println();
		
		System.out.println("check isExterma");
		double [] coe = {4.0,4.0,1.0};
		Polynomial p = new Polynomial(coe);
		System.out.println("true = " + p.isExtrema(-2));
		double [] coe6 = {1.0,-3.0,-3.0,1.0};
		Polynomial p6 = new Polynomial(coe6);
		System.out.println("false = " + p.isExtrema(-1)); // פיתול
		double [] coe7 = {-2.0,1.0,1.0};
		Polynomial p7 = new Polynomial(coe7);
		System.out.println("false = " + p7.isExtrema(1));
		System.out.println("true = " + p7.isExtrema(-0.5));
		System.out.println("false = " + p7.isExtrema(-2));
		System.out.println("false = " + p7.isExtrema(3));
		
		
	}

	public static void printError(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException("[ERROR] " + message);
		}
	}
	
	public static void printpol(Polynomial p) {
		System.out.println(Arrays.toString(p.getCoe()) + ", len = " + p.getlen());
	}
}
