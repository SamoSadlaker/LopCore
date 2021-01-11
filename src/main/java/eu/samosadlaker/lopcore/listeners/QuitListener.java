package eu.samosadlaker.lopcore.listeners;

import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class QuitListener implements Listener {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix") + " &f» &3";

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();

        e.setQuitMessage(Colors.formatColor(config.getString("leave-message").replace("@Player", p.getDisplayName())));
    }
}
