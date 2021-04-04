package de.legoshi.practicepluginv2.commands;

import de.legoshi.practicepluginv2.Main;
import de.legoshi.practicepluginv2.util.PlayerObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FrontJumpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {

            return true;

        }

        Player p = ((Player) commandSender).getPlayer();
        PlayerObject po = Main.getInstance().playerObjectManager.getPlayerObject().get(p);

        if(strings.length == 0) {

            p.sendMessage("/jf <on/off> <facing> <diag> <0>");
            return true;

        }

        if(strings.length >= 1) {

            if (strings[0].toLowerCase().equals("off")) {

                po.setAfterJump(false);
                po.setDirection("-");
                po.setTier(0);
                po.setJumps(0);
                po.setFinalJumps(0);
                po.setDiagonal(false);
                po.setFinalTier(0);
                po.setFinalTier2(0);
                p.sendMessage("Jumpfront turned off");
                return true;

            } else if (strings[0].toLowerCase().equals("on")) {

                po.setAfterJump(true);
                p.sendMessage("Jumpfront turned on");

                if(strings.length == 1) {

                    po.setDirection("-");
                    po.setTier(0);
                    po.setJumps(0);
                    po.setFinalJumps(0);
                    po.setDiagonal(false);

                }

                if(strings.length >= 2) {

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

                            if(strings.length >= 5) {

                                po.setTier(Double.parseDouble(strings[4]));
                                po.setFinalTier(tierCalc(po.getTier()));
                                po.setFinalTier2(tierCalc(po.getTier()));

                            }

                            return true;

                        }

                        return true;

                    }

                    return true;

                }

                return true;

            }

        }

        return false;

    }

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

}
