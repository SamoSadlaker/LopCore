package eu.samosadlaker.lopcore.listeners;

import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;
import eu.samosadlaker.lopcore.utils.ScoreboardManager;
import net.md_5.bungee.api.ChatMessageType;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

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
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws InterruptedException {
        Player p = e.getPlayer();

        e.setJoinMessage(Colors.formatColor(config.getString("join-message").replace("%player%", p.getDisplayName())));


        List<String> messages = config.getStringList("welcome-message");


        Bukkit.getScheduler ().runTaskLater (plugin, () -> sendMotd(messages, p), 20);

        Bukkit.getScheduler ().runTaskLater (plugin, () -> p.sendTitle(Colors.formatColor(config.getString("join-title")), Colors.formatColor(config.getString("join-subtitle")).replace("%player%", p.getDisplayName()), 40, 80, 40), 40);

        Bukkit.getScheduler ().runTaskLater (plugin, () -> sendActionBar(p), 40);

        ScoreboardManager.createBoard(p);
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
        for( int i = 0; i < messages.size(); i++){
            p.sendMessage(Colors.formatColor(messages.get(i)));
        }
    }

    /**
     * This is a simple function. This function need Player instance.
     * Send a action bar message.
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-12
     * @param p Player instance
     */
    private void sendActionBar(Player p){
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Colors.formatColor(config.getString("join-action-bar")).replace("%player%", p.getDisplayName())));
    }
}
