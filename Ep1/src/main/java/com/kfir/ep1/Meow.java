package com.kfir.ep1;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Meow implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("heal")){
                    player.setHealth(20);
                    player.sendMessage("§aMeow hp");
                    return true;
                }
                if(args[0].equalsIgnoreCase("feed")){
                    player.setFoodLevel(20);
                    player.sendMessage("§aMeow food");
                    return true;
                }
            }
            return false;
        }
        sender.sendMessage("Only player can use this command.");
        return true;
    }
}
