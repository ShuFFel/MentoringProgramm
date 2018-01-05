package com.instinctools.egor.mentoring.JPA.testing;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestUtil {
    public static ByteArrayOutputStream prepare(String input){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(((input).getBytes()));
        PrintStream out = new PrintStream(outputStream);
        System.setOut(out);
        System.setIn(in);

        return outputStream;
    }

}
