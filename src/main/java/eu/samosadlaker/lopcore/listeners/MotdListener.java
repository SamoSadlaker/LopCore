package eu.samosadlaker.lopcore.listeners;

import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * <h1>Motd!</h1>
 *
 * @author  SamoSadlaker
 * @version 1.0
 * @since   2021-01-14
 */
public class MotdListener implements Listener {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();

    /**
     * Serverlist Ping Listener... this function editing motd of server
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-14
     */
    @EventHandler
    public void onPlayerPing(ServerListPingEvent e){
        e.setMotd(Colors.formatColor(config.getString("motd.line1") + "\n" + config.getString("motd.lin2")));
    }
}
