package me.discordbot.command.commands;

import me.discordbot.command.CommandContext;
import me.discordbot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;


public class AvatarCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        final Message message = ctx.getMessage();
        Member member = null;
        User user = null;
        if (!message.getMentionedMembers().isEmpty()) {
            member = message.getMentionedMembers().get(0);
            user = message.getMentionedUsers().get(0);
        }
        final MessageChannel channel = ctx.getChannel();
        final EmbedBuilder eb = new EmbedBuilder();

        try {
            eb.setTitle("Avatar of: " + member.getAsMention());
            eb.setImage(user.getEffectiveAvatarUrl());
        } catch (NullPointerException e) {
            if (message.getMentionedMembers().isEmpty()) {
                eb.setTitle("Avator of: " + message.getAuthor());
                eb.setImage(message.getAuthor().getEffectiveAvatarUrl());
            } else {
                channel.sendMessage("Couldn't complete operation");
            }
        }

        channel.sendMessage(eb.build()).queue();
    }

    @Override
    public String getHelp() {
        return "Returns the avatar of the mentioned member";
    }

    @Override
    public String getName() {
        return "avatar";
    }
}
