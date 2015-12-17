package com.uni.stuttgart.fswt.icts.Controller;

import com.uni.stuttgart.fswt.icts.Model.Issue;
import com.uni.stuttgart.fswt.icts.Model.Result;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GM on 10.12.2015.
 */
public class FileReader {

    private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    // Lädt die Issues
    public static Result<ArrayList<Issue>> readIssueCSV(String issueCsvFilePath) {

        ArrayList<Issue> issues = new ArrayList<>();
        ArrayList<Exception> exceptions = new ArrayList<>();
        List<String> lines;

        try {
            lines = Files.readAllLines(FileSystems.getDefault().getPath(issueCsvFilePath));
        } catch (Exception ex) {
            return new Result<>(new Exception("Fehler beim Einlesen der Datei: " + issueCsvFilePath));
        }

        for (String line : lines) {
            // Zeilenweise durchgehen
            Issue issue = new Issue();
            String[] values = line.split(";");

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
                issue.setUpdateDate(DATE_FORMAT.parse(values[9]));
                issue.setCategory(values[10]);
                issue.setMilestone(values[11]);
                issue.setStartDate(DATE_FORMAT.parse(values[12]));
                issue.setEndDate(DATE_FORMAT.parse(values[13]));
                issue.setEstimatedEffort(Integer.parseInt(values[14]));
                issue.setWorkedTime(Double.parseDouble(values[15]));
                issue.setPercentageDone(Double.parseDouble(values[16]));
                issue.setCreationDate(DATE_FORMAT.parse(values[17]));
                issue.setCloseDate(DATE_FORMAT.parse(values[18]));
                issue.setRelatedTasks(values[19].split(", "));
                issue.setIsPrivate(values[20].equalsIgnoreCase("true"));
                issue.setDescription(values[21]);
            } catch (Exception ex) {
                exceptions.add(ex);
            }
        }

        return new Result<>(issues, exceptions);
    }

    // Lädt die Commit-Messages
    public static Result<ArrayList<Object>> readCommits(String commitLogFilePath) {
        // todo
        return null;
    }
}
