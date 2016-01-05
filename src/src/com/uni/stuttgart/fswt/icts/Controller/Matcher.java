package com.uni.stuttgart.fswt.icts.Controller;

import com.uni.stuttgart.fswt.icts.Model.Commit;
import com.uni.stuttgart.fswt.icts.Model.Issue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Matcher {

    private Matcher() { /* Singleton */ }

    public static void match(ArrayList<Issue> issues, ArrayList<Commit> commits) throws IOException {
        File file = new File("results.txt");
        file.createNewFile();
        PrintWriter out = new PrintWriter(file);

        for (Commit commit : commits) {
            Issue bestIssue = null;
            int bestScore = 0;

            for (Issue issue : issues) {
                int[] bestMatches = new int[5];

                for (String commitWord : commit.getCommentTokens().getTokens().keySet()) {
                    for (String issueWord : issue.getDescriptionTokens().getTokens().keySet()) {
                        if (commitWord.equals(issueWord)) {
                            int wordCount = TokenManager.getWordCount(issueWord);
                            int weight = Matcher.calculateWeight(wordCount);

                            for (int j = 0; j < bestMatches.length; j++) {
                                if (weight > bestMatches[j]) {
                                    System.arraycopy(bestMatches, j, bestMatches, j + 1, bestMatches.length - j - 1);
                                    bestMatches[j] = weight;
                                    break;
                                }
                            }
                        }
                    }
                }

                int matchScore = 0;

                for (int i : bestMatches) {
                    matchScore += i;
                }

                if (matchScore > bestScore) {
                    bestIssue = issue;
                    bestScore = matchScore;
                }
            }

            if (bestScore > 0) {
                System.out.println("Matching commit " + commit.getId() + " with issue " + bestIssue.getId()
                + " (score: " + bestScore + ")");
                out.println(commit.getId() + " " + bestIssue.getId());
            } else {
                System.out.println("No Match found for " + commit.getId());
            }
        }

        out.close();
    }

    private static int calculateWeight(int wordCount) {
        return (int) (1000 * Math.exp(-0.01 * (double) wordCount));
    }
}
