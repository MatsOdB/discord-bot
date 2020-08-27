package me.discordbot.customlibraries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsefulExtras {
    public String[] findArrayStrings(String[] args) {
        List<String> stringList = new ArrayList<>();
        int counter = 0;
        String tempStr = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("\"")) {
                for (int j = i; !args[j].endsWith("\""); j++) {
                    tempStr = String.join(" ", tempStr, args[j]);
                    counter++;
                }
                tempStr = String.join(" ", tempStr, args[i + counter]);
                stringList.add(tempStr);
            }
            counter = 0;
            tempStr = "";
        }
        return Arrays.copyOf(stringList.toArray(), stringList.size(), String[].class);
    }

    public List<String> findListStrings(List<String> args) {
        List<String> stringList = new ArrayList<>();
        int counter = 0;
        String tempStr = "";
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).startsWith("\"")) {
                for (int j = i; !args.get(j).endsWith("\""); j++) {
                    tempStr = String.join(" ", tempStr, args.get(j));
                    counter++;
                }
                tempStr = String.join(" ", tempStr, args.get(i + counter));
                stringList.add(tempStr);
            }
            counter = 0;
            tempStr = "";
        }
        return stringList;
    }
}