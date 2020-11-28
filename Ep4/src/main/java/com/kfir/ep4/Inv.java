package com.kfir.ep4;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inv implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length >= 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    player.openInventory(target.getInventory());
                    return true;
                }
                player.sendMessage("§cCan't find player.");
                return true;
            }
            player.sendMessage("§cUsage: /inv [name]");
            return true;
        }
        sender.sendMessage("§cOnly player can use this command.");
        return true;
    }
}
