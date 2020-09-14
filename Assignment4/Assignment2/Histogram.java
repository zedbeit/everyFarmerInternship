package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Histogram {
    private static Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) {
        String filePath = "mbox-short.txt";
        Matcher mt = null;
        Pattern pt = null;

        try {
            Scanner textFile = new Scanner(new File(filePath));

            while (textFile.hasNextLine()) {
                String line = textFile.nextLine().toLowerCase().trim();

                if (line.contains("by") && !(line.contains("this"))) {
                    line = line.replaceAll("by\\s", "");
                    String[] str = line.split(" ");
                    pt = Pattern.compile("[a-z.]+");
                    mt = pt.matcher(str[0]);

                    if(mt.matches()){
                        addToMap(str[0]);
                    }
                }
            }
            textFile.close();
            printMap();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }
    }

    public static void addToMap(String word) {
        if (map.containsKey(word)) {
            int count = map.get(word) + 1;
            map.put(word, count);
        } else {
            map.put(word, 1);
        }
    }

    public static void printMap() {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }
}