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

        // TODO GM: fertig machen
        for (Issue issue : issues) {
            for (WordFrequency frequency : issue.getTopicTokens().getTokens().values()) {

            }

            for (WordFrequency frequency : issue.getDescriptionTokens().getTokens().values()) {

            }
        }

        for (Commit commit : commits) {

        }
    }
}
