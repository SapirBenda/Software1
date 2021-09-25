package il.ac.tau.cs.sw1.riddle.b;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

public class CTest {
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
                {"hello", "world", "bye"},
                {"hello world", "nice seeing you there", "see you later alligator"},
                {"Hello wOrld", "nice seEing You there", "see You Later alligatOR"},

        };
    }

    @Test(dataProvider = "testMainDataProvider")
    public void testMain(String a, String b, String c) {
        C.main(new String[]{a, b, c});
        String expected = String.format("%s%n" +
                "***%n" +
                "%s%n" +
                "***%n" +
                "%s", a, b, c);

        assertEquals(getOutput(), expected);
    }
}