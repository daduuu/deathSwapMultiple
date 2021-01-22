package co.daviddu.minecraft;

import co.daviddu.minecraft.commands.EndCommand;
import co.daviddu.minecraft.commands.SwapCommand;
import co.daviddu.minecraft.events.onPlayerDeath;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){
        new SwapCommand(this);
        new EndCommand(this);
        getServer().getPluginManager().registerEvents(new onPlayerDeath(this), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Death Swap Multiple Plugin has been enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Death Swap Multiple Plugin has been disabled");
    }
}
