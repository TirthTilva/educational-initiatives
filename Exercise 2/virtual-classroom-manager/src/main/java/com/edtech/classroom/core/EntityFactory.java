package com.edtech.classroom.core;

import com.edtech.classroom.domain.*;
import java.time.LocalDateTime;

public class EntityFactory {
    public static Learner createLearner(String id, String name) {
        return new Learner(id, name);
    }
    public static VirtualRoom createRoom(String name) {
        return new VirtualRoom(name);
    }
    public static TaskAssignment createAssignment(String title, String desc, LocalDateTime dueDate) {
        return new TaskAssignment(title, desc, dueDate);
    }
}