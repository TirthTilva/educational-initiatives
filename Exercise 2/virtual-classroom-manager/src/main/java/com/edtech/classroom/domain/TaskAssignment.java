package com.edtech.classroom.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskAssignment {
    private final String title;
    private final String description;
    private final LocalDateTime dueDate;
    private final Map<String, String> submissions = new HashMap<>();

    public TaskAssignment(String title, String description, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDueDateString() {
        if (dueDate == null) return "No due date";
        return dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public boolean addSubmission(String learnerId, String submission) {
        submissions.put(learnerId, submission);
        return true;
    }

    public Map<String, String> getSubmissions() { return submissions; }
}