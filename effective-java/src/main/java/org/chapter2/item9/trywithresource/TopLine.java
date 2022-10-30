package org.chapter2.item9.trywithresource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * 9.try-with-resources优先于try-finally
 *
 * @author mawenhao 2022/10/29
 */
public class TopLine {
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int i = 1/0;
            return br.readLine();
        }catch (Exception e){
            System.out.println(Arrays.toString(e.getSuppressed()));
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        String path = "D:\\github\\desktop\\cs-practice\\effective-java\\src\\main\\java\\org\\chapter2\\item9" +
                "\\trywithresource\\TopLine.java";
        String firstLine = firstLineOfFile(path);
        System.out.println(firstLine);
    }
}
