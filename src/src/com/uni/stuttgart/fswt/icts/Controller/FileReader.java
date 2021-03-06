package com.uni.stuttgart.fswt.icts.Controller;

import com.uni.stuttgart.fswt.icts.Model.Commit;
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
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by GM on 10.12.2015.
 */
public class FileReader {

    private static final Pattern NEW_LINE_PATTERN = Pattern.compile("[0-9]+;[^\\n]+");

    // Regex to match CSV-Lines
    private static String CSV_REGEX = "[0-9]+(;[^;]*?){21}(?=(\\n[0-9]+;)|$)";

    private static DateFormat ISSUE_DATE_FORMAT_MINUTES = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static DateFormat ISSUE_DATE_FORMAT_DATE = new SimpleDateFormat("dd.MM.yyyy");

    private static DateFormat COMMIT_DATE_FORMAT = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy", Locale.ENGLISH);

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
                issue.setUpdateDate(tryParseDate(values[9], ISSUE_DATE_FORMAT_MINUTES));
                issue.setCategory(values[10]);
                issue.setMilestone(values[11]);
                issue.setStartDate(tryParseDate(values[12], ISSUE_DATE_FORMAT_DATE));
                issue.setEndDate(tryParseDate(values[13], ISSUE_DATE_FORMAT_DATE));
                issue.setEstimatedEffort(tryParseInt(values[14]));
                issue.setWorkedTime(tryParseDouble(values[15]));
                issue.setPercentageDone(tryParseDouble(values[16]));
                issue.setCreationDate(tryParseDate(values[17], ISSUE_DATE_FORMAT_MINUTES));
                issue.setCloseDate(tryParseDate(values[18], ISSUE_DATE_FORMAT_MINUTES));
                issue.setRelatedTasks(values[19].split(", "));
                issue.setIsPrivate(values[20].equalsIgnoreCase("true"));

                if (values.length > 21) {
                    descriptionLines.append(values[21]);
                }

            } catch (Exception ex) {
                exceptions.add(ex);
                continue;
            }

            // Zeile gelesen -> Zeilenindex hochzählen
            currentLineIndex += 1;


            String followingLine;

            while (currentLineIndex < lines.size())
            {
                followingLine = lines.get(currentLineIndex);

                if (!NEW_LINE_PATTERN.matcher(followingLine).find()) {

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
    public static Result<ArrayList<Commit>> readCommits(String commitLogFilePath) {
        ArrayList<Commit> commits = new ArrayList<>();
        ArrayList<Exception> exceptions = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(commitLogFilePath), "Cp1252"));
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            reader.close();

        } catch (Exception ex) {
            return new Result<>(new Exception("Fehler beim Einlesen der Datei: " + commitLogFilePath));
        }

        int currentLineIndex = 0;

        while (currentLineIndex < lines.size()) {
            Commit commit = new Commit();
            String[] values = lines.get(currentLineIndex).split("##");

            try {
                commit.setId((values[0]));
                commit.setAuthor(values[1]);
                commit.setCommitDate(tryParseDate(values[2], COMMIT_DATE_FORMAT));
                commit.setComment(values[3]);
            } catch (Exception ex) {
                exceptions.add(ex);
                continue;
            }

            // Zeile gelesen -> Zeilenindex hochzählen
            currentLineIndex += 1;

            commits.add(commit);
        }

        return new Result<>(commits, exceptions);
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
