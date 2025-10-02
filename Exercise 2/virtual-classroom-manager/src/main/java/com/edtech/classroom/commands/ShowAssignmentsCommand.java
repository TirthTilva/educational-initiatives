package com.edtech.classroom.commands;

import com.edtech.classroom.core.ClassroomRegistry;
import com.edtech.classroom.domain.*;

import java.util.Map;

public class ShowAssignmentsCommand implements UserCommand {
    public void execute(String[] args) {
        if (args.length < 2) { System.out.println("Usage: show_assignments <roomName>"); return; }
        String roomName = args[1];
        VirtualRoom room = ClassroomRegistry.getInstance().getRoom(roomName);
        if (room == null) { System.out.println("Room not found."); return; }
        Map<String, TaskAssignment> tasks = room.getAssignments();
        if (tasks.isEmpty()) { System.out.println("No assignments in [" + roomName + "]."); return; }
        System.out.println("Assignments in [" + roomName + "]:");
        for (TaskAssignment t : tasks.values()) {
            System.out.println(" - " + t.getTitle() + " | Due: " + t.getDueDateString());
            if (!t.getSubmissions().isEmpty())
                System.out.println("   Submissions: " + t.getSubmissions().keySet());
        }
    }
    public String name() { return "show_assignments"; }
    public String usage() { return "show_assignments <roomName>"; }
}
