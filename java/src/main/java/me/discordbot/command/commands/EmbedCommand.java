package me.discordbot.command.commands;

import me.discordbot.Listener;
import me.discordbot.command.CommandContext;
import me.discordbot.command.ICommand;
import me.discordbot.customlibraries.UsefulExtras;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.util.List;

public class EmbedCommand implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @Override
    public void handle(CommandContext ctx) {
        EmbedBuilder eb = new EmbedBuilder();
        UsefulExtras usefulExtras = new UsefulExtras();
        MessageChannel receivedChannel = ctx.getChannel();
        Message message = ctx.getMessage();
        List<String> args = ctx.getArgs();
        logger.info("Embed command arguments: " + args.toString());

        //define necessary variables
        String title = null;
        String url = null;
        String footer = null;
        String footerIcon = null;
        MessageChannel channel = null;

        for (int i = 0; i < args.size() - 1; i++) {
            switch (args.get(i)) {
                case "-color" -> eb.setColor(Color.decode(args.get(i + 1)));
                case "-title" -> {
                    String str = usefulExtras.findListStrings(args.subList(i, args.size())).get(0);
                    title = str.substring(2, str.length() - 1);
                    eb.setTitle(title);
                }
                case "-url" -> url = args.get(i + 1);
                case "-desc" -> {
                    String str = usefulExtras.findListStrings(args.subList(i, args.size())).get(0);
                    String desc = str.substring(2, str.length() - 1);
                    eb.setDescription(desc);
                }
                case "-thumbnail" -> eb.setThumbnail(args.get(i + 1));
                case "-image" -> eb.setImage(args.get(i + 1));
                case "-author" -> eb.setAuthor(message.getMentionedMembers().get(0).getUser().getAsTag(),
                        message.getMentionedMembers().get(0).getUser().getEffectiveAvatarUrl(),
                        message.getMentionedMembers().get(0).getUser().getEffectiveAvatarUrl());
                case "-footer" -> {
                    String str = usefulExtras.findListStrings(args.subList(i, args.size())).get(0);
                    footer = str.substring(2, str.length() - 1);
                    eb.setFooter(footer);
                }
                case "-footer-icon" -> footerIcon = args.get(i + 1);
                case "-field" -> {
                    String str = usefulExtras.findListStrings(args.subList(i, args.size())).get(0);
                    String fieldName = str.substring(2, str.length() - 1);
                    str = usefulExtras.findListStrings(args.subList(i, args.size())).get(1);
                    String fieldValue = str.substring(2, str.length() - 1);
                    eb.addField(fieldName, fieldValue, false);
                }
                case "-inline-field" -> {
                    String str = usefulExtras.findListStrings(args.subList(i, args.size())).get(0);
                    String fieldName = str.substring(2, str.length() - 1);
                    str = usefulExtras.findListStrings(args.subList(i, args.size())).get(1);
                    String fieldValue = str.substring(2, str.length() - 1);
                    eb.addField(fieldName, fieldValue, true);
                }
                case "-channel" -> channel = message.getMentionedChannels().get(0);
            }
        }

        if (title != null && url != null) {
            eb.setTitle(title, url);
        }
        if (footer != null && footerIcon != null) {
            eb.setFooter(footer, footerIcon);
        }
        if (channel != null) {
            channel.sendMessage(eb.build()).queue();
        } else {
            receivedChannel.sendMessage(eb.build()).queue();
        }
    }

    @Override
    public String getHelp() {
        return "A beautiful way to display a text message\n" +
                "```\n" +
                "Usage //embed\n" +
                "```\n" +
                "```\n" +
                "-channel <#channel>\n" +
                "-color <#HEX>\n" +
                "-title <\"title (in quotes)\">\n" +
                "-url <URL>\n" +
                "-desc <\"descrition (in quotes)\">\n" +
                "-thumbnail <URL>\n" +
                "-image <URL>\n" +
                "-author <@user>\n" +
                "-footer <\"footer (in quotes)\">\n" +
                "-footer-icon <URL>\n" +
                "-field <\"name (in quotes)\"> <\"value (in quotes)\">\n" +
                "-inline-field <\"name (in quotes)\"> <\"value (in quotes)\">\n" +
                "```";
    }

    @Override
    public String getName() {
        return "embed";
    }
}
