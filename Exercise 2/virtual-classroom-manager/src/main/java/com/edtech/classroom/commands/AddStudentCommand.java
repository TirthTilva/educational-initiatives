package com.edtech.classroom.commands;

import com.edtech.classroom.core.ClassroomRegistry;
import com.edtech.classroom.domain.Learner;
import com.edtech.classroom.domain.VirtualRoom;

public class AddStudentCommand implements UserCommand {
    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: add_student <studentId> <studentName> <roomName>");
            return;
        }

        String studentId = args[1];
        String studentName = args[2];
        String roomName = args[3];

        ClassroomRegistry registry = ClassroomRegistry.getInstance();

        // Register learner if not exists
        if (registry.getLearner(studentId) == null) {
            boolean registered = registry.registerLearner(studentId, studentName);
            if (!registered) {
                System.out.println("Failed to register student. Try different ID.");
                return;
            }
        }

        Learner learner = registry.getLearner(studentId);
        VirtualRoom room = registry.getRoom(roomName);

        if (room == null) {
            System.out.println("Classroom [" + roomName + "] not found. Please create it first.");
            return;
        }

        if (room.addLearner(learner)) {
            System.out.println("Student [" + studentId + "] has been enrolled in [" + roomName + "].");
        } else {
            System.out.println("Failed: Student may already be enrolled.");
        }
    }

    @Override
    public String name() {
        return "add_student";
    }

    @Override
    public String usage() {
        return "add_student <studentId> <studentName> <roomName>";
    }
}
