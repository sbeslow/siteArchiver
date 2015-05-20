package com.orangewall;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Utilities {

    public static List<String> readInFile(String fileDirAndName) {

        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileDirAndName));
            List<String> textLines = new ArrayList<String>();

            while ((sCurrentLine = br.readLine()) != null) {
                textLines.add(sCurrentLine);
            }

            return textLines;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null)br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }

    public static void writeToFile(String directory, String timestamp, String fileId, String content) {
        try {


            String fileName = directory + fileId + "-" + timestamp + ".html";

            File file = new File(fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String fetchUrl(String url) throws Exception {

        Document doc = Jsoup.connect(url).get();
        return doc.toString();
    }
}
