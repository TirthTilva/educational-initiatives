package com.edtech.classroom.commands;

import com.edtech.classroom.core.ClassroomRegistry;

public class RemoveRoomCommand implements UserCommand {
    public void execute(String[] args) {
        if (args.length < 2) { System.out.println("Usage: remove_classroom <roomName>"); return; }
        String roomName = args[1];
        boolean ok = ClassroomRegistry.getInstance().removeRoom(roomName);
        if (ok) System.out.println("Classroom [" + roomName + "] removed.");
        else System.out.println("Failed: Classroom not found.");
    }
    public String name() { return "remove_classroom"; }
    public String usage() { return "remove_classroom <roomName>"; }
}
