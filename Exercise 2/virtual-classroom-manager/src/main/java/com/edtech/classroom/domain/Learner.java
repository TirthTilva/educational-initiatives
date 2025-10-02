package com.edtech.classroom.domain;

import java.util.*;

public class Learner {
    private final String id;
    private final String name;
    private final Set<String> enrolledRooms = new HashSet<>();

    public Learner(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public void enrollRoom(String roomName) { enrolledRooms.add(roomName); }
}