package com.albert.toolkit.process;

import java.io.*;

public class ExecProcess {

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] cmd = {"ping", "-z", "www.baidu.com"};
        Process process = Runtime.getRuntime().exec(cmd);
        String info = consumeInputStream(process.getInputStream());
        String error = consumeInputStream(process.getErrorStream());
        process.waitFor();
        System.out.println("info: " + info);
        System.out.println("error: " + error);
    }


    private static String consumeInputStream(InputStream inputStream) throws IOException {
        StringBuilder sbd = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                sbd.append(line);
                sbd.append(System.lineSeparator());
            }
        }

        return sbd.toString();
    }

}
