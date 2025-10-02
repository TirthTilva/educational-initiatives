package com.edtech.classroom.commands;

public interface UserCommand {
    void execute(String[] args);
    String name();
    String usage();
}