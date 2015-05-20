package com.orangewall;

import org.joda.time.DateTime;

import java.io.File;
import java.util.List;

public class RunScrape {

    public static void main(String[] args) {

        // arg 0 -> A file path and name where there is a line separated list of web pages to scape and a file identifier
        // arg 1 -> Path to the output directory
        String filePathAndName;
        String outputDirectory;
        try {
            filePathAndName = args[0];
            outputDirectory = args[1];
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }

        List<String> sitesToScrapeInfo = Utilities.readInFile(filePathAndName);
        if ((sitesToScrapeInfo == null) || (sitesToScrapeInfo.size() == 0)) {
            System.out.println("Unable to parse out info from input file");
            return;
        }

        File file = new File(outputDirectory);
        if (!file.exists()) {
            if (!file.mkdir()) {
                System.out.println("Failed to create the directory");
                return;
            }
        }

        DateTime now = new DateTime();
        String timestamp = now.toString();

        for (String siteToScrapeInfo : sitesToScrapeInfo) {
            String [] siteInfoSplit = siteToScrapeInfo.split("%%");
            try {
                String url = siteInfoSplit[0];
                String fileId = siteInfoSplit[1];
                String siteContent = Utilities.fetchUrl(url);

                Utilities.writeToFile(outputDirectory, timestamp, fileId, siteContent);
            }
            catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }



    }
}
