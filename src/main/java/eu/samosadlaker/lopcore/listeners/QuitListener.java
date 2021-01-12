package eu.samosadlaker.lopcore.listeners;

import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * <h1>Quit Listener!</h1>
 * <p>This Class listen player leave event & sending left message</p>
 *
 * @author  SamoSadlaker
 * @version 3.0
 * @since   2021-01-11
 */
public class QuitListener implements Listener {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix") + " &f» &3";

    /**
     * This function listen player leave event & sending messages.
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-11
     */
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();

        e.setQuitMessage(Colors.formatColor(config.getString("leave-message").replace("%player%", p.getDisplayName())));
    }
}
