package com.languageplay.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    
    private HashMap<String, String> dictionaryWords = new HashMap<>();


    public Dictionary() {

        try {
            InputStream inputStream = getClass().getResourceAsStream("/com/languageplay/dictionary/english.txt");
            if (inputStream == null) {
                throw new IOException("File not found at the specified path.");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;

            while ((line = reader.readLine()) != null) {
                int firstSpaceIndex = line.indexOf(' ');
                if (firstSpaceIndex > 0) {
                    String word = line.substring(0, firstSpaceIndex).trim();
                    String definition = line.substring(firstSpaceIndex + 1).trim();
                    dictionaryWords.put(word.toLowerCase(), definition);
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }

            reader.close();
            System.out.println("Total words loaded: " + dictionaryWords.size());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getDefinition(String word) {
        return this.dictionaryWords.get(word.toLowerCase());
    }




}
