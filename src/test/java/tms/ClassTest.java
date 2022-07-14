package tms;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClassTest {

    @Test
    public void testMethod() {
        Class clas = new Class();
        int i = clas.testMethod(3, 4);
        //assertTrue();
        assertEquals(7, i);
    }
}