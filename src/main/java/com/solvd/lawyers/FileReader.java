package com.solvd.lawyers;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class FileReader {

    private static final Logger LOGGER = LogManager.getLogger(LawyerOffice.class);

    public static void readFile() {

        try {
            String content = FileUtils.readFileToString(new File("File.txt"), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
