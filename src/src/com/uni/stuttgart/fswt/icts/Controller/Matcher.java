package com.uni.stuttgart.fswt.icts.Controller;

import com.uni.stuttgart.fswt.icts.Model.Commit;
import com.uni.stuttgart.fswt.icts.Model.Issue;

import java.util.ArrayList;

/**
 * Created by Benedikt on 03.01.2016.
 */
public class Matcher {

    private Matcher() { /* Singleton */ }

    public static void match(ArrayList<Issue> issues, ArrayList<Commit> commits) {
        for (Commit commit : commits) {
            for (Issue issue : issues) {
                for (String commitWord : commit.getCommentTokens().getTokens().keySet()) {
                    for (String issueWord : issue.getDescriptionTokens().getTokens().keySet()) {
                        if (commitWord.equals(issueWord)) {
                            int wordCount = TokenManager.getWordCount(issueWord);
                            System.out.println(issueWord + " " + wordCount + " " + Matcher.calculateWeight(wordCount));
                        }
                    }
                }
            }
        }
    }

    private static int calculateWeight(int wordCount) {
        return (int) (1000 * Math.exp(-0.01 * (double) wordCount));
    }
}
