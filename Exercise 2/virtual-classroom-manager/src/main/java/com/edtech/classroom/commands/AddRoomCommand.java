package com.edtech.classroom.commands;

import com.edtech.classroom.core.ClassroomRegistry;
import com.edtech.classroom.core.Log;

public class AddRoomCommand implements UserCommand {
    public void execute(String[] args) {
        if (args.length < 2) { System.out.println("Usage: add_classroom <roomName>"); return; }
        String roomName = args[1];
        boolean ok = ClassroomRegistry.getInstance().addRoom(roomName);
        if (ok) {
            Log.info("Classroom '" + roomName + "' created.");
            System.out.println("Classroom [" + roomName + "] has been created.");
        } else System.out.println("Failed: Classroom may exist already.");
    }
    public String name() { return "add_classroom"; }
    public String usage() { return "add_classroom <roomName>"; }
}