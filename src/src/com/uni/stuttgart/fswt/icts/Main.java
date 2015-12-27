package com.uni.stuttgart.fswt.icts;

import com.uni.stuttgart.fswt.icts.Controller.FileReader;
import com.uni.stuttgart.fswt.icts.Controller.Lemmatizer;
import com.uni.stuttgart.fswt.icts.Controller.TokenManager;
import com.uni.stuttgart.fswt.icts.Model.Commit;
import com.uni.stuttgart.fswt.icts.Model.Issue;
import com.uni.stuttgart.fswt.icts.Model.Result;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Result<ArrayList<Issue>> issues;
        Result<ArrayList<Commit>> commits;

        try {
            Lemmatizer.init();
        } catch (IOException ex) {
            printException("Lemmatizer@Init", ex);
        }

        try {
            // 1.) Einlesen, Tokens extrahieren & lemmatisieren
            issues = FileReader.readIssueCSV(args[0]);
            printExceptions("FileReader@Issues", issues.getExceptions());

            commits = FileReader.readCommits(args[1]);
            printExceptions("FileReader@Commits", commits.getExceptions());

            // 2.) Wort-Tabelle erstellen
            TokenManager.initialize(issues.getResult(), commits.getResult());

            // 3.) Commits und Issues matchen

        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Unbehandelter Fehler:\n\n" + e.getMessage());
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.out.println("\t" + stackElement.toString());
            }
        }
    }

    private static void printExceptions(String module, ArrayList<Exception> exceptionArrayList) {
        if (exceptionArrayList != null && exceptionArrayList.size() > 0)
        {
            System.out.println("Fehler im Modul \"" + module + "\"");

            for (Exception ex : exceptionArrayList) {
                printException(ex, "");
            }
        }
    }

    private static void printException(String module, Exception ex) {
        System.out.println("Fehler im Modul \"" + module + "\"");
        printException(ex, "");
    }

    private static void printException(Throwable ex, String indent) {
        System.out.println(indent + ex.getMessage());
        for(StackTraceElement stackElement : ex.getStackTrace()) {
            System.out.println(indent + "\t" + stackElement.toString());
        }

        if (ex.getCause() != null) {
            printException(ex.getCause(), indent + "   ");
        }
    }
}
