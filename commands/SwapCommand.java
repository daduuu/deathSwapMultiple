package co.daviddu.minecraft.commands;

import co.daviddu.minecraft.Main;
import com.google.gson.internal.$Gson$Preconditions;
import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SwapCommand implements CommandExecutor {
    private Main plugin;

    public int task1;
    public int task2;
    public int task0;
    private static List<Player> playerList;




    public SwapCommand(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("startswap").setExecutor(this);
        this.playerList = new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        try{
            if(args.length > 0){
                if(args.length == 1 && args[0].equalsIgnoreCase("@a")){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        playerList.add(p);
                    }
                    Bukkit.getServer().broadcastMessage("1");
                }
                else {
                    for (int k = 0; k < args.length; k++){
                        playerList.add(Bukkit.getPlayer(args[k]));
                    }
                    Bukkit.getServer().broadcastMessage("2");
                }
            }
            else{
                for (Player p : Bukkit.getOnlinePlayers()){
                    playerList.add(p);
                }
                Bukkit.getServer().broadcastMessage("3");
            }

            if(playerList.size() == 1){
                playerList.get(0).sendMessage(ChatColor.RED + "Cannot play with one player");
            }

            final BukkitScheduler schedule = Bukkit.getServer().getScheduler();

            final StringBuilder str = new StringBuilder();
            for (Player p : playerList){
                str.append(p.getName() + " ");
            }

            Bukkit.getServer().broadcastMessage("Game Starting Between " + str.toString());
            Bukkit.getServer().broadcastMessage("Test Swapping In");

            task0 = schedule.scheduleSyncRepeatingTask(this.plugin, new Runnable(){
                int num = 5;

                @Override
                public void run(){
                    if(num == 0){
                        Random rand = new Random();
                        if(num == 0){

                            int[] swapPositions = new int[playerList.size()];
                            Location[] swapLocations = new Location[playerList.size()];

                            for (int m = 0; m < playerList.size(); m++){
                                swapPositions[m] = -1;
                            }

                            for (int i = 0; i < playerList.size(); i++){
                                int randInt = i;
                                int count = 0;
                                boolean end_loop_bug = false;
                                while (randInt == i || find(swapPositions, randInt)){
                                    randInt = rand.nextInt(playerList.size());
                                    if(i == playerList.size() - 1 && count == 20){
                                        int randInt2 = rand.nextInt(playerList.size());
                                        while (randInt2 == playerList.size() - 1){
                                            randInt2 = rand.nextInt(playerList.size());
                                        }
                                        int temp = swapPositions[randInt2];
                                        Location tempLoc = swapLocations[randInt2];
                                        swapPositions[randInt2] = i;
                                        swapLocations[randInt2] = playerList.get(i).getLocation();
                                        swapPositions[i] = temp;
                                        swapLocations[i] = tempLoc;
                                        end_loop_bug = true;
                                        break;
                                    }
                                    count++;
                                }

                                if(!end_loop_bug){
                                    swapPositions[i] = randInt;
                                    swapLocations[i] = playerList.get(randInt).getLocation();
                                }

                                swapPositions[i] = randInt;
                                swapLocations[i] = playerList.get(randInt).getLocation();

                            }

                            for (int z = 0; z < playerList.size(); z++) {
                                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Swapped " + playerList.get(z).getName() + " and " + playerList.get(swapPositions[z]).getName());
                                playerList.get(z).teleport(swapLocations[z]);
                            }

                            Bukkit.getScheduler().cancelTask(task0);

                        }

                    }
                    else{
                        Bukkit.getServer().broadcastMessage(ChatColor.BLUE + Integer.toString(num));
                        num--;

                    }

                }

            }, 10L, 20L);


            task1 = schedule.scheduleSyncRepeatingTask(this.plugin, new Runnable(){

                int num = 11;

                @Override
                public void run(){
                    if(num == 0){
                        Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "Start!");
                        Bukkit.getServer().getScheduler().cancelTask(task1);

                    }
                    else if (num == 11){
                        Bukkit.getServer().broadcastMessage("Game Starting In...");
                        num--;
                    }
                    else{
                        Bukkit.getServer().broadcastMessage(ChatColor.BLUE + Integer.toString(num));
                        num--;
                    }

                }

            }, 120L, 20L);


            schedule.scheduleSyncRepeatingTask(this.plugin, new Runnable(){

                @Override
                public void run(){
                    task2 = schedule.scheduleSyncRepeatingTask(plugin, new Runnable(){
                        int num = 20;
                        @Override
                        public void run(){
                            if(playerList.size() == 1){
                                Bukkit.getServer().broadcastMessage(ChatColor.AQUA + ">>> " + SwapCommand.getPlayerList().get(0).getName() + " wins! <<<");
                                Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "Game is now stopping... ");
                                Bukkit.getServer().getScheduler().cancelTasks(plugin);
                            }
                            Random rand = new Random();
                            if(num == 0){

                                int[] swapPositions = new int[playerList.size()];
                                Location[] swapLocations = new Location[playerList.size()];

                                for (int m = 0; m < playerList.size(); m++){
                                    swapPositions[m] = -1;
                                }

                                for (int i = 0; i < playerList.size(); i++){
                                    int randInt = i;
                                    int count = 0;
                                    boolean end_loop_bug = false;
                                    while (randInt == i || find(swapPositions, randInt)){
                                        randInt = rand.nextInt(playerList.size());
                                        if(i == playerList.size() - 1 && count == 20){
                                            int randInt2 = rand.nextInt(playerList.size());
                                            while (randInt2 == playerList.size() - 1){
                                                randInt2 = rand.nextInt(playerList.size());
                                            }
                                            int temp = swapPositions[randInt2];
                                            Location tempLoc = swapLocations[randInt2];
                                            swapPositions[randInt2] = i;
                                            swapLocations[randInt2] = playerList.get(i).getLocation();
                                            swapPositions[i] = temp;
                                            swapLocations[i] = tempLoc;
                                            end_loop_bug = true;
                                            break;
                                        }
                                        count++;
                                    }

                                    if(!end_loop_bug){
                                        swapPositions[i] = randInt;
                                        swapLocations[i] = playerList.get(randInt).getLocation();
                                    }


                                }

                                for (int z = 0; z < playerList.size(); z++) {
                                    Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Swapped " + playerList.get(z).getName() + " and " + playerList.get(swapPositions[z]).getName());
                                    playerList.get(z).teleport(swapLocations[z]);
                                }

                                Bukkit.getScheduler().cancelTask(task2);

                            }
                            else{
                                Bukkit.getServer().broadcastMessage(ChatColor.BLUE + Integer.toString(num));
                                num--;
                            }

                        }

                    }, 0L, 20L);

                }

            }, 1340L, 1000L);
//20 ticks per second
        }
        catch (Exception e){
            sender.sendMessage(ChatColor.RED + "Invalid Player please try again");
        }

        return true;


    }
    public static boolean find(int[] array, int a){
        for (int i = 0; i < array.length; i++){
          if(array[i] == a){
              return true;
          }
        }
        return false;
    }

    public static List<Player> getPlayerList() {
        return playerList;
    }
}
