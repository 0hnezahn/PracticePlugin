package de.legoshi.practicepluginv2.commands;

import de.legoshi.practicepluginv2.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JumpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            return true;
        }

        Player p = ((Player) commandSender).getPlayer();

        if (strings.length == 0) {
            p.sendMessage("/practice <build/jump>");
            return true;
        }

        if (strings[0].toLowerCase().equals("build")) {
            p.sendMessage("You entered build mode");
            Main.getInstance().inventoryObjectManager.getInventoryObjectManager().get(p).setBuild(p);
            return true;
        }
        if (strings[0].toLowerCase().equals("jump")) {
            p.sendMessage("You entered jump mode");
            Main.getInstance().inventoryObjectManager.getInventoryObjectManager().get(p).setJump(p);
            return true;
        }

        return false;
    }
}

