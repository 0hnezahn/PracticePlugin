package de.legoshi.practicepluginv2.commands;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.util.PlayerObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class EdgeJumpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {

            return true;

        }

        Player p = ((Player) commandSender).getPlayer();
        PlayerObject po = Main.getInstance().playerObjectManager.getPlayerObject().get(p);

        if(strings.length == 0) {

            p.sendMessage("/jumpedge <on/off> <facing> <diag> <0>");
            return true;

        }

        if(strings[0].toLowerCase().equals("off")) {

            po.setBeforeJump(false);
            po.setDirection("-");
            po.setTier(0);
            po.setJumps(0);
            po.setFinalJumps(0);
            po.setFinalTier(0);
            po.setFinalTier2(0);
            po.setDiagonal(false);
            p.sendMessage("Jumpedge turned off");
            return true;

        } else if(strings[0].toLowerCase().equals("on")) {

            po.setBeforeJump(true);
            p.sendMessage("Jumpedge turned on");

            if(strings.length == 1) {

                po.setDirection("-");
                po.setTier(0);
                po.setJumps(0);
                po.setFinalJumps(0);
                po.setFinalTier(0);
                po.setFinalTier2(0);
                po.setDiagonal(false);
                return true;

            }

            if(!(strings[1].equals("-"))) {

                po.setDirection(strings[1]);

            } else {

                po.setDirection("-");

            }

                if(strings.length >= 3) {

                    po.setDiagonal(strings[2].equals("+"));

                    if(strings.length >= 4) {

                        po.setJumps(Integer.parseInt(strings[3]));
                        po.setFinalJumps(Integer.parseInt(strings[3]));

                        return true;

                    }

                    return true;

                }

                return true;

            }

        return false;

    }

}
