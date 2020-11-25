package com.kfir.ep3;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class events implements Listener {
    
    public static HashMap<UUID, Material> getItem = new HashMap<>();
    public static HashMap<UUID,Integer> points = new HashMap<>();

    public static Material randomBlock(){
        List<Material> materialList = new ArrayList<>();
        for(Material material : Material.values()){
            if(material.isBlock())
                if(!material.equals(Material.BEDROCK) && !material.equals(Material.BARRIER))
                    materialList.add(material);
        }
        return materialList.get((int) (System.currentTimeMillis()%materialList.size()));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        getItem.put(player.getUniqueId(),randomBlock());
        points.put(player.getUniqueId(),0);
        player.sendMessage("§eYou have to break: §c"+getItem.get(player.getUniqueId()).name());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        getItem.remove(player.getUniqueId());
        points.remove(player.getUniqueId());
    }
    
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(getItem.get(player.getUniqueId()).equals(event.getBlock().getType())){
            player.sendMessage("§aPoints +10");
            points.put(player.getUniqueId(),points.get(player.getUniqueId())+10);
            getItem.put(player.getUniqueId(),randomBlock());
            player.sendMessage("§eYou have to break: §c"+getItem.get(player.getUniqueId()).name());
        }
    }


}
