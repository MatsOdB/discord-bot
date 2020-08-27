package me.discordbot.command;

import java.util.List;

public interface ICommand {
    void handle(CommandContext ctx);

    String getHelp();

    String getName();

    default List<String> getAliases() {
        return List.of();
    }
}
