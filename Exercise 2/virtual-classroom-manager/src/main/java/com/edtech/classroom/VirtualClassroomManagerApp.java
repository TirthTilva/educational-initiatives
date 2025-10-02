package com.edtech.classroom;

import com.edtech.classroom.commands.*;
import com.edtech.classroom.core.Log;
import java.util.*;

public class VirtualClassroomManagerApp {
    private final Map<String, UserCommand> commands = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public VirtualClassroomManagerApp() {
        registerCommands();
    }

    private void registerCommands() {
        addCommand(new AddRoomCommand());
        addCommand(new RemoveRoomCommand());
        addCommand(new AddStudentCommand());
        addCommand(new ListRoomsCommand());
        addCommand(new ListStudentsCommand());
        addCommand(new ScheduleAssignmentCommand());
        addCommand(new SubmitAssignmentCommand());
        addCommand(new ShowAssignmentsCommand());
        addCommand(new HelpCommand(commands.values()));
    }

    private void addCommand(UserCommand c) { commands.put(c.name(), c); }

    private void handleInput(String line) {
        if (line == null || line.trim().isEmpty()) return;
        String[] tokens = splitPreserveQuoted(line.trim());
        String cmd = tokens[0].toLowerCase();
        if (cmd.equals("exit") || cmd.equals("quit")) {
            System.out.println("Exiting... bye 👋");
            System.exit(0);
        }
        UserCommand command = commands.get(cmd);
        if (command == null) {
            System.out.println("Unknown command. Type 'help' to list commands.");
            return;
        }
        try {
            command.execute(tokens);
        } catch (Exception ex) {
            Log.err("Error executing command '" + cmd + "': " + ex.getMessage());
            System.out.println("Operation failed: " + ex.getMessage());
        }
    }

        // Splits by space but preserves quoted substrings as one token
    private String[] splitPreserveQuoted(String input) {
        List<String> parts = new ArrayList<>();
        boolean inQuote = false;
        StringBuilder sb = new StringBuilder();
        char quoteChar = '"';
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ((c == '"' || c == '\'') && !inQuote) {
                inQuote = true;
                quoteChar = c;
                continue;
            }
            if (inQuote && c == quoteChar) {
                inQuote = false;
                parts.add(sb.toString());
                sb.setLength(0);
                continue;
            }
            if (!inQuote && Character.isWhitespace(c)) {
                if (sb.length() > 0) { parts.add(sb.toString()); sb.setLength(0); }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) parts.add(sb.toString());
        return parts.toArray(new String[0]);
    }

    public void run() {
        System.out.println("============================================");
        System.out.println(" Virtual Classroom Manager (Console)");
        System.out.println(" Type 'help' to see available commands. Type 'exit' to quit.");
        System.out.println("============================================");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            handleInput(line);
        }
    }

    public static void main(String[] args) {
        new VirtualClassroomManagerApp().run();
    }
}