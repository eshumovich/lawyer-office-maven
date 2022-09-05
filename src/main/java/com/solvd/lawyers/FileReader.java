package com.solvd.lawyers;

import com.solvd.lawyers.characteristic.ClientCase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileReader {

    private static final Logger LOGGER = LogManager.getLogger(ClientCase.class);

    public static void readFile() {

        try {
            String content = FileUtils.readFileToString(new File("src/main/resources/Textfile.txt"), "UTF-8");
            String[] strings = content.toLowerCase().split("\\W");
            Set<String> sdata = new HashSet<String>();

            for (String val : strings) {
                sdata.add(val);
            }

            Map<String, Integer> map = new HashMap<>();

            for (String string : sdata) {
                int i = StringUtils.countMatches(content.toLowerCase(), string);
                map.put(string, i);
            }

            List<Map.Entry<Integer, String>> list = new ArrayList(map.entrySet());
            Object[] a = map.entrySet().toArray();
            Arrays.sort(a, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return ((Map.Entry<String, Integer>) o2).getValue()
                            .compareTo(((Map.Entry<String, Integer>) o1).getValue());
                }
            });
            for (Object e : a) {
                LOGGER.info(((Map.Entry<String, Integer>) e).getKey() + " : "
                        + ((Map.Entry<String, Integer>) e).getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}