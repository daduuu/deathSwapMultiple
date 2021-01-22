package co.daviddu.minecraft.commands;

import co.daviddu.minecraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EndCommand implements CommandExecutor{
    private Main plugin;

    public EndCommand(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("endswap").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        Bukkit.getServer().broadcastMessage("Ending Swap");
        SwapCommand.getPlayerList().clear();
        Bukkit.getServer().getScheduler().cancelTasks(this.plugin);
        return true;

    }
}
