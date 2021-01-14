package eu.samosadlaker.lopcore.listeners;

import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * <h1>Chat listener!</h1>
 *
 * @author  SamoSadlaker
 * @version 3.0
 * @since   2021-01-11
 */
public class ChatListener implements Listener {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();

    /**
     * ChatListener... this function editing default minecraft message format
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-13
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        e.setFormat(Colors.formatColor(config.getString("format").replace("%name%", e.getPlayer().getDisplayName()).replace("%message%", e.getMessage())));
    }
}
