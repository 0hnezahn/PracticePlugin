package de.legoshi.practicepluginv2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TierCalcCommand implements CommandExecutor {

    public int tierCalc(double height) {
        double sum = 1.24919;
        double n = 0.00301;
        int i;
        for (i = 4; sum >= height; i++) {
            sum = sum + n;
            n = (n - 0.08) * 0.98;
        }
        return i;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("No Player");
            return true;
        }

        Player p = ((Player) commandSender).getPlayer();

        if (strings.length == 0) {
            p.sendMessage("/tier <Height>");
            return true;
        }

        if (strings.length == 1) {

            try {

                if (Double.parseDouble(strings[0]) >= 1.24920 || Double.parseDouble(strings[0]) > -10000) {
                    p.sendMessage("Your Input is too hight/small");
                    return true;
                } else {
                    p.sendMessage("Tier: " + (tierCalc(Double.parseDouble(strings[0])) - 11) * (-1));
                    return true;
                }

            } catch (NumberFormatException ex) {

                p.sendMessage("Wrong input NOOB");
                return true;

            }

        }

        return false;
    }
}
