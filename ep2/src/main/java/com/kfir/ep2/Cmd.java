package com.kfir.ep2;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Cmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("ep2.meow")){
                player.sendMessage("§cYou do not have permission to use this command.");
                return true;
            }
        }

        if(args.length>=3){
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null){
                int times;
                try{
                    times = Integer.parseInt(args[1]);
                }catch (Exception exception){
                    sender.sendMessage("§cInvalid number");
                    return true;
                }
                if(times >= 1 && times <= 10) {
                    String message = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                    for (int i = 0; i < times; i++)
                        target.sendMessage("§e" + message);
                    sender.sendMessage("Sent.");
                    return true;
                }
                sender.sendMessage("§cInvalid number [1-10]");
                return true;
            }
            sender.sendMessage("§cCan't find player");
            return true;
        }
        sender.sendMessage("§cUsage: /"+label+" [name] [times] [message]");
        return true;
    }
}
