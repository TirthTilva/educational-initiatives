package com.edtech.classroom.core;

import com.edtech.classroom.domain.*;
import java.util.*;

public class ClassroomRegistry {
    private final Map<String, VirtualRoom> rooms = new HashMap<>();
    private final Map<String, Learner> learners = new HashMap<>();
    private static volatile ClassroomRegistry instance;

    private ClassroomRegistry() {}
    public static ClassroomRegistry getInstance() {
        if (instance == null) {
            synchronized (ClassroomRegistry.class) {
                if (instance == null) instance = new ClassroomRegistry();
            }
        }
        return instance;
    }

    public boolean addRoom(String name) {
        if (name == null || name.trim().isEmpty()) return false;
        if (rooms.containsKey(name.toLowerCase())) return false;
        rooms.put(name.toLowerCase(), EntityFactory.createRoom(name));
        return true;
    }

    public boolean removeRoom(String name) {
        return rooms.remove(name.toLowerCase()) != null;
    }

    public VirtualRoom getRoom(String name) {
        return rooms.get(name.toLowerCase());
    }

    public List<String> listRoomNames() {
        return new ArrayList<>(rooms.keySet());
    }

    public boolean registerLearner(String id, String name) {
        if (learners.containsKey(id.toLowerCase())) return false;
        learners.put(id.toLowerCase(), EntityFactory.createLearner(id, name));
        return true;
    }

    public Learner getLearner(String id) { return learners.get(id.toLowerCase()); }
}