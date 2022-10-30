package org.chapter2.item9.tryfinally;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 9.try-with-resources优先于try-finally
 *
 * @author mawenhao 2022/10/29
 */
public class TopLine {
    static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String path = "D:\\github\\desktop\\cs-practice\\effective-java\\src\\main\\java\\org\\chapter2\\item9" +
                "\\tryfinally\\TopLine.java";
        String firstLine = firstLineOfFile(path);
        System.out.println(firstLine);
    }
}
