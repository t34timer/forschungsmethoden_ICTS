package com.uni.stuttgart.fswt.icts.Model;

import java.util.Date;

/**
 * Created by GM on 10.12.2015.
 */
public class Issue {

    private int _id;
    private String _project;
    private String _tracker;
    private String _superiorTask;
    private String _status;
    private String _priority;
    private String _topic;
    private String _author;
    private String _assignedTo;
    private Date _updateDate;
    private String _category;
    private String _milestone;
    private Date _startDate;
    private Date _endDate;
    private double _estimatedEffort;
    private double _workedTime;
    private double _percentageDone;
    private Date _creationDate;
    private Date _closeDate;
    private String[] _relatedTasks;
    private boolean _isPrivate;
    private String _description;

    private TokenContainer _topicTokens = new TokenContainer();
    private TokenContainer _desctiptionTokens = new TokenContainer();


    public int getId() {
        return _id;
    }
    public void setId(int id) { _id = id; }

    public boolean getIsPrivate() {
        return _isPrivate;
    }
    public void setIsPrivate(boolean isPrivate) {
        _isPrivate = isPrivate;
    }


    public TokenContainer getTopicTokens() { return _topicTokens; }
    public String getTopic() {
        return _topic;
    }
    public void setTopic(String topic) {
        _topic = topic;
        _topicTokens.tokenize(topic);
    }


    public TokenContainer getDescriptionTokens() { return _desctiptionTokens; }
    public String getDescription() {
        return _description;
    }
    public void setDescription(String description) {
        _description = description;
        _desctiptionTokens.tokenize(description);
    }


    public String getSuperiorTask() {
        return _superiorTask;
    }
    public void setSuperiorTask(String superiorTask) { _superiorTask = superiorTask; }

    public String[] getRelatedTasks() {
        return _relatedTasks;
    }
    public void setRelatedTasks(String[] relatedTasks) { _relatedTasks = relatedTasks; }


    public String getAssignedTo() {
        return _assignedTo;
    }
    public void setAssignedTo(String assignedTo) { _assignedTo = assignedTo; }

    public String getAuthor() {
        return _author;
    }
    public void setAuthor(String author) { _author = author; }

    public String getCategory() {
        return _category;
    }
    public void setCategory(String category) { _category = category; }

    public String getMilestone() {
        return _milestone;
    }
    public void setMilestone(String milestone) { _milestone = milestone; }

    public String getPriority() {
        return _priority;
    }
    public void setPriority(String priority) {
        _priority = priority;
    }

    public String getProject() {
        return _project;
    }
    public void setProject(String project) {
        _project = project;
    }

    public String getTracker() {
        return _tracker;
    }
    public void setTracker(String tracker) {
        _tracker = tracker;
    }

    public String getStatus() {
        return _status;
    }
    public void setStatus(String status) {
        _status = status;
    }


    public Date getCreationDate() {
        return _creationDate;
    }
    public void setCreationDate(Date creationDate) {
        _creationDate = creationDate;
    }

    public Date getStartDate() {
        return _startDate;
    }
    public void setStartDate(Date startDate) {
        _startDate = startDate;
    }

    public Date getUpdateDate() {
        return _updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        _updateDate = updateDate;
    }

    public Date getEndDate() {
        return _endDate;
    }
    public void setEndDate(Date endDate) {
        _endDate = endDate;
    }

    public Date getCloseDate() {
        return _closeDate;
    }
    public void setCloseDate(Date closeDate) {
        _closeDate = closeDate;
    }


    public double getEstimatedEffort() {
        return _estimatedEffort;
    }
    public void setEstimatedEffort(double estimatedEffort) {
        _estimatedEffort = estimatedEffort;
    }

    public double getPercentageDone() {
        return _percentageDone;
    }
    public void setPercentageDone(double percentageDone) {
        _percentageDone = percentageDone;
    }

    public double getWorkedTime() {
        return _workedTime;
    }
    public void setWorkedTime(double workedTime) {
        _workedTime = workedTime;
    }
}
