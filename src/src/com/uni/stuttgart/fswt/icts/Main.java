package com.uni.stuttgart.fswt.icts;

import com.uni.stuttgart.fswt.icts.Controller.FileReader;
import com.uni.stuttgart.fswt.icts.Model.Issue;
import com.uni.stuttgart.fswt.icts.Model.Result;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Result<ArrayList<Issue>> issues;
        Result<ArrayList<String>> words;

        try {
            // 1.) Einlesen, Tokens extrahieren & lemmatisieren
            issues = FileReader.readIssueCSV(args[0]);
            printExceptions("FileReader", issues.getExceptions());

            // 2.) Wort-Tabelle erstellen

        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Unbehandelter Fehler:\n\n" + e.getMessage());
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.out.println("\n\t" + stackElement.toString());
            }
        }
    }

    private static void printExceptions(String module, ArrayList<Exception> exceptionArrayList) {
        if (exceptionArrayList != null && exceptionArrayList.size() > 0)
        {

        }
    }
}
