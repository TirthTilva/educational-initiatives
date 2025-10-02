package com.edtech.classroom.commands;

import java.util.Collection;

public class HelpCommand implements UserCommand {
    private final Collection<UserCommand> commands;
    public HelpCommand(Collection<UserCommand> commands) { this.commands = commands; }

    public void execute(String[] args) {
        System.out.println("Available commands:");
        for (UserCommand c : commands) {
            System.out.println(" - " + c.name() + " : " + c.usage());
        }
        System.out.println("Type 'exit' to quit.");
    }
    public String name() { return "help"; }
    public String usage() { return "help"; }
}
