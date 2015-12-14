package com.uni.stuttgart.fswt.icts.Controller;

import com.uni.stuttgart.fswt.icts.Model.Commit;
import com.uni.stuttgart.fswt.icts.Model.Issue;
import com.uni.stuttgart.fswt.icts.Model.WordFrequency;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GM on 10.12.2015.
 */
public class TokenManager {

    private static HashMap<String, WordFrequency> _issueTopicWordMap = new HashMap<>();
    private static HashMap<String, WordFrequency> _issueDesctiptionWordMap = new HashMap<>();
    private static HashMap<String, WordFrequency> _commitMessageWordMap = new HashMap<>();

    private static HashMap<String, WordFrequency> _issueWordMap = new HashMap<>();
    // aktuell Äquivalent zu _commitMessageWordMap
    // private static HashMap<String, WordFrequency> _commitWordMap = new HashMap<>();


    private TokenManager() { /* Singleton */ }


    public static void initialize(ArrayList<Issue> issues, ArrayList<Commit> commits) {

        for (Issue issue : issues) {
            for (WordFrequency frequency : issue.getTopicTokens().getTokens().values()) {

                String word = frequency.getWord();
                _issueTopicWordMap.put(word, calcNewWordFrequency(frequency, _issueTopicWordMap));
                _issueWordMap.put(word, calcNewWordFrequency(frequency, _issueWordMap));
            }

            for (WordFrequency frequency : issue.getDescriptionTokens().getTokens().values()) {

                String word = frequency.getWord();
                _issueDesctiptionWordMap.put(word, calcNewWordFrequency(frequency, _issueDesctiptionWordMap));
                _issueWordMap.put(word, calcNewWordFrequency(frequency, _issueWordMap));
            }
        }

        for (Commit commit : commits) {
            //for (WordFrequency frequency : commit) {

            //}
        }
    }

    private static WordFrequency calcNewWordFrequency(WordFrequency newWord, HashMap<String,WordFrequency> storage) {

        String word = newWord.getWord();

        if (storage.containsKey(word)) {
            WordFrequency oldFrequency = storage.get(word);
            oldFrequency.incrementWordCount(newWord.getWordCount());
            return oldFrequency;
        } else {
            return newWord;
        }
    }
}
