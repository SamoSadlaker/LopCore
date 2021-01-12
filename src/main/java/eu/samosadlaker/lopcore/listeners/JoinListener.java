package eu.samosadlaker.lopcore.listeners;

import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinListener implements Listener {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix") + " &f» &3";

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws InterruptedException {
        Player p = e.getPlayer();

        e.setJoinMessage(Colors.formatColor(config.getString("join-message").replace("%player%", p.getDisplayName())));


        List<String> messages = config.getStringList("welcome-message");
        for( int i = 0; i < messages.size(); i++){
            p.sendMessage(Colors.formatColor(messages.get(i)));
        }



        Bukkit.getScheduler ().runTaskLater (plugin, () -> p.sendTitle(Colors.formatColor(config.getString("join-title")), Colors.formatColor(config.getString("join-subtitle")).replace("%player%", p.getDisplayName()), 40, 80, 40), 40);

    }
}