package com.uni.stuttgart.fswt.icts.Model;

import java.util.Date;

/**
 * Created by GM on 10.12.2015.
 */
public class Issue {

    private int id;
    private String project;
    private String tracker;
    private String superiorTask;
    private String status;
    private String priority;
    private String topic;
    private String author;
    private String assignedTo;
    private Date updateDate;
    private String category;
    private String milestone;
    private Date startDate;
    private Date endDate;
    private double estimatedEffort;
    private double workedTime;
    private double percentageDone;
    private Date creationDate;
    private Date closeDate;
    private String[] relatedTasks;
    private boolean isPrivate;
    private String description;

    private TokenContainer _topicTokens = new TokenContainer();
    private TokenContainer _desctiptionTokens = new TokenContainer();


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }
    public void setIsPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }


    public TokenContainer getTopicTokens() { return _topicTokens; }
    public TokenContainer getDescriptionTokens() { return _desctiptionTokens; }


    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
        _topicTokens.Tokenize(topic);
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
        _desctiptionTokens.Tokenize(description);
    }


    public String getSuperiorTask() {
        return superiorTask;
    }
    public void setSuperiorTask(String superiorTask) {
        this.superiorTask = superiorTask;
    }

    public String[] getRelatedTasks() {
        return relatedTasks;
    }
    public void setRelatedTasks(String[] relatedTasks) {
        this.relatedTasks = relatedTasks;
    }


    public String getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getMilestone() {
        return milestone;
    }
    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProject() {
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }

    public String getTracker() {
        return tracker;
    }
    public void setTracker(String tracker) {
        this.tracker = tracker;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }


    public double getEstimatedEffort() {
        return estimatedEffort;
    }
    public void setEstimatedEffort(double estimatedEffort) {
        this.estimatedEffort = estimatedEffort;
    }

    public double getPercentageDone() {
        return percentageDone;
    }
    public void setPercentageDone(double percentageDone) {
        this.percentageDone = percentageDone;
    }

    public double getWorkedTime() {
        return workedTime;
    }
    public void setWorkedTime(double workedTime) {
        this.workedTime = workedTime;
    }
}
