package com.edtech.classroom.domain;

import java.util.*;

public class VirtualRoom {
    private final String name;
    private final Map<String, Learner> learners = new HashMap<>();
    private final Map<String, TaskAssignment> assignments = new HashMap<>();

    public VirtualRoom(String name) { this.name = name; }
    public String getName() { return name; }

    public boolean addLearner(Learner s) {
        if (learners.containsKey(s.getId())) return false;
        learners.put(s.getId(), s);
        s.enrollRoom(name);
        return true;
    }

    public Collection<Learner> listLearners() { return learners.values(); }
    public boolean addAssignment(TaskAssignment task) {
        if (assignments.containsKey(task.getTitle())) return false;
        assignments.put(task.getTitle(), task);
        return true;
    }

    public boolean submitAssignment(String learnerId, String title, String submission) {
        TaskAssignment t = assignments.get(title);
        if (t == null) return false;
        return t.addSubmission(learnerId, submission);
    }

    public Map<String, TaskAssignment> getAssignments() { return assignments; }
}