package com.edtech.classroom.commands;

import com.edtech.classroom.core.ClassroomRegistry;
import java.util.List;

public class ListRoomsCommand implements UserCommand {
    public void execute(String[] args) {
        List<String> rooms = ClassroomRegistry.getInstance().listRoomNames();
        if (rooms.isEmpty()) System.out.println("No classrooms available.");
        else {
            System.out.println("Classrooms:");
            for (String r : rooms) System.out.println(" - " + r);
        }
    }
    public String name() { return "list_classrooms"; }
    public String usage() { return "list_classrooms"; }
}
