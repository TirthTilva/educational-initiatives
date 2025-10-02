package com.edtech.classroom.commands;

import com.edtech.classroom.core.ClassroomRegistry;
import com.edtech.classroom.domain.*;

public class SubmitAssignmentCommand implements UserCommand {
    public void execute(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: submit_assignment <studentId> <roomName> <title> <submissionText>");
            return;
        }
        String studentId = args[1];
        String roomName = args[2];
        String title = args[3];
        String submission = args[4];

        Learner l = ClassroomRegistry.getInstance().getLearner(studentId);
        if (l == null) { System.out.println("Student not registered."); return; }
        VirtualRoom room = ClassroomRegistry.getInstance().getRoom(roomName);
        if (room == null) { System.out.println("Room not found."); return; }

        if (room.submitAssignment(studentId, title, submission))
            System.out.println("Assignment submitted by Student [" + studentId + "] in [" + roomName + "].");
        else
            System.out.println("Failed: Assignment not found or submission error.");
    }
    public String name() { return "submit_assignment"; }
    public String usage() { return "submit_assignment <studentId> <roomName> <title> <submissionText>"; }
}
