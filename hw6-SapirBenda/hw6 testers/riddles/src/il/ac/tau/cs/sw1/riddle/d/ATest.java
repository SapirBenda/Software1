package il.ac.tau.cs.sw1.riddle.d;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

public class ATest {
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

    @Test
    public void testMain() {
        A.main(new String[0]);
        assertEquals(getOutput(), "210");
    }
}