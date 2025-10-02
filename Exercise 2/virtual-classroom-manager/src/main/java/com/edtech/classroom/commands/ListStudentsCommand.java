package com.edtech.classroom.commands;

import com.edtech.classroom.core.ClassroomRegistry;
import com.edtech.classroom.domain.*;

import java.util.Collection;

public class ListStudentsCommand implements UserCommand {
    public void execute(String[] args) {
        if (args.length < 2) { System.out.println("Usage: list_students <roomName>"); return; }
        String roomName = args[1];
        VirtualRoom room = ClassroomRegistry.getInstance().getRoom(roomName);
        if (room == null) { System.out.println("Room not found."); return; }
        Collection<Learner> students = room.listLearners();
        if (students.isEmpty()) System.out.println("No students in [" + roomName + "].");
        else {
            System.out.println("Students in [" + roomName + "]:");
            for (Learner s : students) System.out.println(" - " + s.getId() + " (" + s.getName() + ")");
        }
    }
    public String name() { return "list_students"; }
    public String usage() { return "list_students <roomName>"; }
}
