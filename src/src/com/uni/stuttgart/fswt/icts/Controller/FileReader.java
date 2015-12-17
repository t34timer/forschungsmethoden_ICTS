package com.uni.stuttgart.fswt.icts.Controller;

import com.uni.stuttgart.fswt.icts.Model.Issue;
import com.uni.stuttgart.fswt.icts.Model.Result;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by GM on 10.12.2015.
 */
public class FileReader {

    private static String IS_NEW_DATARECORD_REGEX = "[0-9]+;[^\\n]+";
    private static String CSV_REGEX = "[0-9]+(;[^;]*?){21}(?=(\\n[0-9]+;)|$)";
    private static DateFormat DATE_FORMAT_MINUTES = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static DateFormat DATE_FORMAT_DATE = new SimpleDateFormat("dd.MM.yyyy");

    // Lädt die Issues
    public static Result<ArrayList<Issue>> readIssueCSV(String issueCsvFilePath) {

        ArrayList<Issue> issues = new ArrayList<>();
        ArrayList<Exception> exceptions = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(issueCsvFilePath), "Cp1252"));
            reader.readLine(); // skip first line
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            reader.close();

        } catch (Exception ex) {
            return new Result<>(new Exception("Fehler beim Einlesen der Datei: " + issueCsvFilePath));
        }

        int currentLineIndex = 0;

        while (currentLineIndex < lines.size()) {
            Issue issue = new Issue();
            String[] values = lines.get(currentLineIndex).split(";");
            StringBuilder descriptionLines = new StringBuilder();

            try {
                issue.setId(Integer.parseInt(values[0]));
                issue.setProject(values[1]);
                issue.setTracker(values[2]);
                issue.setSuperiorTask(values[3]);
                issue.setStatus(values[4]);
                issue.setPriority(values[5]);
                issue.setTopic(values[6]);
                issue.setAuthor(values[7]);
                issue.setAssignedTo(values[8]);
                issue.setUpdateDate(tryParseDate(values[9], DATE_FORMAT_MINUTES));
                issue.setCategory(values[10]);
                issue.setMilestone(values[11]);
                issue.setStartDate(tryParseDate(values[12], DATE_FORMAT_DATE));
                issue.setEndDate(tryParseDate(values[13], DATE_FORMAT_DATE));
                issue.setEstimatedEffort(tryParseInt(values[14]));
                issue.setWorkedTime(tryParseDouble(values[15]));
                issue.setPercentageDone(tryParseDouble(values[16]));
                issue.setCreationDate(tryParseDate(values[17], DATE_FORMAT_MINUTES));
                issue.setCloseDate(tryParseDate(values[18], DATE_FORMAT_MINUTES));
                issue.setRelatedTasks(values[19].split(", "));
                issue.setIsPrivate(values[20].equalsIgnoreCase("true"));

                if (values.length > 21) {
                    descriptionLines.append(values[21]);
                }

            } catch (Exception ex) {
                exceptions.add(ex);
                return null;
            }

            // Zeile gelesen -> Zeilenindex hochzählen
            currentLineIndex += 1;


            String followingLine;

            while (currentLineIndex < lines.size())
            {
                followingLine = lines.get(currentLineIndex);

                if (!Pattern.matches(IS_NEW_DATARECORD_REGEX, followingLine)) {

                    descriptionLines.append("\n");
                    descriptionLines.append(followingLine);
                    currentLineIndex += 1;

                } else {
                    break;
                }
            }

            issue.setDescription(descriptionLines.toString());
            issues.add(issue);
        }

        return new Result<>(issues, exceptions);
    }

    // Lädt die Commit-Messages
    public static Result<ArrayList<Object>> readCommits(String commitLogFilePath) {
        // todo
        return null;
    }

    private static Date tryParseDate(String input, DateFormat format) {
        if (input == null || input.length() < 10) {
            return null;
        }

        try {
            return format.parse(input);
        } catch (ParseException e) {
            return null;
        }
    }

    private static int tryParseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception ex) {
            return Integer.MIN_VALUE;
        }
    }

    private static double tryParseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (Exception ex) {
            return Double.MIN_VALUE;
        }
    }
}
