package org.chapter2.item9.trywithresource;

import java.io.*;

/**
 * 9.try-with-resources优先于try-finally
 *
 * @author mawenhao 2022/10/29
 */
public class Copy {
    private static final int BUFFER_SIZE = 8 * 1024;

    static void copy(String src, String dst) throws IOException {
        try (
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dst))
        {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String dirPath =
                "D:\\github\\desktop\\cs-practice\\effective-java\\src\\main" + "\\java"
                        + "\\org\\chapter2\\item9\\";
        Copy.copy(dirPath + "src.txt", dirPath + "dest.txt");
    }
}
