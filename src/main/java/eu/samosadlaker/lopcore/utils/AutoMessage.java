package eu.samosadlaker.lopcore.utils;

import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class AutoMessage extends BukkitRunnable {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix") + " &f» &3";

    private List<String> messages = config.getStringList("messages");
    private boolean random = config.getBoolean("message-random");
    private int lastMessage;

    @Override
    public void run() {

        String message = "";

        if(!random){
            try{
                message = message = messages.get(lastMessage + 1);
                lastMessage++;
            } catch (ArrayIndexOutOfBoundsException e){
                message = messages.get(0);
                lastMessage = 0;
            }


        } else {
            Random random = new Random();
            int nextMessage = random.nextInt(messages.size());
            while(nextMessage == lastMessage){
                nextMessage = random.nextInt(messages.size());
            }
            message = messages.get(nextMessage);
            lastMessage = nextMessage;
        }

        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(Colors.formatColor(prefix + message));
        }

    }
}
