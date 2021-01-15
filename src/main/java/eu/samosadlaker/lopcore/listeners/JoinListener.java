package eu.samosadlaker.lopcore.listeners;

import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import java.util.List;

/**
 * <h1>Join Listener!</h1>
 * <p>This Class listen player join event & sending title & join message & motd</p>
 *
 * @author  SamoSadlaker
 * @version 3.0
 * @since   2021-01-11
 */
public class JoinListener implements Listener {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix") + " &f» &3";

    /**
     * This function listen player join event & sending messages, titles and actionbar.
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-11
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent e) throws InterruptedException {
        Player p = e.getPlayer();

        e.setJoinMessage(Colors.formatColor(config.getString("join-message").replace("%player%", p.getDisplayName())));


        List<String> messages = config.getStringList("welcome-message");


        sendMotd(messages, p);

       p.sendTitle(Colors.formatColor(config.getString("join-title")), Colors.formatColor(config.getString("join-subtitle")).replace("%player%", p.getDisplayName()), 30, 60, 30);

    }
    /**
     * This is a simple function. This function
     * need List of Strings and Player argument. Sending in for loop messages from list
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-12
     * @param messages List strings with messages
     * @param p Player instance
     */
    private void sendMotd(List<String>messages, Player p){
        String message = "";
        for( int i = 0; i < messages.size(); i++){


            message = message + messages.get(i) + "\n";
        }
        p.sendMessage(Colors.formatColor(message));
    }

}
