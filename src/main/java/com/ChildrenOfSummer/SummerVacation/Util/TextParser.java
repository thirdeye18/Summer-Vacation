package com.ChildrenOfSummer.SummerVacation.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
 * Takes userInput String and strips whitespace, anything not a letter, "the", and "to"
 * then returns an ArrayList of the remaining words
 * Returns empty list if there is nothing left after scrubbing text, or if the userInput is blank
 */

public class TextParser {

    public static List<String> parseText(String userInput) {
        String[] strArr;
        String stripRegex = "[^A-Za-z]";
        ArrayList<String> removeWords = new ArrayList<>();
        removeWords.add("the");
        removeWords.add("to");
        removeWords.add("");

        strArr = userInput.toLowerCase().trim().split(stripRegex);
        List<String> parsedArr =
                new ArrayList<>(Arrays.asList(strArr));

        parsedArr.removeAll(removeWords);

        return parsedArr;
    }

    /*
     * Returns verb if present in the ArrayList returned after parsing text
     * returns null if there are no recognized verbs from the verbs.json
     */

    public static String getVerb(String userInput) {
        List<String> parsedArr = parseText(userInput.toLowerCase());
        Map<String, ArrayList<String>> verbMap = JsonHandler.jsonToMapStringList("verbs.json", "config");

        for (String word : parsedArr) {
            assert verbMap != null;
            if (verbMap.containsKey(word)) {  // if user input matches specific keyword no need to iterate Map synonyms
                return word;
            }
            // checking synonyms, then return keyword if there is a match
            for (Map.Entry<String, ArrayList<String>> entry : verbMap.entrySet()) {
                if (entry.getValue().contains(word)) {
                    return entry.getKey();
                }
            }
        }
        return "";
    }

    /*
     * accepts userInput string, parses to leave verbs and nouns,
     * then move through parsed array removing the nouns
     * will return an array with verb keywords removed
     * if no nouns were found returns ArrayList containing "empty"
     */

    public static ArrayList<String> getNouns(String userInput) {
        List<String> parsedArr = parseText(userInput.toLowerCase());
        Map<String, ArrayList<String>> nounMap = JsonHandler.jsonToMapStringList("nouns.json", "config");

        ArrayList<String> nouns = new ArrayList<>();

        for (String s : parsedArr) {
            assert nounMap != null;
            if (nounMap.containsKey(s)) {
                nouns.add(s);
            }
            for (Map.Entry<String, ArrayList<String>> entry : nounMap.entrySet()) {
                if (entry.getValue().contains(s)) {
                    nouns.add(entry.getKey());
                }
            }
        }
        if(nouns.isEmpty()) nouns.add("empty");

        return nouns;
    }

}
