package il.ac.tau.cs.sw1.riddle.a;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BTest {
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeMethod
    public void setUp() {
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @AfterMethod
    public void tearDown() {
        byteArrayOutputStream.reset();
    }

    private String getOutput() {
        return byteArrayOutputStream.toString().trim();
    }

    @DataProvider
    private Object[][] testMainDataProvider() {
        return new Object[][]{
                {15},
                {0},
                {-10}
        };
    }

    @Test(dataProvider = "testMainDataProvider")
    public void testMain(int num) {
        B.main(new String[]{String.valueOf(num)});
        String expected = String.format("B%n" +
                "A1%n" +
                "%d%n" +
                "A2", num);

        assertEquals(getOutput(), expected);
    }
}