package il.ac.tau.cs.sw1.hw6;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PolynomialTest {

    @Test(dependsOnMethods = "testGetDegree")
    public void testConstructor() {
        Polynomial emptyConstructor = new Polynomial();
        Polynomial arrayConstructor = new Polynomial(new double[]{0});

        assertPolynomialEquals(emptyConstructor, arrayConstructor);
    }

    @DataProvider
    private Object[][] testAddsDataProvider() {
        return new Object[][]{
                {new Polynomial(), new Polynomial(), new Polynomial()},
                {new Polynomial(new double[]{4}), new Polynomial(new double[]{4}), new Polynomial(new double[]{8})},
                {new Polynomial(new double[]{-4, 1}), new Polynomial(new double[]{4, 1}), new Polynomial(new double[]{0, 2})},
                {new Polynomial(new double[]{4, 1}), new Polynomial(new double[]{4, -1}), new Polynomial(new double[]{8})},
                {new Polynomial(new double[]{1}), new Polynomial(new double[]{0, -1}), new Polynomial(new double[]{1, -1})},
                {new Polynomial(new double[]{0, -1, 30}), new Polynomial(new double[]{1, 0, -30}), new Polynomial(new double[]{1, -1})},

        };
    }

    @Test(dataProvider = "testAddsDataProvider", dependsOnMethods = "testGetDegree")
    public void testAdds(Polynomial a, Polynomial b, Polynomial expected) {
        Polynomial result = a.adds(b);
        assertPolynomialEquals(result, expected);
    }

    @DataProvider
    private Object[][] testMultiplyDataProvider() {
        return new Object[][]{
                {new Polynomial(), Integer.MAX_VALUE, new Polynomial()},
                {new Polynomial(new double[]{10, 51, 123154.9}), 0, new Polynomial()},
                {new Polynomial(new double[]{10, 51, 123154.9}), 1, new Polynomial(new double[]{10, 51, 123154.9})},
                {new Polynomial(new double[]{10, 51, 300}), 20, new Polynomial(new double[]{200, 1020, 6000})}
        };
    }

    @Test(dataProvider = "testMultiplyDataProvider", dependsOnMethods = "testGetDegree")
    public void testMultiply(Polynomial polynomial, double multiplier, Polynomial expected) {
        Polynomial result = polynomial.multiply(multiplier);
        assertPolynomialEquals(result, expected);
    }

    @DataProvider
    private Object[][] testGetDegreeDataProvider() {
        return new Object[][]{
                {new Polynomial(), 0},
                {new Polynomial(new double[]{1}), 0},
                {new Polynomial(new double[]{1, 1}), 1},
                {new Polynomial(new double[]{1, 1, 0, 0, 0, 0}), 1},
                {new Polynomial(new double[]{1, 0, 0, 0, 0.01}), 4},
                {new Polynomial(new double[]{1, 0, 0, 0, -0.01}), 4},
        };
    }

    @Test(dataProvider = "testGetDegreeDataProvider")
    public void testGetDegree(Polynomial polynomial, int expected) {
        assertEquals(polynomial.getDegree(), expected);
    }

    @DataProvider
    private Object[][] testGetCoefficientDataProvider() {
        return new Object[][]{
                {new Polynomial(), 0, 0},
                {new Polynomial(new double[]{101}), 0, 101},
                {new Polynomial(new double[]{101, 0, 0, 3}), 1, 0},
                {new Polynomial(new double[]{101, 0, 0, 3}), 2, 0},
                {new Polynomial(new double[]{101, 0, 0, -3}), 3, -3},
                {new Polynomial(new double[]{101, 0, 0, -3}), 100, 0},
                {new Polynomial(new double[]{101, 0, 0, -3}), 4, 0},
                {new Polynomial(new double[]{101, 10, -453, -3, 154}), 4, 154},
        };
    }

    @Test(dataProvider = "testGetCoefficientDataProvider")
    public void testGetCoefficient(Polynomial polynomial, int n, int expected) {
        assertEquals(polynomial.getCoefficient(n), expected);
    }

    @DataProvider
    private Object[][] testSetCoefficientDataProvider() {
        return new Object[][]{
                {new Polynomial(), 3, 10, new Polynomial(new double[]{0, 0, 0, 10})},
                {new Polynomial(new double[]{5}), 0, 5, new Polynomial(new double[]{5})},
                {new Polynomial(new double[]{5}), 0, 10, new Polynomial(new double[]{10})},
                {new Polynomial(new double[]{0, 0, 0, 1}), 3, 0, new Polynomial()},
                {new Polynomial(new double[]{1, 0, 0, 1}), 3, 0, new Polynomial(new double[]{1})},
                {new Polynomial(new double[]{1, 0, 0, 1}), 0, 3, new Polynomial(new double[]{3, 0, 0, 1})},
                {new Polynomial(new double[]{1, 0, 0, 1}), 1, 3, new Polynomial(new double[]{1, 3, 0, 1})},
        };
    }

    @Test(dataProvider = "testSetCoefficientDataProvider", dependsOnMethods = "testGetDegree")
    public void testSetCoefficient(Polynomial polynomial, int n, double value, Polynomial expected) {
        polynomial.setCoefficient(n, value);
        assertPolynomialEquals(polynomial, expected);
    }

    @DataProvider
    private Object[][] testGetFirstDerivationDataProvider() {
        return new Object[][]{
                {new Polynomial(), new Polynomial()},
                {new Polynomial(new double[]{100}), new Polynomial()},
                {new Polynomial(new double[]{100, 10}), new Polynomial(new double[]{10})},
                {new Polynomial(new double[]{100, 10, 1}), new Polynomial(new double[]{10, 2})},
                {new Polynomial(new double[]{100, 10, 1, 0, 0, 5}), new Polynomial(new double[]{10, 2, 0, 0, 25})},
        };
    }

    @Test(dataProvider = "testGetFirstDerivationDataProvider", dependsOnMethods = "testGetDegree")
    public void testGetFirstDerivation(Polynomial polynomial, Polynomial expected) {
        assertPolynomialEquals(polynomial.getFirstDerivation(), expected);
    }

    @DataProvider
    private Object[][] testComputePolynomialDataProvider() {
        return new Object[][]{
                {new Polynomial(), Integer.MAX_VALUE, 0},
                {new Polynomial(new double[]{5}), 0, 5},
                {new Polynomial(new double[]{0, 1}), 2, 2},
                {new Polynomial(new double[]{0, 0, 1}), 2, 4},
                {new Polynomial(new double[]{0, 0, 0, 1}), 2, 8},
                {new Polynomial(new double[]{0, 0, 0, 0, 1}), 2, 16},
                {new Polynomial(new double[]{5, 1531, 3513, 1, 351, 3135, 1, 31}), 0, 5},
                {new Polynomial(new double[]{100, 10, 1, 0, 0, 5}), 31, 143147126}
        };
    }

    @Test(dataProvider = "testComputePolynomialDataProvider")
    public void testComputePolynomial(Polynomial polynomial, double x, double expected) {
        assertEquals(polynomial.computePolynomial(x), expected);
    }

    @DataProvider
    private Object[][] testIsExtremaDataProvider() {
        return new Object[][]{
                {new Polynomial(), 0, false},
                {new Polynomial(), 10, false},
                {new Polynomial(new double[]{5}), 0, false},
                {new Polynomial(new double[]{5}), 10, false},
                {new Polynomial(new double[]{5, 5}), 0, false},
                {new Polynomial(new double[]{5, 5}), 1, false},
                {new Polynomial(new double[]{0, 0, 1}), 0, true},
                {new Polynomial(new double[]{1, -3, 0, 1}), 1, true},
                {new Polynomial(new double[]{1, -3, 0, 1}), -1, true},
                {new Polynomial(new double[]{1, -3, 0, 1}), 2, false},
                {new Polynomial(new double[]{1, -3, 0, 1}), 0, false},
                {new Polynomial(new double[]{0, 0, -3, 2}), 0, true},
                {new Polynomial(new double[]{0, 0, -3, 2}), 1, true},
                {new Polynomial(new double[]{0, -80, 0, 0, 0, 1}), 2, true},
                {new Polynomial(new double[]{0, -80, 0, 0, 0, 1}), -2, true}
        };
    }

    @Test(dataProvider = "testIsExtremaDataProvider")
    public void testIsExtrema(Polynomial polynomial, double x, boolean expected) {
        assertEquals(polynomial.isExtrema(x), expected);
    }

    private void assertPolynomialEquals(Polynomial actual, Polynomial expected) {
        assertTrue(equals(actual, expected), String.format("Expected: %s\tReceived: %s",
                printCoefficients(expected), printCoefficients(actual)));
    }

    private boolean equals(Polynomial a, Polynomial b) {
        double epsilon = 1e-9;
        if (a.getDegree() != b.getDegree()) {
            return false;
        }

        for (int i = 0; i <= a.getDegree(); i++) {
            if (Math.abs(a.getCoefficient(i) - b.getCoefficient(i)) >= epsilon) {
                return false;
            }
        }
        return true;
    }

    private String printCoefficients(Polynomial polynomial) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < polynomial.getDegree(); i++) {
            result.append(polynomial.getCoefficient(i)).append(", ");
        }
        result.append(polynomial.getCoefficient(polynomial.getDegree())).append("]");
        return result.toString();
    }
}