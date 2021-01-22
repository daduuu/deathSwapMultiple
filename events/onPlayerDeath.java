package co.daviddu.minecraft.events;

import co.daviddu.minecraft.Main;
import co.daviddu.minecraft.commands.SwapCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.List;

public class onPlayerDeath implements Listener {

    private Main plugin;

    public onPlayerDeath(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player p = event.getEntity().getPlayer();
        if(SwapCommand.getPlayerList().contains(p)){
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + p.getName() + " has been eliminated");
            SwapCommand.getPlayerList().remove(p);
            p.setOp(true);
        }
        if(SwapCommand.getPlayerList().size() == 1){
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + ">>> " + SwapCommand.getPlayerList().get(0).getName() + " wins! <<<");
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "Game is now stopping... ");
            Bukkit.getServer().getScheduler().cancelTasks(this.plugin);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player p = event.getPlayer();

        if(SwapCommand.getPlayerList().contains(p)){
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + p.getName() + " has left");
            SwapCommand.getPlayerList().remove(p);
        }
        if(SwapCommand.getPlayerList().size() == 1){
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + ">>> " + SwapCommand.getPlayerList().get(0).getName() + " wins! <<<");
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "Game is now stopping... ");
            Bukkit.getServer().getScheduler().cancelTasks(this.plugin);
        }
    }



}
