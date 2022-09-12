package com.solvd.lawyers;

import com.solvd.lawyers.characteristic.ClientCase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileReader {

    private static final Logger LOGGER = LogManager.getLogger(ClientCase.class);
    private static Map<String, Integer> wordsList;

    public static Map<String, Integer> countWords(String textFile) {
        try {
            String content = FileUtils.readFileToString(new File(textFile), "UTF-8");
            String[] strings = content.toLowerCase().split("\\W");

            Set<String> unigueWords = new HashSet<>();
            Collections.addAll(unigueWords, strings);
            wordsList = new HashMap<>();

            for (String word : unigueWords) {
                int frequency = StringUtils.countMatches(content.toLowerCase(), word);
                wordsList.put(word, frequency);
            }

        } catch (IOException e) {
            LOGGER.error(e);
        }

        return wordsList;
    }

    public static void writeSortedWordsToFile(String fileName) {
        File file = new File(fileName);

        Comparator<Integer> comparator = (o1, o2) -> o2.compareTo(o1);

        List<String> sorted = wordsList.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .map((word) -> word.toString())
                .collect(Collectors.toList());

        try {
            FileUtils.writeLines(file, sorted);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}