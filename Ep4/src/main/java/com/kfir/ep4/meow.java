package com.kfir.ep4;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class meow implements CommandExecutor, Listener {

    public static List<UUID> finish = new ArrayList<>();
    public static HashMap<UUID, BukkitTask> tasks = new HashMap<>();
    public static HashMap<UUID, Inventory> inventoryHashMap = new HashMap<>();

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        finish.remove(player.getUniqueId());
        if (tasks.containsKey(player.getUniqueId())){
            tasks.get(player.getUniqueId()).cancel();
            tasks.remove(player.getUniqueId());
        }
        inventoryHashMap.remove(player.getUniqueId());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if(finish.contains(player.getUniqueId()))
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.openInventory(inventoryHashMap.get(player.getUniqueId()));
                }
            }.runTaskLater(Main.getInstance(),1);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getView().getTitle().equals("§bWow")){
            if(event.getClickedInventory() != null)
                if(event.getClickedInventory().equals(event.getView().getTopInventory()))
                    event.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            Inventory inventory = Bukkit.createInventory(null,9*3,"§bWow");
            inventoryHashMap.put(player.getUniqueId(),inventory);
            ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta glassMeta = glass.getItemMeta();
            glassMeta.setDisplayName("§f");
            glass.setItemMeta(glassMeta);
            for(int i = 0; i < inventory.getSize(); i++){
                inventory.setItem(i,glass);
            }
            ItemStack hopper = new ItemStack(Material.HOPPER);
            ItemMeta hopperMeta = hopper.getItemMeta();
            hopperMeta.setDisplayName("§f");
            hopper.setItemMeta(hopperMeta);
            inventory.setItem(4,hopper);
            finish.add(player.getUniqueId());
            List<ItemStack> list = new ArrayList<>(Arrays.asList(new ItemStack(Material.DIAMOND_BLOCK),new ItemStack(Material.DIAMOND),new ItemStack(Material.EMERALD_BLOCK),new ItemStack(Material.EMERALD)));
            List<ItemStack> items = new ArrayList<>(list);
            for(int i = 0; i < 13; i++){
                Random random = new Random();
                items.add(list.get(random.nextInt(list.size())));
            }
            int Final = (int) (items.size()+(System.currentTimeMillis()%items.size()));
            BukkitTask task = new BukkitRunnable() {
                int index = 0;
                @Override
                public void run() {
                    for(int i = 0; i < 9; i++)
                        inventory.setItem(i+9,items.get((i+index)%items.size()));
                    if(index >= Final){
                        player.getInventory().addItem(inventory.getItem(9+4));
                        finish.remove(player.getUniqueId());
                        tasks.remove(player.getUniqueId());
                        inventoryHashMap.remove(player.getUniqueId());
                        this.cancel();
                    }
                    index++;
                }
            }.runTaskTimer(Main.getInstance(),1,1);
            tasks.put(player.getUniqueId(),task);
            player.openInventory(inventory);
            return true;
        }
        sender.sendMessage("§cOnly player can use this command.");
        return true;
    }
}
