package com.kfir.ep3;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Points implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage("§eYou have to break: §c"+events.getItem.get(player.getUniqueId()).name());
            player.sendMessage("§eYou have: "+events.points.get(player.getUniqueId())+"p");
            return true;
        }
        sender.sendMessage("§cSorry but only player can use this command.");
        return true;
    }
}
