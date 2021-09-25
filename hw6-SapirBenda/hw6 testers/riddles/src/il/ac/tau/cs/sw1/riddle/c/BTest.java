package il.ac.tau.cs.sw1.riddle.c;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

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

    @Test
    public void testMain() {
        B.main(new String[]{});
        String output = getOutput();
        String lastLine = output.substring(output.lastIndexOf("\n") + 1);
        assertEquals(lastLine, "success!");
    }
}