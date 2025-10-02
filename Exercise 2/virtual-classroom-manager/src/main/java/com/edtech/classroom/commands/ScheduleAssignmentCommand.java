package com.edtech.classroom.commands;

import com.edtech.classroom.core.ClassroomRegistry;
import com.edtech.classroom.core.EntityFactory;
import com.edtech.classroom.domain.*;

import java.time.LocalDateTime;

public class ScheduleAssignmentCommand implements UserCommand {
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: schedule_assignment <roomName> <title> [due:yyyy-MM-ddTHH:mm] [description]");
            return;
        }
        String roomName = args[1];
        String title = args[2];
        String dueRaw = (args.length >= 4) ? args[3] : null;
        String desc = (args.length >= 5) ? args[4] : "";

        VirtualRoom room = ClassroomRegistry.getInstance().getRoom(roomName);
        if (room == null) { System.out.println("Room not found."); return; }

        LocalDateTime due = null;
        if (dueRaw != null && dueRaw.startsWith("due:")) {
            try { due = LocalDateTime.parse(dueRaw.substring(4)); }
            catch (Exception ex) { System.out.println("Invalid due format. Use yyyy-MM-ddTHH:mm"); return; }
        }

        TaskAssignment task = EntityFactory.createAssignment(title, desc, due);
        if (room.addAssignment(task))
            System.out.println("Assignment for [" + roomName + "] has been scheduled.");
        else
            System.out.println("Failed: Assignment already exists.");
    }
    public String name() { return "schedule_assignment"; }
    public String usage() { return "schedule_assignment <roomName> <title> [due:yyyy-MM-ddTHH:mm] [description]"; }
}
